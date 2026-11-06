package com.androidharness.musicplayer.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.androidharness.musicplayer.data.local.entity.TrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {

    @Query("SELECT * FROM tracks ORDER BY title COLLATE NOCASE")
    fun observeAll(): Flow<List<TrackEntity>>

    @Query("SELECT COUNT(*) FROM tracks")
    fun observeCount(): Flow<Int>

    @Query("SELECT * FROM tracks WHERE isFavorite = 1 ORDER BY title COLLATE NOCASE")
    fun observeFavorites(): Flow<List<TrackEntity>>

    @Query("SELECT * FROM tracks WHERE isDownloaded = 1")
    suspend fun downloaded(): List<TrackEntity>

    @Query("SELECT * FROM tracks WHERE localUri = :uri LIMIT 1")
    suspend fun byLocalUri(uri: String): TrackEntity?

    @Query("SELECT * FROM tracks WHERE albumId = :albumId ORDER BY discNumber, trackNumber")
    fun observeByAlbum(albumId: String): Flow<List<TrackEntity>>

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

    @Query("DELETE FROM tracks WHERE id IN (:ids)")
    suspend fun deleteByIds(ids: List<String>)

    @Query("DELETE FROM tracks")
    suspend fun deleteAll()

    @Query("UPDATE tracks SET isFavorite = :favorite WHERE id = :id")
    suspend fun setFavorite(id: String, favorite: Boolean)

    @Query("UPDATE tracks SET isDownloaded = 1, localUri = :localUri WHERE id = :id")
    suspend fun markDownloaded(id: String, localUri: String)

    @Query("UPDATE tracks SET isDownloaded = 0, localUri = NULL WHERE id = :id")
    suspend fun markNotDownloaded(id: String)

    @Query(
        """
        UPDATE tracks
        SET replayGainTrackDb = :trackDb, replayGainAlbumDb = :albumDb
        WHERE id = :id
        """
    )
    suspend fun setReplayGain(id: String, trackDb: Float?, albumDb: Float?)
}
