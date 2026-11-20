package com.androidharness.musicplayer.feature.home

import com.androidharness.musicplayer.core.model.Track

/**
 * Vibe Stacks folds the library into a small set of named mixes: a decade crossed
 * with a primary genre ("1990s · Trip hop"). They are derived, not curated, so a fresh
 * sync always yields something to tap. Ranking is stable: biggest stack first, then
 * alphabetical, so the shelf does not jump around between recompositions.
 */
data class VibeStack(
    val id: String,
    val title: String,
    val subtitle: String,
    val accentIndex: Int,
    val coverArtIds: List<String>,
    val trackIds: List<String>,
    val totalDurationMs: Long,
)

private const val ACCENT_COUNT = 5
private const val MAX_COVERS = 3
private const val UNTITLED_GENRE = "Mixed"

fun displayGenre(genre: String?): String {
    val normalized = normalizeGenre(genre)
    if (normalized.isEmpty()) return UNTITLED_GENRE
    return normalized.split(' ', '-')
        .filter { it.isNotEmpty() }
        .joinToString(" ") { word -> word.replaceFirstChar { char -> char.uppercase() } }
}

fun buildVibeStacks(
    tracks: List<Track>,
    limit: Int = 6,
    minSize: Int = 3,
): List<VibeStack> {
    if (tracks.isEmpty() || limit <= 0) return emptyList()

    val groups = LinkedHashMap<Pair<Int, String>, MutableList<Track>>()
    tracks.distinctBy { it.id }.forEach { track ->
        val decade = decadeOf(track.year) ?: UNDATED_ERA
        val key = decade to normalizeGenre(track.genre)
        groups.getOrPut(key) { mutableListOf() } += track
    }

    return groups.entries
        .filter { it.value.size >= minSize.coerceAtLeast(1) }
        .map { (key, members) ->
            val decadeLabel = eraLabel(key.first)
            val genre = if (key.second.isEmpty()) UNTITLED_GENRE else displayGenre(key.second)
            val ordered = members.sortedWith(
                compareBy<Track> { it.year ?: 0 }
                    .thenBy { it.album }
                    .thenBy { it.title }
                    .thenBy { it.id }
            )
            val duration = ordered.sumOf { it.durationMs }
            VibeStack(
                id = "${key.first}-${key.second.ifEmpty { "mixed" }}",
                title = "$decadeLabel · $genre",
                subtitle = "${ordered.size} ${if (ordered.size == 1) "track" else "tracks"}" +
                    " · ${formatListenTime(duration)}",
                accentIndex = 0,
                coverArtIds = ordered.mapNotNull { it.coverArtId }
                    .filter { it.isNotBlank() }
                    .distinct()
                    .take(MAX_COVERS),
                trackIds = ordered.map { it.id },
                totalDurationMs = duration,
            )
        }
        .sortedWith(compareByDescending<VibeStack> { it.trackIds.size }.thenBy { it.title }.thenBy { it.id })
        .take(limit)
        .mapIndexed { index, stack -> stack.copy(accentIndex = index % ACCENT_COUNT) }
}
