package com.androidharness.musicplayer.feature.home

import com.androidharness.musicplayer.core.model.Track

internal fun selectHomeArtwork(tracks: List<Track>): List<Track> = tracks
    .asSequence()
    .filter { !it.coverArtId.isNullOrBlank() }
    .distinctBy { it.coverArtId }
    .take(6)
    .toList()
