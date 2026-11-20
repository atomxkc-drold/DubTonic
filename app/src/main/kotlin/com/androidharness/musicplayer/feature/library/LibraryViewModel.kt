package com.androidharness.musicplayer.feature.library

import android.net.Uri
import androidx.compose.ui.graphics.Color
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.androidharness.musicplayer.core.model.Album
import com.androidharness.musicplayer.core.model.Artist
import com.androidharness.musicplayer.core.model.Playlist
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.core.result.AppResult
import com.androidharness.musicplayer.data.remote.SubsonicUrls
import com.androidharness.musicplayer.domain.repository.MusicRepository
import com.androidharness.musicplayer.feature.player.PlaybackConnection
import com.androidharness.musicplayer.ui.image.AlbumPaletteRepository
import com.androidharness.musicplayer.ui.image.AppImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class TrackUi(
    val track: Track,
    val artworkUri: Uri?,
)

data class LibraryUiState(
    val tracks: List<TrackUi> = emptyList(),
    val albums: List<AlbumUi> = emptyList(),
    val artists: List<ArtistUi> = emptyList(),
    val playlists: List<PlaylistUi> = emptyList(),
    val query: String = "",
    val isSyncing: Boolean = false,
    val message: String? = null,
    val filter: LibraryFilter = LibraryFilter.ALL,
    val section: LibrarySection = LibrarySection.TRACKS,
    val trackSort: TrackSort = TrackSort.TITLE,
    val artistCount: Int = 0,
)

enum class LibraryFilter {
    ALL,
    FAVORITES,
    DOWNLOADED,
}

sealed interface LibraryIntent {
    data class QueryChanged(val value: String) : LibraryIntent
    data class Play(val index: Int) : LibraryIntent
    data object Sync : LibraryIntent
    data class ToggleFavorite(val trackId: String, val favorite: Boolean) : LibraryIntent
    data class Download(val trackId: String) : LibraryIntent
    data class RemoveDownload(val trackId: String) : LibraryIntent
    data class FilterChanged(val filter: LibraryFilter) : LibraryIntent
    data class SectionChanged(val section: LibrarySection) : LibraryIntent
    data class TrackSortChanged(val sort: TrackSort) : LibraryIntent
    data class PlayAlbum(val albumId: String) : LibraryIntent
    data class PlayArtist(val artistId: String) : LibraryIntent
    data class PlayPlaylist(val playlistId: String) : LibraryIntent
    data object ShuffleAll : LibraryIntent
    data object DismissMessage : LibraryIntent
}

private data class LibraryData(
    val tracks: List<Track>,
    val albums: List<Album>,
    val artists: List<Artist>,
    val playlists: List<Playlist>,
)

private data class LibraryControls(
    val query: String,
    val syncing: Boolean,
    val message: String?,
    val filter: LibraryFilter,
    val section: LibrarySection,
    val sort: TrackSort,
)

