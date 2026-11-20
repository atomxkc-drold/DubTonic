package com.androidharness.musicplayer.feature.home

import com.androidharness.musicplayer.core.model.Track
import kotlin.math.abs

/**
 * Sonic Flow builds a "continuous mix" out of the library: instead of shuffling at
 * random it walks to the nearest track each time, using decade, length and genre as
 * coordinates. Consecutive tracks therefore feel adjacent, and the whole queue reads
 * like one long arc instead of a bag of songs.
 *
 * Deterministic: same set of tracks and the same seed always produce the same order.
 */
private const val DEFAULT_FALLBACK_YEAR = 2000
private const val MINUTES_WEIGHT = 1f / 12f
private const val GENRE_PENALTY = 0.5f
private const val DECADE_WEIGHT = 1f

data class SonicPoint(val decade: Float, val minutes: Float)

fun sonicPoint(track: Track, fallbackYear: Int = DEFAULT_FALLBACK_YEAR): SonicPoint {
    val year = track.year ?: fallbackYear
    return SonicPoint(decade = year / 10f, minutes = track.durationMs / 60_000f)
}

private fun sonicDistance(a: Track, b: Track, fallbackYear: Int): Float {
    val pa = sonicPoint(a, fallbackYear)
    val pb = sonicPoint(b, fallbackYear)
    val decade = abs(pa.decade - pb.decade) * DECADE_WEIGHT
    val length = abs(pa.minutes - pb.minutes) * MINUTES_WEIGHT
    val genre = if (normalizeGenre(a.genre) == normalizeGenre(b.genre)) 0f else GENRE_PENALTY
    return decade + length + genre
}

internal fun normalizeGenre(genre: String?): String =
    genre.orEmpty().split(';', ',', '/', '|')
        .firstOrNull()
        .orEmpty()
        .trim()
        .lowercase()

fun fallbackYear(tracks: List<Track>): Int {
    val years = tracks.mapNotNull { it.year }.filter { it in 1880 until 2100 }.sorted()
    return if (years.isEmpty()) DEFAULT_FALLBACK_YEAR else years[years.size / 2]
}

fun totalDistance(tracks: List<Track>): Float {
    if (tracks.size < 2) return 0f
    val fallback = fallbackYear(tracks)
    return tracks.zipWithNext { a, b -> sonicDistance(a, b, fallback) }.sum()
}

/**
 * @param seedId track the arc should start from; unknown id or null starts from the
 *   lowest id so the result never depends on input order.
 * @param maxTracks hard cap on the resulting queue.
 */
fun buildSonicFlow(
    tracks: List<Track>,
    seedId: String? = null,
    maxTracks: Int = 40,
): List<Track> {
    if (tracks.isEmpty() || maxTracks <= 0) return emptyList()
    val fallback = fallbackYear(tracks)

    val remaining = tracks.distinctBy { it.id }.sortedBy { it.id }.toMutableList()
    val start = remaining.firstOrNull { it.id == seedId }
        ?: remaining.minByOrNull { it.id }
        ?: return emptyList()

    val flow = ArrayList<Track>(minOf(remaining.size, maxTracks))
    flow += start
    remaining.remove(start)

    while (remaining.isNotEmpty() && flow.size < maxTracks) {
        val current = flow.last()
        val next = remaining.minWithOrNull(
            compareBy<Track> { sonicDistance(current, it, fallback) }.thenBy { it.id }
        ) ?: break
        flow += next
        remaining.remove(next)
    }
    return flow
}

/** Average neighbor distance; used to prove the arc is smoother than input order. */
fun averageStepDistance(tracks: List<Track>): Float =
    if (tracks.size < 2) 0f else totalDistance(tracks) / (tracks.size - 1)

fun formatListenTime(durationMs: Long): String {
    if (durationMs <= 0L) return "0 min"
    val totalMinutes = durationMs / 60_000L
    if (totalMinutes < 1L) return "${(durationMs / 1000L).coerceAtLeast(1L)}s"
    if (totalMinutes < 60L) return "$totalMinutes min"
    val hours = totalMinutes / 60L
    val minutes = totalMinutes % 60L
    return if (minutes == 0L) "${hours}h" else "${hours}h ${minutes}m"
}
