package com.androidharness.musicplayer.feature.home

import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.data.settings.TimeMachineLens
import com.androidharness.musicplayer.data.settings.UndatedTrackMode

/**
 * The Era Dial: the library folded into slices laid out as one ring.
 * Pure maths only, so the hit test and the layout can be unit tested.
 */
data class EraBucket(
    /** Stable identity of the slice; the ring, the art and the selection all key on this. */
    val id: String,
    /** 0 marks undated tracks. Non-year lenses use [NON_YEAR_KEY]. */
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

/** Buckets that are not years (genre, play tiers) carry this so they are never "undated". */
const val NON_YEAR_KEY: Int = -1

private const val MIN_YEAR = 1880
private const val MAX_YEAR = 2100

/** Play-count tiers used by the User Plays and Global Streams lenses. */
private val PLAY_TIERS: List<Pair<String, (Int) -> Boolean>> = listOf(
    "100+" to { plays: Int -> plays >= 100 },
    "50-99" to { plays: Int -> plays in 50..99 },
    "20-49" to { plays: Int -> plays in 20..49 },
    "10-19" to { plays: Int -> plays in 10..19 },
    "1-9" to { plays: Int -> plays in 1..9 },
    "Never" to { plays: Int -> plays <= 0 },
)

fun decadeOf(year: Int?): Int? {
    val value = year ?: return null
    if (value < MIN_YEAR || value >= MAX_YEAR) return null
    return value / 10 * 10
}

fun eraLabel(decade: Int): String =
    if (decade == UNDATED_ERA) "Undated" else "${decade}s"

/**
 * Which slices the dial shows for [lens].
 *
 * Undated tracks are governed by [undatedMode] in every lens, so the "??" pile can be hidden,
 * included as one slice, or inspected on its own instead of swamping the ring.
 *
 * [globalStreams] is the locally cached play-count map used by [TimeMachineLens.GLOBAL_STREAMS].
 * Nothing here performs a lookup: the lens is opt-in and reads the cache only, so offline use is
 * unaffected and no request is ever triggered by opening the dial.
 */
fun eraBuckets(
    tracks: List<Track>,
    lens: TimeMachineLens = TimeMachineLens.SONG_YEAR,
    undatedMode: UndatedTrackMode = UndatedTrackMode.HIDDEN,
    localPlays: Map<String, Int> = emptyMap(),
    globalStreams: Map<String, Int> = emptyMap(),
): List<EraBucket> {
    if (tracks.isEmpty()) return emptyList()

    val groups = LinkedHashMap<String, Int>()

    tracks.forEach { track ->
        val key = groupKey(track, lens, localPlays, globalStreams)
        if (key == null) {
            if (undatedMode != UndatedTrackMode.HIDDEN) {
                groups.merge(UNDATED_KEY, 1, Int::plus)
            }
            return@forEach
        }
        if (undatedMode == UndatedTrackMode.ONLY) return@forEach
        groups.merge(key, 1, Int::plus)
    }

    if (groups.isEmpty()) return emptyList()

    val total = groups.values.sum().toFloat()
    var cursor = 0f
    val ordered = groups.entries
        .sortedWith(compareBy<MutableMap.MutableEntry<String, Int>> { sortRank(it.key, lens) }
            .thenByDescending { it.value })

    return ordered.map { (key, count) ->
        val share = count / total
        val sweep = share * 360f
        val bucket = EraBucket(
            id = key,
            startYear = yearKeyOf(key, lens),
            label = labelOf(key, lens),
            count = count,
            share = share,
            startDegrees = cursor,
            sweepDegrees = sweep,
        )
        cursor += sweep
        bucket
    }
}

private const val UNDATED_KEY = "era:undated"

private fun groupKey(
    track: Track,
    lens: TimeMachineLens,
    localPlays: Map<String, Int>,
    globalStreams: Map<String, Int>,
): String? = when (lens) {
    TimeMachineLens.SONG_YEAR -> decadeOf(track.year)?.let { "year:$it" }

    TimeMachineLens.DATE_ADDED -> addedYearOf(track.addedAtMs)?.let { "added:$it" }

    TimeMachineLens.GENRE -> track.genre?.trim()?.takeIf { it.isNotBlank() }?.let { "genre:$it" }

    TimeMachineLens.USER_PLAYS -> playTierKey(localPlays[track.id] ?: 0)

    TimeMachineLens.GLOBAL_STREAMS -> playTierKey(globalStreams[track.id] ?: track.serverPlayCount)
}

private fun playTierKey(plays: Int): String =
    PLAY_TIERS.firstOrNull { (_, matches) -> matches(plays) }?.first ?: "Never"

private fun yearKeyOf(key: String, lens: TimeMachineLens): Int = when (lens) {
    TimeMachineLens.SONG_YEAR -> key.removePrefix("year:").toIntOrNull() ?: UNDATED_ERA
    TimeMachineLens.DATE_ADDED -> key.removePrefix("added:").toIntOrNull() ?: UNDATED_ERA
    else -> if (key == UNDATED_KEY) UNDATED_ERA else NON_YEAR_KEY
}

private fun labelOf(key: String, lens: TimeMachineLens): String = when (lens) {
    TimeMachineLens.SONG_YEAR -> eraLabel(key.removePrefix("year:").toIntOrNull() ?: UNDATED_ERA)
    TimeMachineLens.DATE_ADDED -> key.removePrefix("added:").toIntOrNull()?.let { "$it" }
        ?: eraLabel(UNDATED_ERA)
    TimeMachineLens.GENRE -> key.removePrefix("genre:")
    TimeMachineLens.USER_PLAYS, TimeMachineLens.GLOBAL_STREAMS ->
        if (key == UNDATED_KEY) "Undated" else key
}

private fun sortRank(key: String, lens: TimeMachineLens): Int = when (lens) {
    TimeMachineLens.SONG_YEAR -> key.removePrefix("year:").toIntOrNull() ?: Int.MAX_VALUE
    TimeMachineLens.DATE_ADDED -> key.removePrefix("added:").toIntOrNull() ?: Int.MAX_VALUE
    TimeMachineLens.GENRE -> if (key == UNDATED_KEY) Int.MAX_VALUE else 0
    TimeMachineLens.USER_PLAYS, TimeMachineLens.GLOBAL_STREAMS ->
        PLAY_TIERS.indexOfFirst { (name, _) -> name == key }.let { if (it < 0) Int.MAX_VALUE else it }
}

/** Local calendar year of an added-at timestamp, or null when it is unknown. */
private fun addedYearOf(addedAtMs: Long): Int? {
    if (addedAtMs <= 0L) return null
    val year = java.util.Calendar.getInstance().apply { timeInMillis = addedAtMs }
        .get(java.util.Calendar.YEAR)
    return if (year in MIN_YEAR..MAX_YEAR) year else null
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

fun tracksInEra(
    tracks: List<Track>,
    bucket: EraBucket,
    lens: TimeMachineLens = TimeMachineLens.SONG_YEAR,
    localPlays: Map<String, Int> = emptyMap(),
    globalStreams: Map<String, Int> = emptyMap(),
): List<Track> = tracks.filter { track ->
    if (bucket.isUndated) {
        groupKey(track, lens, localPlays, globalStreams) == null
    } else {
        groupKey(track, lens, localPlays, globalStreams) == bucket.id
    }
}

/** The tracks the undated slice stands for, for the inspect/filter sheet. */
fun undatedTracks(tracks: List<Track>): List<Track> =
    tracks.filter { decadeOf(it.year) == null }

/** Short chip label for the lens selector. */
val TimeMachineLens.label: String
    get() = when (this) {
        TimeMachineLens.SONG_YEAR -> "Song year"
        TimeMachineLens.DATE_ADDED -> "Date added"
        TimeMachineLens.GENRE -> "Genre"
        TimeMachineLens.USER_PLAYS -> "User plays"
        TimeMachineLens.GLOBAL_STREAMS -> "Streams"
    }
