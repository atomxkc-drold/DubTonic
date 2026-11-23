package com.androidharness.musicplayer.feature.home

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.data.remote.SubsonicCredentialsStore
import com.androidharness.musicplayer.data.remote.SubsonicSession
import com.androidharness.musicplayer.data.remote.SubsonicUrls
import com.androidharness.musicplayer.data.settings.UiSettings
import com.androidharness.musicplayer.domain.repository.MusicRepository
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import com.androidharness.musicplayer.feature.player.PlaybackConnection
import com.androidharness.musicplayer.feature.player.PlaybackUiState
import com.androidharness.musicplayer.ui.image.AlbumColors
import com.androidharness.musicplayer.ui.image.AlbumPaletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalTime
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlin.random.Random

data class HomeClock(val hour: Int, val minute: Int)

/** How many covers the Home fan deck draws from the library per app launch. */
private const val FAN_SIZE = 24

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MusicRepository,
    private val urls: SubsonicUrls,
    private val connection: PlaybackConnection,
    private val paletteRepository: AlbumPaletteRepository,
    settingsRepository: SettingsRepository,
    credentials: SubsonicCredentialsStore,
) : ViewModel() {

    /** Signed-in Subsonic user, used by the brand header and the yellow name pill. */
    val session: StateFlow<SubsonicSession?> = credentials.session

    val tracks = repository.observeTracks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val playback: StateFlow<PlaybackUiState> = connection.state

    /**
     * Covers for the Home fan deck. The seed is generated once per process, so the deck
     * reshuffles every time the app is launched but stays put while it is open.
     */
    private val sessionSeed = Random.nextLong()

    val fanTracks: StateFlow<List<Track>> = tracks
        .map { all -> selectRandomArtwork(all, FAN_SIZE, sessionSeed) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val clock: StateFlow<HomeClock> = clockTicks()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            LocalTime.now().let { HomeClock(it.hour, it.minute) },
        )

    val ui: StateFlow<UiSettings> = settingsRepository.settings
        .map { it.ui }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UiSettings())

    /**
     * The aurora borrows the palette of whatever is playing; when nothing plays it falls
     * back to the freshest cover in the library so the wall still has a colour identity.
     */
    val colors: StateFlow<AlbumColors> = ambienceArt()
        .distinctUntilChanged()
        .mapLatest { uri -> paletteRepository.colorsFor(uri) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AlbumColors())

    init {
        connection.connect()
    }

    private fun clockTicks(): Flow<HomeClock> = flow {
        while (true) {
            val now = LocalTime.now()
            emit(HomeClock(now.hour, now.minute))
            delay(30_000)
        }
    }

    private fun ambienceArt(): Flow<Uri?> =
        combine(playback.map { it.artworkUri }.distinctUntilChanged(), tracks) { artwork, library ->
            artwork ?: artworkFor(library.firstOrNull())
        }

    fun artworkFor(track: Track?): Uri? = artUri(track?.coverArtId)

    fun artUri(coverArtId: String?): Uri? = coverArtId?.takeIf { it.isNotBlank() }?.let { id ->
        runCatching { urls.coverArtUrl(id, size = 512).toUri() }.getOrNull()
    }

    fun shuffleAll() {
        play(tracks.value, shuffle = true)
    }

    /** Liked Music tile in the Home launchpad. */
    fun playLiked() {
        play(tracks.value.filter { it.isFavorite }, shuffle = true)
    }

    /** New Release Mix tile: the most recently added songs. */
    fun playRecent() {
        play(tracks.value.sortedByDescending { it.addedAtMs }.take(30), shuffle = false)
    }

    /** Auto-ordered continuous mix that starts from the track already playing. */
    fun playFlow(seedId: String? = null) {
        val library = tracks.value
        if (library.isEmpty()) return
        val seed = seedId?.takeIf { it.isNotBlank() } ?: playback.value.trackId.takeIf { it.isNotBlank() }
        play(buildSonicFlow(library, seedId = seed, maxTracks = 60), shuffle = false)
    }

    fun playEra(bucket: EraBucket) {
        play(tracksInEra(tracks.value, bucket), shuffle = true)
    }

    fun playStack(stack: VibeStack) {
        val byId = tracks.value.associateBy { it.id }
        play(stack.trackIds.mapNotNull { byId[it] }, shuffle = false)
    }

    private fun play(list: List<Track>, shuffle: Boolean) {
        if (list.isEmpty()) return
        val items = list.map { it.toMediaItem() }
        if (shuffle) connection.playShuffled(items) else connection.play(items)
    }

    private fun Track.toMediaItem(): MediaItem = MediaItem.Builder()
        .setMediaId(id)
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setTitle(title)
                .setArtist(artist)
                .setAlbumTitle(album)
                .setArtworkUri(artUri(coverArtId))
                .setDurationMs(durationMs)
                .setIsPlayable(true)
                .setIsBrowsable(false)
                .setMediaType(MediaMetadata.MEDIA_TYPE_MUSIC)
                .build()
        )
        .build()
}
