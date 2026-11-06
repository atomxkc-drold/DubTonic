package com.androidharness.musicplayer.playback.audio

import com.androidharness.musicplayer.data.settings.BandType
import com.androidharness.musicplayer.data.settings.EqualizerBand
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BiquadTest {

    @Test
    fun unityFilterPassesSignal() {
        val biquad = Biquad.unity()
        assertEquals(0.5f, biquad.process(0.5f), 0f)
        assertEquals(-0.25f, biquad.process(-0.25f), 0f)
    }

    @Test
    fun zeroGainBandIsTransparent() {
        val biquad = Biquad.create(
            EqualizerBand(frequencyHz = 1_000f, gainDb = 0f, q = 1f),
            sampleRate = 44_100,
        )
        assertEquals(0.33f, biquad.process(0.33f), 0f)
    }

    @Test
    fun peakingFilterStaysFinite() {
        val biquad = Biquad.create(
            EqualizerBand(frequencyHz = 1_000f, gainDb = 9f, q = 1.4f, type = BandType.PEAKING),
            sampleRate = 48_000,
        )
        repeat(20_000) { index ->
            val input = if (index % 2 == 0) 0.8f else -0.8f
            assertTrue(biquad.process(input).isFinite())
        }
    }

    @Test
    fun shelvesStayFiniteAtBandEdges() {
        listOf(
            EqualizerBand(frequencyHz = 20f, gainDb = -12f, type = BandType.LOW_SHELF),
            EqualizerBand(frequencyHz = 20_000f, gainDb = 12f, type = BandType.HIGH_SHELF),
        ).forEach { band ->
            val biquad = Biquad.create(band, sampleRate = 44_100)
            repeat(5_000) { index ->
                val output = biquad.process(if (index % 2 == 0) 1f else -1f)
                assertTrue(output.isFinite())
            }
        }
    }

    @Test
    fun resetClearsHistory() {
        val biquad = Biquad.create(
            EqualizerBand(frequencyHz = 200f, gainDb = 6f),
            sampleRate = 44_100,
        )
        repeat(100) { biquad.process(0.5f) }
        biquad.reset()
        val firstAfterReset = biquad.process(0f)
        assertEquals(0f, firstAfterReset, 0f)
    }
}
