package com.androidharness.musicplayer.feature.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidharness.musicplayer.data.settings.UiSettings
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import com.androidharness.musicplayer.ui.image.AlbumColors
import com.androidharness.musicplayer.ui.image.AlbumPaletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val connection: PlaybackConnection,
    paletteRepository: AlbumPaletteRepository,
    settingsRepository: SettingsRepository,
) : ViewModel() {

    val playback: StateFlow<PlaybackUiState> = connection.state

    val colors: StateFlow<AlbumColors> = connection.state
        .map { it.artworkUri }
        .distinctUntilChanged()
        .mapLatest { uri -> paletteRepository.colorsFor(uri) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AlbumColors())

    val uiSettings: StateFlow<UiSettings> = settingsRepository.settings
        .map { it.ui }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UiSettings())

    init {
        connection.connect()
    }

    fun playPause() = connection.togglePlayPause()

    fun next() = connection.next()

    fun previous() = connection.previous()

    fun seekToFraction(fraction: Float) {
        val duration = playback.value.durationMs
        if (duration > 0) connection.seekTo((duration * fraction).toLong())
    }

    fun toggleShuffle() = connection.setShuffle(!playback.value.shuffleEnabled)

    fun cycleRepeat() = connection.cycleRepeatMode()
}
