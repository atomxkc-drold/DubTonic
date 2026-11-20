package com.androidharness.musicplayer.feature.home

import androidx.compose.ui.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HomeAtmosphereTest {

    @Test
    fun clockIsMappedToSixPhasesAndWrapsAround() {
        assertEquals(DayPhase.NIGHT, dayMood(3).phase)
        assertEquals(DayPhase.DAWN, dayMood(6).phase)
        assertEquals(DayPhase.MORNING, dayMood(9).phase)
        assertEquals(DayPhase.AFTERNOON, dayMood(14).phase)
        assertEquals(DayPhase.GOLDEN, dayMood(18).phase)
        assertEquals(DayPhase.EVENING, dayMood(22).phase)

        assertEquals(DayPhase.NIGHT, dayMood(25).phase)
        assertEquals(DayPhase.EVENING, dayMood(-2).phase)
    }

    @Test
    fun everyHourHasUsableCopyAndBoundedWarmth() {
        (0..23).forEach { hour ->
            val mood = dayMood(hour)
            assertTrue(hour.toString(), mood.kicker.isNotBlank())
            assertTrue(hour.toString(), mood.headline.isNotBlank())
            assertTrue(hour.toString(), mood.blurb.isNotBlank())
            assertTrue(hour.toString(), mood.warmth in 0f..1f)
        }
        assertEquals(dayMood(18).warmth, (0..23).maxOf { dayMood(it).warmth }, 0f)
    }

    @Test
    fun kickerCarriesPaddedClockAndNormalizesInput() {
        assertEquals("LATE NIGHT · 03:07", moodKicker(dayMood(3), 3, 7))
        assertEquals("GOLDEN HOUR · 18:00", moodKicker(dayMood(18), 18, 0))
        assertEquals("LATE NIGHT · 01:01", moodKicker(dayMood(3), 25, 61))
    }

    @Test
    fun goldenHourIsWarmerThanNightAtTheSameAlbumColour() {
        val primary = Color(0xFF2F6F63)
        val secondary = Color(0xFF4A5A70)
        val night = auroraFor(primary, secondary, dayMood(2), reducedMotion = false)
        val golden = auroraFor(primary, secondary, dayMood(18), reducedMotion = false)

        assertEquals(3, night.blobs.size)
        assertEquals(3, golden.blobs.size)
        assertTrue(golden.blobs[0].red > night.blobs[0].red)
        assertTrue(golden.blobs[0].blue < night.blobs[0].blue)
        assertTrue(golden.glow.red > night.glow.red)
        assertNotEquals(night.shade, Color(0xFF000000))
    }

    @Test
    fun driftStopsForReducedMotionAndStaysInRangeOtherwise() {
        val primary = Color(0xFF2F6F63)
        val secondary = Color(0xFF4A5A70)
        (0..23).forEach { hour ->
            val spec = auroraFor(primary, secondary, dayMood(hour), reducedMotion = false)
            assertTrue(spec.driftMillis in 15_000..35_000)
        }
        val still = auroraFor(primary, secondary, dayMood(12), reducedMotion = true)
        assertEquals(0, still.driftMillis)
    }
}
