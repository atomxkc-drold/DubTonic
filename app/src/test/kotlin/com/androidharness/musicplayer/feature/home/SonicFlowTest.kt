package com.androidharness.musicplayer.feature.home

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SonicFlowTest {

    private val library = listOf(
        testTrack("1960", year = 1960),
        testTrack("1962", year = 1962),
        testTrack("1999", year = 1999),
        testTrack("2001", year = 2001),
        testTrack("2003", year = 2003),
    )

    @Test
    fun arcWalksToTheNearestDecadeFirst() {
        val flow = buildSonicFlow(library, seedId = "1960")

        assertEquals(listOf("1960", "1962", "1999", "2001", "2003"), flow.map { it.id })
    }

    @Test
    fun arcIsSmootherThanTheInputOrder() {
        val scrambled = listOf(library[4], library[0], library[3], library[1], library[2])
        val flow = buildSonicFlow(scrambled, seedId = "1960")

        assertTrue(
            "flow=${averageStepDistance(flow)} input=${averageStepDistance(scrambled)}",
            averageStepDistance(flow) < averageStepDistance(scrambled),
        )
    }

    @Test
    fun resultIsDeterministicAndSeedIsHonoured() {
        val forward = buildSonicFlow(library, seedId = "1999")
        val reversedInput = buildSonicFlow(library.reversed(), seedId = "1999")

        assertEquals(forward.map { it.id }, reversedInput.map { it.id })
        assertEquals("1999", forward.first().id)

        val unseeded = buildSonicFlow(library)
        assertEquals(buildSonicFlow(library.reversed()).map { it.id }, unseeded.map { it.id })
    }

    @Test
    fun queueIsCappedAndKeepsEveryTrackOnce() {
        assertEquals(library.size, buildSonicFlow(library).size)
        assertEquals(library.size, buildSonicFlow(library).map { it.id }.distinct().size)
        assertEquals(3, buildSonicFlow(library, maxTracks = 3).size)
        assertTrue(buildSonicFlow(library, maxTracks = 0).isEmpty())
    }

    @Test
    fun missingMetadataStillProducesAQueue() {
        val unknown = listOf(
            testTrack("x", durationMs = 90_000),
            testTrack("y", durationMs = 400_000, year = 2010),
            testTrack("z", durationMs = 185_000),
        )
        val flow = buildSonicFlow(unknown)

        assertEquals(setOf("x", "y", "z"), flow.map { it.id }.toSet())
        assertEquals(listOf("x", "z", "y"), flow.map { it.id })
    }

    @Test
    fun emptyAndSingleLibrariesAreSafe() {
        assertTrue(buildSonicFlow(emptyList()).isEmpty())

        val one = listOf(testTrack("only"))
        assertEquals(listOf("only"), buildSonicFlow(one).map { it.id })
        assertEquals(0f, averageStepDistance(one), 0f)
    }

    @Test
    fun genrePenaltyKeepsSameGenreTogether() {
        val tracks = listOf(
            testTrack("a", year = 1990, genre = "Jazz"),
            testTrack("b", year = 1991, genre = "Techno"),
            testTrack("c", year = 1990, genre = "Jazz"),
        )
        assertEquals(listOf("a", "c", "b"), buildSonicFlow(tracks, seedId = "a").map { it.id })
    }

    @Test
    fun listenTimeReadsNaturally() {
        assertEquals("0 min", formatListenTime(0))
        assertEquals("30s", formatListenTime(30_000))
        assertEquals("5 min", formatListenTime(5 * 60_000L))
        assertEquals("1h", formatListenTime(60 * 60_000L))
        assertEquals("1h 12m", formatListenTime(72 * 60_000L))
    }
}
