package com.androidharness.musicplayer.data.local

import com.androidharness.musicplayer.data.local.entity.TrackEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class TrackSyncStateTest {
    @Test
    fun keepsLocalStateWhenMetadataIsRefreshed() {
        val current = track("Old").copy(isFavorite = true, rating = 4, isDownloaded = true,
            localUri = "file:///music", replayGainTrackDb = -2f, addedAtMs = 42)
        val incoming = track("New").copy(lastSyncedAtMs = 100)

        val result = preserveLocalTrackState(incoming, current)

        assertEquals("New", result.title)
        assertEquals(true, result.isFavorite)
        assertEquals(4, result.rating)
        assertEquals(true, result.isDownloaded)
        assertEquals("file:///music", result.localUri)
        assertEquals(-2f, result.replayGainTrackDb)
        assertEquals(42L, result.addedAtMs)
        assertEquals(100L, result.lastSyncedAtMs)
    }

    @Test
    fun keepsIncomingValuesForNewTracks() {
        val incoming = track("New")
        assertEquals(incoming, preserveLocalTrackState(incoming, null))
    }

    private fun track(title: String) = TrackEntity(
        rowId = 1, id = "1", title = title, artist = "Artist", album = "Album",
        albumId = null, artistId = null, durationMs = 0, trackNumber = null,
        discNumber = null, year = null, genre = null, bitrateKbps = null,
        sampleRateHz = null, channels = null, format = null, sizeBytes = null, coverArtId = null,
    )
}
