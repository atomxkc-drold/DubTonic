package com.androidharness.musicplayer.feature.home

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class VibeStacksTest {

    private val library = listOf(
        testTrack("1", year = 1994, genre = "Trip-Hop", coverArtId = "art-a"),
        testTrack("2", year = 1996, genre = "Trip-Hop", coverArtId = "art-b"),
        testTrack("3", year = 1998, genre = "Trip-Hop", coverArtId = "art-b"),
        testTrack("4", year = 1998, genre = "Trip-Hop", coverArtId = "art-c"),
        testTrack("5", year = 2003, genre = "Rock; Indie", coverArtId = "art-d"),
        testTrack("6", year = 2004, genre = "Rock; Indie", coverArtId = "art-e"),
        testTrack("7", year = 2005, genre = "Rock; Indie", coverArtId = "art-f"),
        testTrack("8", year = null, genre = null),
    )

    @Test
    fun stacksAreRankedBySizeWithReadableTitles() {
        val stacks = buildVibeStacks(library)

        assertEquals(listOf("1990s · Trip Hop", "2000s · Rock"), stacks.map { it.title })
        assertEquals(listOf(4, 3), stacks.map { it.trackIds.size })
        assertEquals(listOf(0, 1), stacks.map { it.accentIndex })
        assertEquals(listOf("1", "2", "3", "4"), stacks[0].trackIds)
        assertEquals("4 tracks · 12 min", stacks[0].subtitle)
    }

    @Test
    fun limitAndMinimumSizeAreHonoured() {
        assertEquals(1, buildVibeStacks(library, limit = 1).size)
        assertTrue(buildVibeStacks(library, limit = 0).isEmpty())

        val loose = buildVibeStacks(library, minSize = 1)
        assertEquals(3, loose.size)
        val undated = loose.first { it.title.startsWith("Undated") }
        assertEquals("Undated · Mixed", undated.title)
        assertEquals("1 track · 3 min", undated.subtitle)
    }

    @Test
    fun orderingDoesNotDependOnInputOrder() {
        val forward = buildVibeStacks(library)
        val shuffled = buildVibeStacks(library.reversed())

        assertEquals(forward.map { it.id }, shuffled.map { it.id })
        assertEquals(forward.map { it.trackIds }, shuffled.map { it.trackIds })
    }

    @Test
    fun coversAreDistinctAndCapped() {
        val stacks = buildVibeStacks(library)
        assertEquals(listOf("art-a", "art-b", "art-c"), stacks[0].coverArtIds)
        assertEquals(listOf("art-d", "art-e", "art-f"), stacks[1].coverArtIds)
        assertEquals(emptyList<String>(), buildVibeStacks(library, minSize = 1)[2].coverArtIds)
    }

    @Test
    fun genreLabelFallsBackAndIsNormalized() {
        assertEquals("Mixed", displayGenre(null))
        assertEquals("Mixed", displayGenre("   "))
        assertEquals("Rock", displayGenre("rock; indie"))
        assertEquals("Trip Hop", displayGenre("TRIP-HOP"))
    }

    @Test
    fun accentsCycleAcrossManyStacks() {
        val tracks = (0 until 12).map { index ->
            testTrack("t$index", year = 1950 + index * 10, genre = "Genre $index")
        }
        val stacks = buildVibeStacks(tracks, minSize = 1, limit = 12)
        assertEquals(12, stacks.size)
        assertEquals((0..11).map { it % 5 }, stacks.map { it.accentIndex })
    }

    @Test
    fun emptyLibraryHasNoStacks() {
        assertTrue(buildVibeStacks(emptyList()).isEmpty())
    }
}
