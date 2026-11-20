package com.androidharness.musicplayer.feature.home

import com.androidharness.musicplayer.core.model.Track

/** Small factory shared by the home screen logic tests. */
internal fun testTrack(
    id: String,
    title: String = "Track $id",
    artist: String = "Artist",
    album: String = "Album",
    year: Int? = null,
    genre: String? = null,
    durationMs: Long = 180_000L,
    coverArtId: String? = null,
) = Track(
    id = id,
    title = title,
    artist = artist,
    album = album,
    albumId = null,
    artistId = null,
    durationMs = durationMs,
    trackNumber = null,
    discNumber = null,
    year = year,
    genre = genre,
    bitrateKbps = null,
    sampleRateHz = null,
    channels = null,
    format = null,
    sizeBytes = null,
    coverArtId = coverArtId,
)
