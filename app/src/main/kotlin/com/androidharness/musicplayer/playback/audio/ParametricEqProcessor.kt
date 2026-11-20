package com.androidharness.musicplayer.playback.audio

import androidx.media3.common.C
import androidx.media3.common.audio.AudioProcessor
import androidx.media3.common.audio.AudioProcessor.AudioFormat
import androidx.media3.common.audio.BaseAudioProcessor
import androidx.media3.common.util.UnstableApi
import com.androidharness.musicplayer.data.settings.EqualizerSettings
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.pow

/**
 * Parametric EQ over interleaved PCM. Supports 16-bit and float input and keeps a
 * per-channel biquad state per band, so no channel bleed occurs.
 */
@UnstableApi
@Singleton
class ParametricEqProcessor @Inject constructor() : BaseAudioProcessor() {

    @Volatile
    private var settings = EqualizerSettings()

    @Volatile
    private var filters: Array<Array<Biquad>> = emptyArray()
    private var channelCount = 2
    private var sampleRate = 44_100
    private var encoding = C.ENCODING_PCM_16BIT
    private var preamp = 1f

    fun update(newSettings: EqualizerSettings) {
        settings = newSettings
        if (isActive) rebuildFilters()
    }

    override fun onConfigure(inputAudioFormat: AudioFormat): AudioFormat {
        if (inputAudioFormat.encoding != C.ENCODING_PCM_16BIT &&
            inputAudioFormat.encoding != C.ENCODING_PCM_FLOAT
        ) {
            return AudioFormat.NOT_SET
        }
        channelCount = inputAudioFormat.channelCount
        encoding = inputAudioFormat.encoding
        sampleRate = inputAudioFormat.sampleRate
        rebuildFilters(sampleRate)
        return inputAudioFormat
    }

    override fun onFlush() {
        filters.forEach { band -> band.forEach(Biquad::reset) }
    }

    override fun queueInput(inputBuffer: ByteBuffer) {
        val byteCount = inputBuffer.remaining()
        val output = replaceOutputBuffer(byteCount)
        output.order(ByteOrder.nativeOrder())
        output.put(inputBuffer)
        inputBuffer.position(inputBuffer.limit())
        // Media3 hands the buffer returned by getOutput() straight to the next processor
        // and then to the audio sink, which only reads position..limit. Flipping here is
        // what makes the produced bytes readable; leaving the position at the end makes
        // the sink discard the whole frame, so the track plays silently.
        output.flip()

        val currentSettings = settings
        if (!currentSettings.enabled || filters.isEmpty()) return

        // Absolute indexing in both passes: the position stays 0 and the limit at byteCount.
        when (encoding) {
            C.ENCODING_PCM_FLOAT -> processFloat(output, byteCount)
            else -> processShort(output, byteCount)
        }
    }

    private fun processShort(buffer: ByteBuffer, byteCount: Int) {
        val samples = buffer.asShortBuffer()
        val sampleCount = byteCount / 2
        for (index in 0 until sampleCount) {
            val channel = index % channelCount
            var value = samples.get(index) / 32768f
            value = applyChain(channel, value)
            samples.put(index, (value.coerceIn(-1f, 1f) * 32767f).toInt().toShort())
        }
    }

    private fun processFloat(buffer: ByteBuffer, byteCount: Int) {
        val samples = buffer.asFloatBuffer()
        val sampleCount = byteCount / 4
        for (index in 0 until sampleCount) {
            val channel = index % channelCount
            var value = samples.get(index)
            value = applyChain(channel, value)
            samples.put(index, value.coerceIn(-1f, 1f))
        }
    }

    private fun applyChain(channel: Int, input: Float): Float {
        var value = input * preamp
        for (band in filters) {
            value = band[channel].process(value)
        }
        return value
    }

    private fun rebuildFilters(sampleRate: Int = this.sampleRate) {
        preamp = 10f.pow(settings.preampDb / 20f)
        val bands = settings.bands.take(16)
        filters = Array(bands.size) { bandIndex ->
            Array(channelCount) {
                Biquad.create(bands[bandIndex], sampleRate)
            }
        }
    }
}
