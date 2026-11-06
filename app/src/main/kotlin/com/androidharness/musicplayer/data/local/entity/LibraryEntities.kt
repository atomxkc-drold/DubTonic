package com.androidharness.musicplayer.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "albums",
    indices = [Index(value = ["id"], unique = true), Index(value = ["artistId"])],
)
data class AlbumEntity(
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    val rowId: Long,
    val id: String,
    val name: String,
    val artist: String,
    val artistId: String?,
    val coverArtId: String?,
    val songCount: Int,
    val durationMs: Long,
    val year: Int?,
    val addedAtMs: Long = 0,
    val lastSyncedAtMs: Long = 0,
    val isFavorite: Boolean = false,
)

@Entity(
    tableName = "artists",
    indices = [Index(value = ["id"], unique = true)],
)
data class ArtistEntity(
    @PrimaryKey
    @ColumnInfo(name = "rowid")
    val rowId: Long,
    val id: String,
    val name: String,
    val albumCount: Int,
    val coverArtId: String?,
    val isFavorite: Boolean = false,
)