@OptIn(FlowPreview::class, kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val repository: MusicRepository,
    private val urls: SubsonicUrls,
    private val connection: PlaybackConnection,
    private val imageLoader: AppImageLoader,
    private val paletteRepository: AlbumPaletteRepository,
) : ViewModel() {

    private val query = MutableStateFlow("")
    private val syncing = MutableStateFlow(false)
    private val message = MutableStateFlow<String?>(null)
    private val filter = MutableStateFlow(LibraryFilter.ALL)
    private val section = MutableStateFlow(LibrarySection.TRACKS)
    private val trackSort = MutableStateFlow(TrackSort.TITLE)

    private val allTracks = repository.observeTracks()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val albums = repository.observeAlbums()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val artists = repository.observeArtists()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val playlists = repository.observePlaylists()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    private val results = query
        .debounce(120)
        .distinctUntilChanged()
        .flatMapLatest { value ->
            if (value.isBlank()) repository.observeTracks() else repository.search(value)
        }

    private val data = combine(
        results,
        albums,
        artists,
        playlists,
    ) { tracks: List<Track>, albumsList: List<Album>, artistsList: List<Artist>,
        playlistsList: List<Playlist> ->
        LibraryData(
            tracks = tracks,
            albums = albumsList,
            artists = artistsList,
            playlists = playlistsList,
        )
    }

    private val controlsBase = combine(
        query,
        syncing,
        message,
        filter,
        section,
    ) { currentQuery: String, isSyncing: Boolean, currentMessage: String?,
        currentFilter: LibraryFilter, currentSection: LibrarySection ->
        ControlsBase(currentQuery, isSyncing, currentMessage, currentFilter, currentSection)
    }

    private val controls = combine(controlsBase, trackSort) { base: ControlsBase, sort: TrackSort ->
        LibraryControls(
            query = base.query,
            syncing = base.syncing,
            message = base.message,
            filter = base.filter,
            section = base.section,
            sort = sort,
        )
    }

    val state: StateFlow<LibraryUiState> = combine(data, controls) { library: LibraryData,
        control: LibraryControls ->
        buildState(library, control)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = LibraryUiState(),
    )

    fun onIntent(intent: LibraryIntent) {
        when (intent) {
            is LibraryIntent.QueryChanged -> query.value = intent.value
            is LibraryIntent.Play -> play(intent.index)
            LibraryIntent.Sync -> sync()
            is LibraryIntent.ToggleFavorite ->
                viewModelScope.launch {
                    repository.toggleFavorite(intent.trackId, intent.favorite)
                }
            is LibraryIntent.Download -> download(intent.trackId)
            is LibraryIntent.RemoveDownload -> removeDownload(intent.trackId)
            is LibraryIntent.FilterChanged -> filter.value = intent.filter
            is LibraryIntent.SectionChanged -> section.value = intent.section
            is LibraryIntent.TrackSortChanged -> trackSort.value = intent.sort
            is LibraryIntent.PlayAlbum -> playAlbum(intent.albumId)
            is LibraryIntent.PlayArtist -> playArtist(intent.artistId)
            is LibraryIntent.PlayPlaylist -> playPlaylist(intent.playlistId)
            LibraryIntent.ShuffleAll -> shuffleAll()
            LibraryIntent.DismissMessage -> message.value = null
        }
    }

    suspend fun colorFor(artwork: Uri?): Color = paletteRepository.colorsFor(artwork).primary

    private fun buildState(library: LibraryData, control: LibraryControls): LibraryUiState {
        val filtered = library.tracks.filter { track ->
            when (control.filter) {
                LibraryFilter.ALL -> true
                LibraryFilter.FAVORITES -> track.isFavorite
                LibraryFilter.DOWNLOADED -> track.isDownloaded
            }
        }
        val q = control.query.trim()
        return LibraryUiState(
            tracks = sortTracks(filtered, control.sort).map { track ->
                TrackUi(track = track, artworkUri = artworkFor(track.coverArtId))
            },
            albums = library.albums
                .filter { q.isBlank() || it.name.contains(q, ignoreCase = true) }
                .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { album: Album -> album.name })
                .map { album -> AlbumUi(album = album, artworkUri = artworkFor(album.coverArtId)) },
            artists = library.artists
                .filter { q.isBlank() || it.name.contains(q, ignoreCase = true) }
                .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { artist: Artist -> artist.name })
                .map { artist -> ArtistUi(artist = artist, artworkUri = artworkFor(artist.coverArtId)) },
            playlists = library.playlists
                .filter { q.isBlank() || it.name.contains(q, ignoreCase = true) }
                .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { playlist: Playlist -> playlist.name })
                .map { playlist -> PlaylistUi(playlist = playlist) },
            query = control.query,
            isSyncing = control.syncing,
            message = control.message,
            filter = control.filter,
            section = control.section,
            trackSort = control.sort,
            artistCount = library.artists.size,
        )
    }

    private fun play(index: Int) {
        val tracks = state.value.tracks
        if (tracks.isEmpty()) return
        connection.play(tracks.map { it.track.toMediaItem() }, index)
        tracks.getOrNull(index + 1)?.artworkUri?.let(imageLoader::preload)
    }

    private fun playAlbum(albumId: String) {
        val tracks = albumOrder(allTracks.value.filter { it.albumId == albumId })
        if (tracks.isEmpty()) return
        connection.play(tracks.map { it.toMediaItem() }, 0)
        tracks.getOrNull(1)?.coverArtId?.let { imageLoader.preload(artworkFor(it)) }
    }

    private fun playArtist(artistId: String) {
        val tracks = sortTracks(
            allTracks.value.filter { it.artistId == artistId },
            TrackSort.ARTIST,
        )
        if (tracks.isEmpty()) return
        connection.play(tracks.map { it.toMediaItem() }, 0)
        tracks.getOrNull(1)?.coverArtId?.let { imageLoader.preload(artworkFor(it)) }
    }

    private fun playPlaylist(playlistId: String) {
        viewModelScope.launch {
            val tracks = repository.observePlaylistTracks(playlistId).first()
            if (tracks.isEmpty()) return@launch
            connection.play(tracks.map { it.toMediaItem() }, 0)
            tracks.getOrNull(1)?.coverArtId?.let { imageLoader.preload(artworkFor(it)) }
        }
    }

    private fun shuffleAll() {
        val tracks = state.value.tracks
        if (tracks.isEmpty()) return
        connection.playShuffled(tracks.map { it.track.toMediaItem() })
    }

    private fun sync() {
        if (syncing.value) return
        viewModelScope.launch {
            syncing.value = true
            message.value = when (val result = repository.syncLibrary()) {
                is AppResult.Success ->
                    "Synced ${result.value.tracks} tracks from ${result.value.albums} albums"
                is AppResult.Failure -> result.error.message
            }
            syncing.value = false
        }
    }

    private fun download(trackId: String) {
        viewModelScope.launch {
            message.value = when (val result = repository.downloadTrack(trackId)) {
                is AppResult.Success -> "Downloaded for offline playback"
                is AppResult.Failure -> result.error.message
            }
        }
    }

    private fun removeDownload(trackId: String) {
        viewModelScope.launch {
            repository.removeDownload(trackId)
            message.value = "Removed download"
        }
    }

    private fun Track.toMediaItem(): MediaItem = MediaItem.Builder()
        .setMediaId(id)
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setTitle(title)
                .setArtist(artist)
                .setAlbumTitle(album)
                .setArtworkUri(artworkFor(coverArtId))
                .setDurationMs(durationMs)
                .setIsPlayable(true)
                .setIsBrowsable(false)
                .setMediaType(MediaMetadata.MEDIA_TYPE_MUSIC)
                .build()
        )
        .build()

    private fun artworkFor(coverArtId: String?): Uri? = coverArtId?.let { id ->
        runCatching { urls.coverArtUrl(id, size = 512).toUri() }.getOrNull()
    }
}

private data class ControlsBase(
    val query: String,
    val syncing: Boolean,
    val message: String?,
    val filter: LibraryFilter,
    val section: LibrarySection,
)
