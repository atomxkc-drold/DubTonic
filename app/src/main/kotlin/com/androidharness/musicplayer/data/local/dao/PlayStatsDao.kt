package com.androidharness.musicplayer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidharness.musicplayer.data.local.entity.PlayStatsEntity
import com.androidharness.musicplayer.data.local.entity.TrackEntity
import kotlinx.coroutines.flow.Flow

/** Play history for the Home shelves: what was played last and what is played most. */
@Dao
interface PlayStatsDao {

    @Query("SELECT * FROM play_stats")
    fun observeAll(): Flow<List<PlayStatsEntity>>

    @Query(
        """
        SELECT t.* FROM tracks t
        INNER JOIN play_stats s ON s.trackId = t.id
        WHERE s.playCount > 0
        ORDER BY s.playCount DESC, s.lastPlayedAtMs DESC
        LIMIT :limit
        """
    )
    fun observeTopPlayed(limit: Int): Flow<List<TrackEntity>>

    @Query(
        """
        SELECT t.* FROM tracks t
        INNER JOIN play_stats s ON s.trackId = t.id
        WHERE s.lastPlayedAtMs > 0
        ORDER BY s.lastPlayedAtMs DESC
        LIMIT :limit
        """
    )
    fun observeRecentlyPlayed(limit: Int): Flow<List<TrackEntity>>

    @Query(
        """
        SELECT t.* FROM tracks t
        LEFT JOIN play_stats s ON s.trackId = t.id
        WHERE s.trackId IS NULL OR s.lastPlayedAtMs = 0
        ORDER BY t.addedAtMs DESC
        LIMIT :limit
        """
    )
    fun observeUnplayed(limit: Int): Flow<List<TrackEntity>>

    /** Returns 1 when the row existed, 0 when the track has no history yet. */
    @Query(
        """
        UPDATE play_stats
        SET playCount = playCount + 1, lastPlayedAtMs = :nowMs
        WHERE trackId = :trackId
        """
    )
    suspend fun bump(trackId: String, nowMs: Long): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(stats: PlayStatsEntity)

    @Query("DELETE FROM play_stats")
    suspend fun clear()

    @Query("DELETE FROM play_stats WHERE trackId NOT IN (SELECT id FROM tracks)")
    suspend fun dropOrphans()
}
