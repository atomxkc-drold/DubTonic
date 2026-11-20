package com.androidharness.musicplayer.feature.home

import com.androidharness.musicplayer.core.model.Track
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeArtworkTest {
    @Test
    fun selectsDistinctArtworkAndLimitsTheFan() {
        val tracks = listOf("one", "one", "two", null, "three", "four", "five", "six", "seven")
            .mapIndexed { index, art -> track(index, art) }

        assertEquals(listOf("one", "two", "three", "four", "five", "six"),
            selectHomeArtwork(tracks).map { it.coverArtId })
    }

    @Test
    fun emptyAndMissingArtworkProduceAnEmptyFan() {
        assertEquals(emptyList<Track>(), selectHomeArtwork(emptyList()))
        assertEquals(emptyList<Track>(), selectHomeArtwork(listOf(track(0, null))))
    }

    private fun track(index: Int, art: String?) = Track(
        id = index.toString(), title = "Track $index", artist = "Artist", album = "Album",
        albumId = null, artistId = null, durationMs = 0, trackNumber = null,
        discNumber = null, year = null, genre = null, bitrateKbps = null,
        sampleRateHz = null, channels = null, format = null, sizeBytes = null, coverArtId = art,
    )
}
