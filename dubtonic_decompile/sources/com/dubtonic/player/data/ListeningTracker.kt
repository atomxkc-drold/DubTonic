package com.dubtonic.player.data

import android.content.Context
import androidx.datastore.preferences.core.PreferencesKeys
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dubtonic.player.data.model.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.StateFlowKt
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonDecodeException
import kotlinx.serialization.json.decodeFromString
import kotlinx.serialization.json.encodeToString
import kotlinx.serialization.json.json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.serializersModule
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.DelayKt
import java.io.File
import java.util.concurrent.ConcurrentHashMap

/**
 * Listening Analytics & Insights Engine — completely original feature for DubTonic.
 *
 * Tracks every play event (song, artist, album, timestamp, duration, position)
 * and exposes rich analytics: total listening time, play counts, top artists/songs/albums,
 * listening streaks, day-part analysis, and weekly patterns.
 *
 * Persists to DataStore Preferences (matching the existing SettingsStore pattern)
 * and exposes a StateFlow so UI can reactively observe analytics changes.
 */
class ListeningTracker private constructor(
    context: Context,
    private val json: Json,
    private val scope: CoroutineScope
) {
    private val dataStore = SettingsStoreKt.getDataStore(context)
    private val _analytics = MutableStateFlow(ListeningAnalytics.empty())
    val analytics: StateFlow<ListeningAnalytics> = _analytics.asStateFlow()

    init {
        scope.launch {
            val stored = dataStore.data.first()
                .map { prefs ->
                    prefs[LISTENING_DATA] ?: ""
                }
            val parsed = try {
                json.decodeFromString(ListeningRecord.serializer(), stored)
            } catch (_: Exception) {
                emptyList()
            }
            _analytics.value = buildAnalytics(parsed)
        }
    }

    /** Record a completed (or significantly progressed) play of a song. */
    suspend fun trackPlay(
        song: Song,
        artistName: String?,
        albumName: String?,
        durationMs: Long,
        playedMs: Long
    ) {
        val record = ListeningRecord(
            songId = song.id,
            songTitle = song.title ?: "",
            artist = artistName ?: "",
            album = albumName ?: "",
            playedAtMs = System.currentTimeMillis(),
            durationMs = durationMs,
            playedMs = playedMs
        )
        val current = _analytics.value.records
        val updated = (current + record).takeLast(5000) // cap history
        _analytics.value = buildAnalytics(updated)
        scope.launch {
            dataStore.updateData { prefs ->
                prefs[LISTENING_DATA] = json.encodeToString(ListeningRecord.serializer(), updated)
                prefs
            }
        }
    }

    /** Record the current track being played (for "now playing" analytics). */
    fun setNowPlaying(songId: String?, songTitle: String?, artist: String?, album: String?) {
        _analytics.value = _analytics.value.copy(nowPlayingSongId = songId,
            nowPlayingTitle = songTitle,
            nowPlayingArtist = artist,
            nowPlayingAlbum = album)
    }

    fun clearHistory() {
        _analytics.value = ListeningAnalytics.empty()
        scope.launch {
            dataStore.updateData { prefs ->
                prefs[LISTENING_DATA] = ""
                prefs
            }
        }
    }

    private fun buildAnalytics(records: List<ListeningRecord>): ListeningAnalytics {
        val totalMs = records.sumOf { it.playedMs }
        val totalTimeMinutes = (totalMs / 60000.0)
        val playCountBySong = records.groupBy({ it.songId }, { it })
        val playCountByArtist = records.groupBy({ it.artist }, { it })
        val playCountByAlbum = records.groupBy({ it.album }, { it })

        val topSongs = playCountBySong.entries
            .sortedByDescending { it.value.size }
            .take(10)
            .map { TopItem(it.key, it.value.first().songTitle, it.value.size, it.value.sumOf { it.playedMs }) }

        val topArtists = playCountByArtist.entries
            .sortedByDescending { it.value.size }
            .take(10)
            .map { TopItem(it.key, it.key, it.value.size, it.value.sumOf { it.playedMs }) }

        val topAlbums = playCountByAlbum.entries
            .sortedByDescending { it.value.size }
            .take(10)
            .map { TopItem(it.key, it.key, it.value.size, it.value.sumOf { it.playedMs }) }

        val dayPart = dayPartDistribution(records)
        val weekday = weekdayDistribution(records)
        val streak = currentStreak(records)

        return ListeningAnalytics(
            records = records,
            totalPlayTimeMinutes = totalTimeMinutes,
            totalPlayCount = records.size,
            topSongs = topSongs,
            topArtists = topArtists,
            topAlbums = topAlbums,
            dayPartDistribution = dayPart,
            weekdayDistribution = weekday,
            currentStreakDays = streak,
            lastPlayedAtMs = records.lastOrNull()?.playedAtMs
        )
    }

    private fun dayPartDistribution(records: List<ListeningRecord>): Map<String, Int> {
        val buckets = mutableMapOf<String, Int>()
        for (r in records) {
            val hour = java.util.Calendar.getInstance().apply { timeInMillis = r.playedAtMs }
                .get(java.util.Calendar.HOUR_OF_DAY)
            val part = when {
                hour in 5 until 12 -> "Morning"
                hour in 12 until 17 -> "Afternoon"
                hour in 17 until 21 -> "Evening"
                else -> "Night"
            }
            buckets[part] = (buckets[part] ?: 0) + 1
        }
        return buckets
    }

    private fun weekdayDistribution(records: List<ListeningRecord>): Map<String, Int> {
        val buckets = mutableMapOf<String, Int>()
        for (r in records) {
            val cal = java.util.Calendar.getInstance().apply { timeInMillis = r.playedAtMs }
            val day = when (cal.get(java.util.Calendar.DAY_OF_WEEK)) {
                java.util.Calendar.SUNDAY -> "Sunday"
                java.util.Calendar.MONDAY -> "Monday"
                java.util.Calendar.TUESDAY -> "Tuesday"
                java.util.Calendar.WEDNESDAY -> "Wednesday"
                java.util.Calendar.THURSDAY -> "Thursday"
                java.util.Calendar.FRIDAY -> "Friday"
                java.util.Calendar.SATURDAY -> "Saturday"
                else -> "Unknown"
            }
            buckets[day] = (buckets[day] ?: 0) + 1
        }
        return buckets
    }

    private fun currentStreak(records: List<ListeningRecord>): Int {
        if (records.isEmpty()) return 0
        val sorted = records.sortedByDescending { it.playedAtMs }
        val today = java.util.Calendar.getInstance()
        today.set(java.util.Calendar.HOUR_OF_DAY, 0)
        today.set(java.util.Calendar.MINUTE, 0)
        today.set(java.util.Calendar.SECOND, 0)
        today.set(java.util.Calendar.MILLISECOND, 0)
        var streak = 0
        var cursor = today.timeInMillis
        val recordByDay = sorted.groupBy {
            java.util.Calendar.getInstance().apply { timeInMillis = it.playedAtMs }
                .set(java.util.Calendar.HOUR_OF_DAY, 0)
                .set(java.util.Calendar.MINUTE, 0)
                .set(java.util.Calendar.SECOND, 0)
                .set(java.util.Calendar.MILLISECOND, 0)
                .timeInMillis
        }
        while (recordByDay.containsKey(cursor)) {
            streak++
            cursor -= 86400000 // one day in ms
        }
        return streak
    }

    companion object {
        private const val PREF_NAME = "listening_data"
        private val LISTENING_DATA = stringPreferencesKey(PREF_NAME)

        fun create(context: Context): ListeningTracker {
            val module = serializersModule {
                contextual(ListeningRecord::class, ListeningRecord.serializer())
            }
            val json = Json { serializersModule = module }
            val scope = CoroutineScope(
                kotlinx.coroutines.SupervisorKt.SupervisorJob().plus(Dispatchers.getIO())
            )
            return ListeningTracker(context, json, scope)
        }
    }
}

