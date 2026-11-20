package com.androidharness.musicplayer.feature.home

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ArtistKickerTest {

    private val library = listOf(
        testTrack("1", artist = "Nina Simone", album = "Pastel Blues", year = 1965, genre = "Jazz"),
        testTrack("2", artist = "Nina Simone", album = "I Put a Spell on You", year = 1965, genre = "jazz"),
        testTrack("3", artist = "Nina Simone", album = "Wild Is the Wind", year = 1966, genre = "Jazz"),
        testTrack("4", artist = "Someone Else", album = "Elsewhere", year = 2001, genre = "Rock"),
    )

    @Test
    fun blankArtistKeepsTheClockKicker() {
        assertNull(artistKicker("", "1", library))
        assertNull(artistKicker("   ", "1", library))
    }

    @Test
    fun unknownArtistKeepsTheClockKicker() {
        assertNull(artistKicker("Nobody Here", "1", library))
    }

    @Test
    fun factDescribesTheOwnedTracks() {
        val kicker = artistKicker("nina simone", "1", library)!!
        assertEquals("Nina Simone", kicker.artist)
        assertTrue(
            "unexpected fact: ${kicker.fact}",
            kicker.fact in setOf(
                "3 TRACKS ON YOUR SHELF",
                "3 ALBUMS ON YOUR SHELF",
                "USUALLY JAZZ",
                "ON SHELF 1965–1966",
            ),
        )
    }

    @Test
    fun lineIsUppercaseArtistsWithSeparator() {
        val kicker = artistKicker("Nina Simone", "1", library)!!
        assertTrue(kicker.line.startsWith("NINA SIMONE · "))
    }

    @Test
    fun sameTrackKeepsTheSameFact() {
        val first = artistKicker("Nina Simone", "track-1", library)!!.fact
        val again = artistKicker("Nina Simone", "track-1", library)!!.fact
        assertEquals(first, again)
    }

    @Test
    fun factRotatesAsDifferentTracksPlay() {
        val facts = (1..40).map { artistKicker("Nina Simone", "id-$it", library)!!.fact }.toSet()
        assertTrue("expected several facts over 40 tracks, got $facts", facts.size > 1)
    }
}
