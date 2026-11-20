package com.androidharness.musicplayer.feature.library

import com.androidharness.musicplayer.core.model.Track
import org.junit.Assert.assertEquals
import org.junit.Test

class LibrarySortingTest {

    @Test
    fun titleSortIsCaseInsensitive() {
        val tracks = listOf(
            track(id = "1", title = "banana", artist = "A"),
            track(id = "2", title = "Apple", artist = "B"),
            track(id = "3", title = "Cherry", artist = "A"),
        )

        assertEquals(listOf("2", "1", "3"), sortTracks(tracks, TrackSort.TITLE).map { it.id })
    }

    @Test
    fun artistSortGroupsAlbumThenDiscThenTrack() {
        val tracks = listOf(
            track(id = "a", title = "T", artist = "B", album = "Z"),
            track(id = "b", title = "T", artist = "A", album = "M", discNumber = 2, trackNumber = 1),
            track(id = "c", title = "T", artist = "A", album = "M", discNumber = 1, trackNumber = 2),
            track(id = "d", title = "T", artist = "A", album = "M", discNumber = 1, trackNumber = 1),
        )

        assertEquals(
            listOf("d", "c", "b", "a"),
            sortTracks(tracks, TrackSort.ARTIST).map { it.id },
        )
    }

    @Test
    fun albumSortOrdersDiscThenTrack() {
        val tracks = listOf(
            track(id = "a", title = "T", artist = "A", album = "B", discNumber = 1, trackNumber = 1),
            track(id = "b", title = "T", artist = "A", album = "A", discNumber = 2, trackNumber = 1),
            track(id = "c", title = "T", artist = "A", album = "A", discNumber = 1, trackNumber = 2),
            track(id = "d", title = "T", artist = "A", album = "A", discNumber = 1, trackNumber = 1),
        )

        assertEquals(
            listOf("d", "c", "b", "a"),
            sortTracks(tracks, TrackSort.ALBUM).map { it.id },
        )
    }

    @Test
    fun recentSortPutsNewestFirst() {
        val tracks = listOf(
            track(id = "a", addedAtMs = 100),
            track(id = "b", addedAtMs = 300),
            track(id = "c", addedAtMs = 200),
        )

        assertEquals(listOf("b", "c", "a"), sortTracks(tracks, TrackSort.RECENT).map { it.id })
    }

    @Test
    fun durationSortPutsLongestFirst() {
        val tracks = listOf(
            track(id = "a", durationMs = 1_000),
            track(id = "b", durationMs = 3_000),
            track(id = "c", durationMs = 2_000),
        )

        assertEquals(listOf("b", "c", "a"), sortTracks(tracks, TrackSort.DURATION).map { it.id })
    }

    @Test
    fun albumOrderSortsDiscThenTrack() {
        val tracks = listOf(
            track(id = "a", discNumber = 1, trackNumber = 3),
            track(id = "b", discNumber = 2, trackNumber = 1),
            track(id = "c", discNumber = 1, trackNumber = 1),
        )

        assertEquals(listOf("c", "a", "b"), albumOrder(tracks).map { it.id })
    }

    private fun track(
        id: String,
        title: String = "Track",
        artist: String = "Artist",
        album: String = "Album",
        discNumber: Int? = null,
        trackNumber: Int? = null,
        durationMs: Long = 0,
        addedAtMs: Long = 0,
    ) = Track(
        id = id,
        title = title,
        artist = artist,
        album = album,
        albumId = null,
        artistId = null,
        durationMs = durationMs,
        trackNumber = trackNumber,
        discNumber = discNumber,
        year = null,
        genre = null,
        bitrateKbps = null,
        sampleRateHz = null,
        channels = null,
        format = null,
        sizeBytes = null,
        coverArtId = null,
        addedAtMs = addedAtMs,
    )
}
