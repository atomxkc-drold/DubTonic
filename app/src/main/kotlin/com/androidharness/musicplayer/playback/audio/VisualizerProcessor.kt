package com.androidharness.musicplayer.playback.audio

import android.os.SystemClock
import androidx.media3.common.C
import androidx.media3.common.audio.AudioProcessor.AudioFormat
import androidx.media3.common.audio.BaseAudioProcessor
import androidx.media3.common.util.UnstableApi
import java.nio.ByteBuffer
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Pass-through processor that measures the PCM already going to the speaker and reports
 * per-band energy to [AudioLevelTap]. It never changes a sample, so it is safe to leave in
 * the chain at all times.
 */
@UnstableApi
class VisualizerProcessor(
    private val tap: AudioLevelTap,
) : BaseAudioProcessor() {

    private var encoding = C.ENCODING_PCM_16BIT
    private var lastPublishMs = 0L

    override fun onConfigure(inputAudioFormat: AudioFormat): AudioFormat {
        if (inputAudioFormat.encoding != C.ENCODING_PCM_16BIT &&
            inputAudioFormat.encoding != C.ENCODING_PCM_FLOAT
        ) {
            return AudioFormat.NOT_SET
        }
        encoding = inputAudioFormat.encoding
        return inputAudioFormat
    }

    override fun onFlush() {
        lastPublishMs = 0L
        tap.clear()
    }

    override fun queueInput(inputBuffer: ByteBuffer) {
        val size = inputBuffer.remaining()
        if (size <= 0) return

        // Measure from a copy: put() advances the source buffer.
        measure(inputBuffer.duplicate())

        val output = replaceOutputBuffer(size)
        output.put(inputBuffer)
        output.flip()
    }

    private fun measure(buffer: ByteBuffer) {
        val now = SystemClock.elapsedRealtime()
        if (now - lastPublishMs < PUBLISH_INTERVAL_MS) return

        val values = FloatArray(AudioLevelTap.BAR_COUNT)
        when (encoding) {
            C.ENCODING_PCM_FLOAT -> {
                val samples = buffer.asFloatBuffer()
                val total = samples.limit()
                if (total < AudioLevelTap.BAR_COUNT) return
                fill(values, total) { index -> samples.get(index) }
            }
            else -> {
                val samples = buffer.asShortBuffer()
                val total = samples.limit()
                if (total < AudioLevelTap.BAR_COUNT) return
                fill(values, total) { index -> samples.get(index) / 32_768f }
            }
        }

        lastPublishMs = now
        tap.publish(values)
    }

    private inline fun fill(values: FloatArray, total: Int, sample: (Int) -> Float) {
        val perBand = (total / values.size).coerceAtLeast(1)
        for (band in values.indices) {
            val start = band * perBand
            val end = (start + perBand).coerceAtMost(total)
            var sum = 0.0
            var count = 0
            for (index in start until end) {
                val value = sample(index)
                sum += (value * value).toDouble()
                count++
            }
            val rms = if (count > 0) sqrt(sum / count).toFloat() else 0f
            values[band] = (rms * HEADROOM).pow(SHAPING).coerceIn(0f, 1f)
        }
    }

    private companion object {
        const val PUBLISH_INTERVAL_MS = 55L
        const val HEADROOM = 3.2f
        const val SHAPING = 0.65f
    }
}
