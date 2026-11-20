package com.androidharness.musicplayer.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.androidharness.musicplayer.data.local.entity.TrackEntity
import com.androidharness.musicplayer.data.local.preserveLocalTrackState
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {

    @Query("SELECT * FROM tracks ORDER BY title COLLATE NOCASE")
    fun observeAll(): Flow<List<TrackEntity>>

    @Query("SELECT * FROM tracks WHERE localUri IS NOT NULL")
    suspend fun byLocalUriIsNotNull(): List<TrackEntity>

    @Query("SELECT * FROM tracks WHERE localUri = :uri LIMIT 1")
    suspend fun byLocalUri(uri: String): TrackEntity?

    @Query("SELECT * FROM tracks WHERE id = :id LIMIT 1")
    fun observeById(id: String): Flow<TrackEntity?>

    @Query("SELECT * FROM tracks WHERE id = :id LIMIT 1")
    suspend fun byId(id: String): TrackEntity?

    @Query("SELECT * FROM tracks WHERE id IN (:ids)")
    suspend fun byIds(ids: List<String>): List<TrackEntity>

    /**
     * ftsQuery must already be sanitized into a valid FTS4 expression, for example
     * `"dark"* "side"*`. Wildcards and quoting are applied by the repository.
     */
    @Query(
        """
        SELECT t.* FROM tracks t
        JOIN tracks_fts fts ON t.rowid = fts.rowid
        WHERE tracks_fts MATCH :ftsQuery
        ORDER BY t.title COLLATE NOCASE
        LIMIT :limit
        """
    )
    fun search(ftsQuery: String, limit: Int = 200): Flow<List<TrackEntity>>

    @Upsert
    suspend fun upsertAll(tracks: List<TrackEntity>)

    @Transaction
    suspend fun upsertSynced(tracks: List<TrackEntity>) {
        tracks.chunked(500).forEach { chunk ->
            val existing = byIds(chunk.map { it.id }).associateBy { it.id }
            upsertAll(chunk.map { preserveLocalTrackState(it, existing[it.id]) })
        }
    }

    @Query("UPDATE tracks SET rating = :rating WHERE id = :id")
    suspend fun setRating(id: String, rating: Int)

    @Query("UPDATE tracks SET isFavorite = :favorite WHERE id = :id")
    suspend fun setFavorite(id: String, favorite: Boolean)

    @Query("UPDATE tracks SET isDownloaded = 1, localUri = :localUri WHERE id = :id")
    suspend fun markDownloaded(id: String, localUri: String)

    @Query("UPDATE tracks SET isDownloaded = 0, localUri = NULL WHERE id = :id")
    suspend fun markNotDownloaded(id: String)
}
