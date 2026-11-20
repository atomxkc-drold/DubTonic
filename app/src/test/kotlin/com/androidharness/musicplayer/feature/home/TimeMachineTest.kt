package com.androidharness.musicplayer.feature.home

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class TimeMachineTest {

    private val library = listOf(
        testTrack("a", year = 1994),
        testTrack("b", year = 1998),
        testTrack("c", year = 2003),
        testTrack("d", year = 2004),
        testTrack("e", year = null),
        testTrack("f", year = 1700),
    )

    @Test
    fun decadesBecomeContiguousSlicesOfOneTurn() {
        val buckets = eraBuckets(library)

        assertEquals(listOf(1990, 2000), buckets.map { it.startYear })
        assertEquals(listOf(2, 2), buckets.map { it.count })
        assertEquals(0f, buckets[0].startDegrees, 0.001f)
        assertEquals(180f, buckets[0].sweepDegrees, 0.001f)
        assertEquals(180f, buckets[1].startDegrees, 0.001f)
        assertEquals(360f, buckets[1].endDegrees, 0.001f)
        assertEquals(1f, buckets.sumOf { it.share.toDouble() }.toFloat(), 0.001f)
        assertEquals(listOf("1990s", "2000s"), buckets.map { it.label })
    }

    @Test
    fun undatedTracksAreOptional() {
        assertEquals(6, library.size)
        assertTrue(eraBuckets(library).none { it.isUndated })

        val withUndated = eraBuckets(library, includeUndated = true)
        val undated = withUndated.first { it.isUndated }
        assertEquals(2, undated.count)
        assertEquals("Undated", undated.label)
        assertEquals(2, tracksInEra(library, undated).size)
    }

    @Test
    fun angleHitTestWrapsAndHandlesBoundaries() {
        val buckets = eraBuckets(library)

        assertEquals(1990, eraAtAngle(buckets, 10f)?.startYear)
        assertEquals(2000, eraAtAngle(buckets, 200f)?.startYear)
        assertEquals(2000, eraAtAngle(buckets, -10f)?.startYear)
        assertEquals(1990, eraAtAngle(buckets, 360f)?.startYear)
        assertEquals(2000, eraAtAngle(buckets, 359.9f)?.startYear)
        assertEquals(1990, eraAtAngle(buckets, 400f)?.startYear)
        assertNull(eraAtAngle(emptyList(), 42f))
        assertNull(eraAtAngle(buckets, Float.NaN))
    }

    @Test
    fun eraSelectionKeepsOnlyThatDecade() {
        val buckets = eraBuckets(library)
        val nineties = buckets.first { it.startYear == 1990 }
        assertEquals(listOf("a", "b"), tracksInEra(library, nineties).map { it.id })
    }

    @Test
    fun decadeOfRejectsImpossibleYears() {
        assertEquals(1990, decadeOf(1999))
        assertEquals(1990, decadeOf(1990))
        assertNull(decadeOf(null))
        assertNull(decadeOf(1700))
        assertNull(decadeOf(2100))
        assertNull(decadeOf(3000))
    }

    @Test
    fun emptyLibraryHasNoDial() {
        assertTrue(eraBuckets(emptyList()).isEmpty())
        assertTrue(eraBuckets(emptyList(), includeUndated = true).isEmpty())
    }
}
