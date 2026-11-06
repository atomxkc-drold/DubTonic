package com.androidharness.musicplayer.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.androidharness.musicplayer.data.local.entity.PlaylistEntity
import com.androidharness.musicplayer.data.local.entity.PlaylistEntryEntity
import com.androidharness.musicplayer.data.local.entity.TrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {

    @Query("SELECT * FROM playlists ORDER BY name COLLATE NOCASE")
    fun observeAll(): Flow<List<PlaylistEntity>>

    @Upsert
    suspend fun upsert(playlist: PlaylistEntity)

    @Upsert
    suspend fun upsertAll(playlists: List<PlaylistEntity>)

    @Query("DELETE FROM playlists WHERE id = :playlistId")
    suspend fun delete(playlistId: String)

    @Query("DELETE FROM playlist_entries WHERE playlistId = :playlistId")
    suspend fun clearEntries(playlistId: String)

    @Upsert
    suspend fun upsertEntries(entries: List<PlaylistEntryEntity>)

    @Query(
        """
        SELECT t.* FROM tracks t
        JOIN playlist_entries e ON e.trackId = t.id
        WHERE e.playlistId = :playlistId
        ORDER BY e.position
        """
    )
    fun observeTracks(playlistId: String): Flow<List<TrackEntity>>

    @Transaction
    suspend fun replaceEntries(playlistId: String, trackIds: List<String>) {
        clearEntries(playlistId)
        val now = System.currentTimeMillis()
        upsertEntries(
            trackIds.mapIndexed { index, trackId ->
                PlaylistEntryEntity(
                    playlistId = playlistId,
                    trackId = trackId,
                    position = index,
                    addedAtMs = now,
                )
            }
        )
    }
}
