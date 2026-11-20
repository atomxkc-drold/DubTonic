package com.androidharness.musicplayer.feature.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.data.remote.SubsonicCredentialsStore
import com.androidharness.musicplayer.data.remote.SubsonicSession
import com.androidharness.musicplayer.data.settings.UiSettings
import com.androidharness.musicplayer.domain.repository.MusicRepository
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import com.androidharness.musicplayer.ui.image.AlbumColors
import com.androidharness.musicplayer.ui.image.AlbumPaletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val connection: PlaybackConnection,
    private val repository: MusicRepository,
    paletteRepository: AlbumPaletteRepository,
    settingsRepository: SettingsRepository,
    credentials: SubsonicCredentialsStore,
) : ViewModel() {

    val session: StateFlow<SubsonicSession?> = credentials.session

    val playback: StateFlow<PlaybackUiState> = connection.state

    val colors: StateFlow<AlbumColors> = connection.state
        .map { it.artworkUri }
        .distinctUntilChanged()
        .mapLatest { uri -> paletteRepository.colorsFor(uri) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AlbumColors())

    val uiSettings: StateFlow<UiSettings> = settingsRepository.settings
        .map { it.ui }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UiSettings())

    val details: StateFlow<Track?> = connection.state
        .map { it.trackId }
        .distinctUntilChanged()
        .flatMapLatest { id -> if (id.isBlank()) flowOf(null) else repository.observeTrack(id) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    init {
        connection.connect()
    }

    fun playPause() = connection.togglePlayPause()

    fun next() = connection.next()

    fun previous() = connection.previous()

    fun seekToFraction(fraction: Float) {
        val duration = playback.value.durationMs
        if (duration > 0) connection.seekTo((duration * fraction.coerceIn(0f, 1f)).toLong())
    }

    fun toggleShuffle() = connection.setShuffle(!playback.value.shuffleEnabled)

    fun cycleRepeat() = connection.cycleRepeatMode()

    fun toggleFavorite() {
        val track = details.value ?: return
        viewModelScope.launch { repository.toggleFavorite(track.id, !track.isFavorite) }
    }

    fun setRating(rating: Int) {
        val track = details.value ?: return
        viewModelScope.launch { repository.setRating(track.id, rating) }
    }
}
