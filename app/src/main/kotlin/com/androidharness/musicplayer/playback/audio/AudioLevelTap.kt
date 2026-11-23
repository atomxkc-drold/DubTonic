package com.androidharness.musicplayer.playback.audio

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Live energy per band, published straight from the audio pipeline.
 *
 * Tapping the PCM that already flows through the player means the visualiser follows the
 * real music without the RECORD_AUDIO permission an audiofx.Visualizer would need.
 */
@Singleton
class AudioLevelTap @Inject constructor() {

    private val _levels = MutableStateFlow(FloatArray(BAR_COUNT))
    val levels: StateFlow<FloatArray> = _levels.asStateFlow()

    fun publish(values: FloatArray) {
        _levels.value = values
    }

    fun clear() {
        if (_levels.value.any { it > 0f }) {
            _levels.value = FloatArray(BAR_COUNT)
        }
    }

    companion object {
        const val BAR_COUNT = 32
    }
}
