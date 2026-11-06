package com.androidharness.musicplayer.feature.library

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.core.result.AppResult
import com.androidharness.musicplayer.data.remote.SubsonicUrls
import com.androidharness.musicplayer.domain.repository.MusicRepository
import com.androidharness.musicplayer.feature.player.PlaybackConnection
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
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class TrackUi(
    val track: Track,
    val artworkUri: Uri?,
)

data class LibraryUiState(
    val tracks: List<TrackUi> = emptyList(),
    val query: String = "",
    val isSyncing: Boolean = false,
    val message: String? = null,
)

sealed interface LibraryIntent {
    data class QueryChanged(val value: String) : LibraryIntent
    data class Play(val index: Int) : LibraryIntent
    data object Sync : LibraryIntent
    data class ToggleFavorite(val trackId: String, val favorite: Boolean) : LibraryIntent
    data class Download(val trackId: String) : LibraryIntent
    data class RemoveDownload(val trackId: String) : LibraryIntent
    data object DismissMessage : LibraryIntent
}

@OptIn(FlowPreview::class, kotlinx.coroutines.ExperimentalCoroutinesApi::class)
@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val repository: MusicRepository,
    private val urls: SubsonicUrls,
    private val connection: PlaybackConnection,
    private val imageLoader: AppImageLoader,
) : ViewModel() {

    private val query = MutableStateFlow("")
    private val syncing = MutableStateFlow(false)
    private val message = MutableStateFlow<String?>(null)

    private val results = query
        .debounce(120)
        .distinctUntilChanged()
        .flatMapLatest { value ->
            if (value.isBlank()) repository.observeTracks() else repository.search(value)
        }

    val state: StateFlow<LibraryUiState> = combine(
        results,
        query,
        syncing,
        message,
    ) { tracks, currentQuery, isSyncing, currentMessage ->
        LibraryUiState(
            tracks = tracks.map { track ->
                TrackUi(track = track, artworkUri = artworkFor(track))
            },
            query = currentQuery,
            isSyncing = isSyncing,
            message = currentMessage,
        )
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
            LibraryIntent.DismissMessage -> message.value = null
        }
    }

    private fun play(index: Int) {
        val tracks = state.value.tracks
        if (tracks.isEmpty()) return
        connection.play(tracks.map { it.toMediaItem() }, index)
        tracks.getOrNull(index + 1)?.artworkUri?.let(imageLoader::preload)
    }

    private fun sync() {        if (syncing.value) return
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

    private fun TrackUi.toMediaItem(): MediaItem = MediaItem.Builder()
        .setMediaId(track.id)
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setTitle(track.title)
                .setArtist(track.artist)
                .setAlbumTitle(track.album)
                .setArtworkUri(artworkUri)
                .setDurationMs(track.durationMs)
                .setIsPlayable(true)
                .setIsBrowsable(false)
                .setMediaType(MediaMetadata.MEDIA_TYPE_MUSIC)
                .build()
        )
        .build()

    private fun artworkFor(track: Track): Uri? = track.coverArtId?.let { coverArt ->
        runCatching { urls.coverArtUrl(coverArt, size = 512).toUri() }.getOrNull()
    }
}