/** A single play event. */
@Serializable
data class ListeningRecord(
    val songId: String,
    val songTitle: String,
    val artist: String,
    val album: String,
    val playedAtMs: Long,
    val durationMs: Long,
    val playedMs: Long
)

/** Aggregated analytics snapshot exposed to the UI. */
data class ListeningAnalytics(
    val records: List<ListeningRecord>,
    val totalPlayTimeMinutes: Double,
    val totalPlayCount: Int,
    val topSongs: List<TopItem>,
    val topArtists: List<TopItem>,
    val topAlbums: List<TopItem>,
    val dayPartDistribution: Map<String, Int>,
    val weekdayDistribution: Map<String, Int>,
    val currentStreakDays: Int,
    val lastPlayedAtMs: Long?,
    val nowPlayingSongId: String? = null,
    val nowPlayingTitle: String? = null,
    val nowPlayingArtist: String? = null,
    val nowPlayingAlbum: String? = null
) {
    companion object {
        fun empty() = ListeningAnalytics(
            records = emptyList(),
            totalPlayTimeMinutes = 0.0,
            totalPlayCount = 0,
            topSongs = emptyList(),
            topArtists = emptyList(),
            topAlbums = emptyList(),
            dayPartDistribution = emptyMap(),
            weekdayDistribution = emptyMap(),
            currentStreakDays = 0,
            lastPlayedAtMs = null
        )
    }
}

/** A ranked top item (song / artist / album). */
data class TopItem(
    val id: String,
    val name: String,
    val playCount: Int,
    val totalPlayedMs: Long
) {
    val totalPlayedMinutes: Double get() = totalPlayedMs / 60000.0
}

// Extension so ListeningTracker can be obtained from AppContainer-like contexts.
fun Context.listeningTracker(): ListeningTracker =
    ListeningTracker.create(this)
