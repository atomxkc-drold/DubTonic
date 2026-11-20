package com.androidharness.musicplayer.feature.home

import com.androidharness.musicplayer.core.model.Track

/**
 * The Era Dial: the library folded into decades laid out as one ring.
 * Pure maths only, so the hit test and the layout can be unit tested.
 */
data class EraBucket(
    /** 0 marks undated tracks. */
    val startYear: Int,
    val label: String,
    val count: Int,
    val share: Float,
    val startDegrees: Float,
    val sweepDegrees: Float,
) {
    val endDegrees: Float get() = startDegrees + sweepDegrees
    val isUndated: Boolean get() = startYear == UNDATED_ERA
}

const val UNDATED_ERA: Int = 0
private const val MIN_YEAR = 1880
private const val MAX_YEAR = 2100

fun decadeOf(year: Int?): Int? {
    val value = year ?: return null
    if (value < MIN_YEAR || value >= MAX_YEAR) return null
    return value / 10 * 10
}

fun eraLabel(decade: Int): String =
    if (decade == UNDATED_ERA) "Undated" else "${decade}s"

fun eraBuckets(tracks: List<Track>, includeUndated: Boolean = false): List<EraBucket> {
    if (tracks.isEmpty()) return emptyList()

    val groups = LinkedHashMap<Int, Int>()
    tracks.forEach { track ->
        val decade = decadeOf(track.year)
        if (decade == null && !includeUndated) return@forEach
        val key = decade ?: UNDATED_ERA
        groups[key] = (groups[key] ?: 0) + 1
    }
    if (groups.isEmpty()) return emptyList()

    val total = groups.values.sum().toFloat()
    var cursor = 0f
    return groups.entries
        .sortedBy { it.key }
        .map { (decade, count) ->
            val share = count / total
            val sweep = share * 360f
            val bucket = EraBucket(
                startYear = decade,
                label = eraLabel(decade),
                count = count,
                share = share,
                startDegrees = cursor,
                sweepDegrees = sweep,
            )
            cursor += sweep
            bucket
        }
}

/** Which slice sits under the given angle. Any input angle is wrapped into one turn. */
fun eraAtAngle(buckets: List<EraBucket>, degrees: Float): EraBucket? {
    if (buckets.isEmpty()) return null
    val normalized = ((degrees % 360f) + 360f) % 360f
    buckets.forEach { bucket ->
        if (normalized >= bucket.startDegrees && normalized < bucket.endDegrees) return bucket
    }
    // A full turn can end a hair under 360 because of float error.
    return buckets.lastOrNull()?.takeIf { normalized >= it.startDegrees }
}

fun tracksInEra(tracks: List<Track>, bucket: EraBucket): List<Track> =
    if (bucket.isUndated) {
        tracks.filter { decadeOf(it.year) == null }
    } else {
        tracks.filter { decadeOf(it.year) == bucket.startYear }
    }
