package com.androidharness.musicplayer.feature.home

import com.androidharness.musicplayer.core.model.Track
import java.util.Locale
import kotlin.math.absoluteValue

/**
 * The hero kicker doubles as the "did you know" line: while something is playing it shows
 * the artist plus one fact about them instead of the clock.
 *
 * The facts are read from the tags already on the device (how many of their tracks you own,
 * which years, the dominant genre, bit-perfect rips, favourites), so every line is true for
 * this collection. The fact is keyed on the track id, which means a new song usually lands
 * on a different angle, so the kicker keeps moving as the queue plays.
 */
data class ArtistKicker(
    val artist: String,
    val fact: String,
) {
    /** Uppercase monospace line for the kicker row, e.g. "RADIOHEAD · 3 ALBUMS ON YOUR SHELF". */
    val line: String
        get() = "${artist.uppercase(Locale.ROOT)} · $fact"
}

private val LosslessFormats = setOf("flac", "alac", "wav", "aiff", "aif", "ape", "dsf", "dff")

/**
 * Builds the kicker for the current [artist] and [trackId], or null when the artist is
 * blank or nothing by them is in [library] (the caller then keeps the time-of-day kicker).
 */
fun artistKicker(
    artist: String,
    trackId: String,
    library: List<Track>,
): ArtistKicker? {
    val name = artist.trim()
    if (name.isBlank()) return null

    val owned = library.filter { it.artist.equals(name, ignoreCase = true) }
    if (owned.isEmpty()) return null

    // Prefer the spelling from the tags so the kicker matches the rest of the app.
    val displayName = owned.first().artist.trim().ifBlank { name }

    val facts = buildList {
        add(if (owned.size == 1) "1 TRACK ON YOUR SHELF" else "${owned.size} TRACKS ON YOUR SHELF")

        val albums = owned.map { it.albumId ?: it.album }.filter { it.isNotBlank() }.distinct()
        if (albums.size > 1) add("${albums.size} ALBUMS ON YOUR SHELF")

        val years = owned.mapNotNull { it.year }.filter { it > 1900 }.distinct().sorted()
        when {
            years.size > 1 -> add("ON SHELF ${years.first()}–${years.last()}")
            years.size == 1 -> add("FIRST PRESSED ${years.first()}")
        }

        owned.mapNotNull { it.genre?.trim()?.takeIf(String::isNotBlank) }
            .groupingBy { it.uppercase(Locale.ROOT) }
            .eachCount()
            .maxByOrNull { it.value }
            ?.key
            ?.let { add("USUALLY $it") }

        val favourites = owned.count { it.isFavorite }
        if (favourites > 0) add("$favourites FAVOURITED")

        val formats = owned.mapNotNull {
            it.format?.trim()?.takeIf(String::isNotBlank)?.lowercase(Locale.ROOT)
        }.distinct()
        if (formats.isNotEmpty() && formats.all { it in LosslessFormats }) add("LOSSLESS SHELF")

        if (owned.size > 1) {
            val average = owned.map { it.durationMs }.filter { it > 0 }.average()
            if (average > 30_000) add("AVG ${clock(average.toLong())} A TRACK")
        }
    }

    val seed = (trackId + displayName).hashCode().toLong().absoluteValue
    return ArtistKicker(displayName, facts[(seed % facts.size).toInt()])
}

private fun clock(ms: Long): String {
    val seconds = (ms / 1000).coerceAtLeast(0)
    return "${seconds / 60}:${(seconds % 60).toString().padStart(2, '0')}"
}
