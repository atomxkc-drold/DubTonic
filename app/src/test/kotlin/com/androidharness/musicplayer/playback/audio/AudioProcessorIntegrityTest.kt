package com.androidharness.musicplayer.playback.audio

import androidx.media3.common.C
import androidx.media3.common.audio.AudioProcessor.AudioFormat
import com.androidharness.musicplayer.data.settings.EqualizerSettings
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.PI
import kotlin.math.sin
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Guards the PCM contract of the processors that sit in front of the audio sink.
 *
 * A processor that emits a different number of samples than it receives changes the
 * playback rate: the sink still consumes at the configured sample rate, so extra or
 * missing samples are heard as chipmunk (pitch-shifted) or slowed audio. This test feeds
 * a known tone through each processor and asserts the sample count is preserved exactly.
 */
class AudioProcessorIntegrityTest {

    private val sampleRate = 44_100
    private val channels = 2
    private val frames = 1_024

    private fun shortBuffer(frames: Int): ByteBuffer {
        val buffer = ByteBuffer.allocateDirect(frames * channels * 2)
            .order(ByteOrder.nativeOrder())
        for (index in 0 until frames * channels) {
            val value = sin(2.0 * PI * 440.0 * index / sampleRate)
            buffer.putShort((value * 8_000).toInt().toShort())
        }
        buffer.flip()
        return buffer
    }

    private fun floatBuffer(frames: Int): ByteBuffer {
        val buffer = ByteBuffer.allocateDirect(frames * channels * 4)
            .order(ByteOrder.nativeOrder())
        for (index in 0 until frames * channels) {
            val value = sin(2.0 * PI * 440.0 * index / sampleRate)
            buffer.putFloat(value.toFloat())
        }
        buffer.flip()
        return buffer
    }

    private fun drain(processor: androidx.media3.common.audio.AudioProcessor, input: ByteBuffer): Int {
        processor.queueInput(input)
        val output = processor.getOutput()
        val bytes = output.remaining()
        // Consume like the audio sink does, so a second pass starts from a clean state.
        output.position(output.limit())
        return bytes
    }

    @Test
    fun visualizerPreservesShortSampleCount() {
        val processor = VisualizerProcessor(AudioLevelTap())
        val format = AudioFormat(sampleRate, channels, C.ENCODING_PCM_16BIT)
        assertEquals(format, processor.configure(format))

        val input = shortBuffer(frames)
        val expected = ByteArray(frames * channels * 2).also { input.duplicate().get(it) }
        processor.queueInput(input)
        val output = processor.getOutput()
        val actual = ByteArray(output.remaining()).also { output.duplicate().get(it) }

        assertEquals(expected.size, actual.size)
        // A pass-through tap must not alter a single byte: any change here is heard as
        // distortion, and a length change is heard as a pitch shift.
        assertArrayEquals(expected, actual)
    }

    @Test
    fun visualizerPreservesFloatSampleCount() {
        val processor = VisualizerProcessor(AudioLevelTap())
        val format = AudioFormat(sampleRate, channels, C.ENCODING_PCM_FLOAT)
        assertEquals(format, processor.configure(format))

        val input = floatBuffer(frames)
        val expected = ByteArray(frames * channels * 4).also { input.duplicate().get(it) }
        processor.queueInput(input)
        val output = processor.getOutput()
        val actual = ByteArray(output.remaining()).also { output.duplicate().get(it) }

        assertEquals(expected.size, actual.size)
        assertArrayEquals(expected, actual)
    }

    @Test
    fun eqPreservesShortSamplesWhenDisabled() {
        val processor = ParametricEqProcessor().apply { update(EqualizerSettings()) }
        val format = AudioFormat(sampleRate, channels, C.ENCODING_PCM_16BIT)
        processor.configure(format)

        val input = shortBuffer(frames)
        val expected = ByteArray(frames * channels * 2).also { input.duplicate().get(it) }
        processor.queueInput(input)
        val output = processor.getOutput()
        val actual = ByteArray(output.remaining()).also { output.duplicate().get(it) }

        assertEquals(expected.size, actual.size)
        assertArrayEquals(expected, actual)
    }

    @Test
    fun eqPreservesShortSampleCountWhenEnabled() {
        val processor = ParametricEqProcessor().apply {
            update(EqualizerSettings(enabled = true).withBandEnabled())
        }
        val format = AudioFormat(sampleRate, channels, C.ENCODING_PCM_16BIT)
        processor.configure(format)

        val emitted = drain(processor, shortBuffer(frames))
        assertEquals(frames * channels * 2, emitted)
    }

    @Test
    fun eqPreservesFloatSampleCountWhenEnabled() {
        val processor = ParametricEqProcessor().apply {
            update(EqualizerSettings(enabled = true).withBandEnabled())
        }
        val format = AudioFormat(sampleRate, channels, C.ENCODING_PCM_FLOAT)
        processor.configure(format)

        val emitted = drain(processor, floatBuffer(frames))
        assertEquals(frames * channels * 4, emitted)
    }

    /** The default bands are all flat, so give the chain something that actually filters. */
    private fun EqualizerSettings.withBandEnabled(): EqualizerSettings = copy(
        bands = bands.mapIndexed { index, band ->
            if (index == 0) band.copy(gainDb = 6f) else band
        },
    )
}
