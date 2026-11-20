/**
 * Captures real-time FFT / waveform amplitudes from the ExoPlayer audio session ID
 * so the Now Playing waveform bars react dynamically to the actual playing audio.
 */
package com.androidharness.musicplayer.playback.audio

import android.media.audiofx.Visualizer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.hypot
import kotlin.math.log10

/**
 * Captures real-time FFT / waveform amplitudes from the ExoPlayer audio session ID
 * so the Now Playing waveform bars react dynamically to the actual playing audio.
 */
class LiveAudioWaveformCapture {
    private var visualizer: Visualizer? = null

    private val _liveAmplitudes = MutableStateFlow(List(64) { 0.15f })
    val liveAmplitudes: StateFlow<List<Float>> = _liveAmplitudes.asStateFlow()

    fun attachToSession(audioSessionId: Int, barCount: Int = 64) {
        release()
        if (audioSessionId <= 0) return

        runCatching {
            visualizer = Visualizer(audioSessionId).apply {
                captureSize = Visualizer.getCaptureSizeRange()[1].coerceAtMost(1024)
                setDataCaptureListener(
                    object : Visualizer.OnDataCaptureListener {
                        override fun onWaveFormDataCapture(
                            vis: Visualizer?,
                            waveform: ByteArray?,
                            samplingRate: Int
                        ) {
                            // Optional raw PCM capture
                        }

                        override fun onFftDataCapture(
                            vis: Visualizer?,
                            fft: ByteArray?,
                            samplingRate: Int
                        ) {
                            if (fft == null || fft.isEmpty()) return
                            _liveAmplitudes.value = mapFftToBars(fft, barCount)
                        }
                    },
                    Visualizer.getMaxCaptureRate() / 2,
                    false,
                    true
                )
                enabled = true
            }
        }
    }

    private fun mapFftToBars(fft: ByteArray, barCount: Int): List<Float> {
        val bins = fft.size / 2
        if (bins <= 1) return List(barCount) { 0.15f }
        // Focus on musical spectrum (20Hz - 14kHz)
        val usableBins = (bins * 0.7f).toInt().coerceAtLeast(barCount)
        val step = (usableBins / barCount).coerceAtLeast(1)

        return List(barCount) { i ->
            val index = (i * step + 1).coerceAtMost(bins - 1)
            val re = fft[index * 2].toFloat()
            val im = fft[index * 2 + 1].toFloat()
            val magnitude = hypot(re, im)
            val db = if (magnitude > 0f) 20f * log10(magnitude) else 0f
            ((db - 8f) / 42f).coerceIn(0.12f, 1.0f)
        }
    }

    fun release() {
        runCatching {
            visualizer?.enabled = false
            visualizer?.release()
        }
        visualizer = null
    }
}
