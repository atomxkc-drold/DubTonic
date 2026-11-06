package com.androidharness.musicplayer.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.FtsOptions
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tracks",
    indices = [
        Index(value = ["id"], unique = true),
        Index(value = ["albumId"]),
        Index(value = ["artistId"]),
        Index(value = ["title"]),
        Index(value = ["isFavorite"]),
    ],
)
data class TrackEntity(
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    val rowId: Long,
    val id: String,
    val title: String,
    val artist: String,
    val album: String,
    val albumId: String?,
    val artistId: String?,
    val durationMs: Long,
    val trackNumber: Int?,
    val discNumber: Int?,
    val year: Int?,
    val genre: String?,
    val bitrateKbps: Int?,
    val sampleRateHz: Int?,
    val channels: Int?,
    val format: String?,
    val sizeBytes: Long?,
    val coverArtId: String?,
    val isFavorite: Boolean = false,
    val isDownloaded: Boolean = false,
    val localUri: String? = null,
    val replayGainTrackDb: Float? = null,
    val replayGainAlbumDb: Float? = null,
    val addedAtMs: Long = 0,
    val lastSyncedAtMs: Long = 0,
)

/**
 * FTS4 content table. Room keeps this table in sync with [TrackEntity] through triggers.
 * rowid must match the content entity primary key.
 */
@Fts4(
    contentEntity = TrackEntity::class,
    tokenizer = FtsOptions.TOKENIZER_UNICODE61,
)
@Entity(tableName = "tracks_fts")
data class TrackFtsEntity(
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    val rowId: Long,
    val title: String,
    val artist: String,
    val album: String,
)
