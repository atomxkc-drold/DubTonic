package com.dubtonic.player.data

import com.dubtonic.player.data.model.Song
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.random.Random

/**
 * MoodMix — Mood-Based Smart Playlist Generator — completely original feature for DubTonic.
 *
 * Since Subsonic does not expose BPM/energy/audio-features, MoodMix derives a "mood score"
 * from available metadata: song duration, release year, play count, title keywords, and
 * listening history recency. It then builds a coherent playlist for a chosen mood:
 *
 *   Chill        — longer, slower, older, mellow tracks
 *   Energetic    — shorter, upbeat, recent, frequently-played tracks
 *   Focus        — instrumental-friendly (no lyrics hints), steady-length tracks
 *   Party        — high play count, short-to-medium, recent hits
 *   Romantic     — mid-length, classic, title/artist keyword matching
 *   Workout      — short, high-tempo-feeling, energetic keywords
 *   Sunset       — golden-hour vibe: 1970s-1990s, mid-length, warm keywords
 *
 * Returns an ordered list of Song recommendations ready to be turned into a Playlist.
 */
class MoodMixGenerator private constructor(
    private val listeningHistory: List<ListeningRecord>
) {
    private val recentSongIds: Set<String> =
        listeningHistory.takeLast(200).map { it.songId }.toSet()
    private val playCountById = listeningHistory.groupingBy { it.songId }.eachCount()

    fun generate(mood: Mood, candidates: List<Song>, limit: Int = 30): List<MoodMixTrack> {
        val scored = candidates.map { song ->
            val score = scoreSong(song, mood)
            MoodMixTrack(song, score)
        }.sortedByDescending { it.score }

        return scored.take(limit)
    }

    private fun scoreSong(song: Song, mood: Mood): Float {
        var score = 0.5f // neutral baseline

        val title = (song.title ?: "").lowercase()
        val artist = (song.artist ?: "").lowercase()
        val album = (song.album ?: "").lowercase()
        val durationMs = song.duration
        val year = song.year.toInt()
        val playCount = playCountById[song.id] ?: 0
        val isRecent = recentSongIds.contains(song.id)

        when (mood) {
            Mood.Chill -> {
                // Longer tracks (5-9 min), older, mellow keywords
                score += durationScore(durationMs, 300000, 600000)
                score += yearScore(year, 1970, 2005)
                score += keywordMatch(title + " " + artist, chillKeywords) * 0.15f
                score -= keywordMatch(title, energeticKeywords) * 0.1f
                if (playCount > 0) score += 0.05f // familiar but not overplayed
            }
            Mood.Energetic -> {
                // Shorter, upbeat, recent, high play count
                score += durationScore(durationMs, 150000, 300000)
                score += yearScore(year, 2005, 2025)
                score += keywordMatch(title + " " + artist, energeticKeywords) * 0.2f
                score += (playCount.coerceAtMost(50) / 50f) * 0.1f
                if (isRecent) score += 0.1f
            }
            Mood.Focus -> {
                // Steady-length, instrumental-friendly, no "live"/"remix" hints
                score += durationScore(durationMs, 200000, 400000)
                score -= keywordMatch(title + " " + artist, liveRemixKeywords) * 0.2f
                score -= keywordMatch(title, vocalHeavyKeywords) * 0.05f
                if (playCount > 0) score += 0.05f
            }
            Mood.Party -> {
                // High play count, short-medium, recent hits
                score += durationScore(durationMs, 180000, 320000)
                score += yearScore(year, 2010, 2025)
                score += (playCount.coerceAtMost(100) / 100f) * 0.2f
                score += keywordMatch(title + " " + artist, partyKeywords) * 0.1f
                if (isRecent) score += 0.1f
            }
            Mood.Romantic -> {
                // Mid-length, classic, romantic keywords
                score += durationScore(durationMs, 240000, 420000)
                score += yearScore(year, 1980, 2010)
                score += keywordMatch(title + " " + artist, romanticKeywords) * 0.2f
            }
            Mood.Workout -> {
                // Short, energetic, driving
                score += durationScore(durationMs, 120000, 240000)
                score += keywordMatch(title + " " + artist, workoutKeywords) * 0.2f
                score += yearScore(year, 2000, 2025)
                score += (playCount.coerceAtMost(30) / 30f) * 0.05f
            }
            Mood.Sunset -> {
                // 1970s-1990s, mid-length, warm keywords
                score += durationScore(durationMs, 240000, 480000)
                score += yearScore(year, 1970, 1995)
                score += keywordMatch(title + " " + artist, sunsetKeywords) * 0.15f
            }
        }

        // Slight randomness so mixes feel fresh each time
        score += Random.nextFloat() * 0.03f
        return score.coerceIn(0f, 1f)
    }

    private fun durationScore(durationMs: Long, idealMin: Long, idealMax: Long): Float {
        return when {
            durationMs in idealMin..idealMax -> 0.2f
            durationMs < idealMin -> (durationMs.toFloat() / idealMin) * 0.2f
            else -> max(0f, 0.2f - ((durationMs - idealMax) / 300000f) * 0.2f)
        }
    }

    private fun yearScore(year: Int?, idealMin: Int, idealMax: Int): Float {
        val y = year ?: 2000
        return when {
            y in idealMin..idealMax -> 0.15f
            y < idealMin -> max(0f, 0.15f - ((idealMin - y) / 50f) * 0.15f)
            else -> max(0f, 0.15f - ((y - idealMax) / 50f) * 0.15f)
        }
    }

    private fun keywordMatch(text: String, keywords: List<String>): Float {
        var hits = 0
        for (kw in keywords) {
            if (text.contains(kw)) hits++
        }
        return (hits.coerceAtMost(3) / 3f)
    }

    private fun max(a: Float, b: Float) = if (a > b) a else b

    private val chillKeywords = listOf("chill", "ambient", "relax", "calm", "soft", "slow", "dream", "space")
    private val energeticKeywords = listOf("energy", "power", "up", "fast", "break", "drop", "loud", "hype")
    private val partyKeywords = listOf("party", "dance", "club", "celebrate", "night", "disco", "funk")
    private val romanticKeywords = listOf("love", "heart", "baby", "forever", "always", "kiss", "darling")
    private val workoutKeywords = listOf("workout", "gym", "run", "push", "go", "strong", "beast")
    private val sunsetKeywords = listOf("sunset", "golden", "evening", "summer", "warm", "sky")
    private val liveRemixKeywords = listOf("live", "remix", "edit", "version", "radio")
    private val vocalHeavyKeywords = listOf("acapella", "choir", "opera")

    companion object {
        fun create(listeningHistory: List<ListeningRecord>): MoodMixGenerator =
            MoodMixGenerator(listeningHistory)
    }
}

enum class Mood {
    Chill, Energetic, Focus, Party, Romantic, Workout, Sunset
}

data class MoodMixTrack(
    val song: com.dubtonic.player.data.model.Song,
    val score: Float,
    val reason: String = ""
) {
    val matchPercent: Int get() = (score * 100).roundToInt()
}
