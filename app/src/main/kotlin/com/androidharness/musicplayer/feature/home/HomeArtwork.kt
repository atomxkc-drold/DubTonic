package com.androidharness.musicplayer.feature.home

import com.androidharness.musicplayer.core.model.Track
import kotlin.random.Random

internal fun selectHomeArtwork(tracks: List<Track>): List<Track> = tracks
    .asSequence()
    .filter { !it.coverArtId.isNullOrBlank() }
    .distinctBy { it.coverArtId }
    .take(6)
    .toList()

/**
 * The fan deck: [count] covers picked at random from the whole library.
 *
 * The seed is fixed for the life of the process, so the deck is stable while the app is
 * open and reshuffles the next time it is launched.
 */
internal fun selectRandomArtwork(
    tracks: List<Track>,
    count: Int,
    seed: Long,
): List<Track> = tracks
    .asSequence()
    .filter { !it.coverArtId.isNullOrBlank() }
    .distinctBy { it.coverArtId }
    .toList()
    .shuffled(Random(seed))
    .take(count)
