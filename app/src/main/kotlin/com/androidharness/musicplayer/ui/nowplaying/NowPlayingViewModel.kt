package com.androidharness.musicplayer.ui.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import com.androidharness.musicplayer.playback.audio.LiveAudioWaveformCapture

/**
 * ViewModel for the Now Playing screen. Holds the UI state and manages
 * the live audio waveform capture from ExoPlayer.
 */
class NowPlayingViewModel(
    private val audioSessionId: Int,
    private val barCount: Int = 64
) : ViewModel() {

    private val _uiState = MutableStateFlow<NowPlayingUiState>(NowPlayingUiState())
    val uiState: StateFlow<NowPlayingUiState> = _uiState.asStateFlow()

    private val waveformCapture = LiveAudioWaveformCapture()

    init {
        waveformCapture.attachToSession(audioSessionId, barCount)
        // Observe live amplitudes and update UI state
        waveformCapture.liveAmplitudes
            .onEach { liveBars ->
                _uiState.update { current ->
                    current.copy(
                        liveSpectrumBars = liveBars
                    )
                }
            }.launchIn(viewModelScope)
    }

    fun onPlayerSessionChanged(audioSessionId: Int) {
        waveformCapture.release()
        waveformCapture.attachToSession(audioSessionId, barCount)
    }

    fun onStop() {
        waveformCapture.release()
    }

    companion object {
        fun create(
            factory: ViewModelProvider.Factory,
            audioSessionId: Int,
            barCount: Int = 64
        ): NowPlayingViewModel = NowPlayingViewModel(audioSessionId, barCount)
    }
}
