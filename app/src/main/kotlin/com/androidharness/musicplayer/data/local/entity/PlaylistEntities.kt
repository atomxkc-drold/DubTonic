package com.androidharness.musicplayer.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "playlists")
data class PlaylistEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val owner: String?,
    val songCount: Int = 0,
    val durationMs: Long = 0,
    val isPublic: Boolean = false,
    val updatedAtMs: Long = 0,
)

@Entity(
    tableName = "playlist_entries",
    primaryKeys = ["playlistId", "position"],
    foreignKeys = [
        ForeignKey(
            entity = PlaylistEntity::class,
            parentColumns = ["id"],
            childColumns = ["playlistId"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = TrackEntity::class,
            parentColumns = ["id"],
            childColumns = ["trackId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index(value = ["trackId"])],
)
data class PlaylistEntryEntity(
    val playlistId: String,
    val trackId: String,
    val position: Int,
    @ColumnInfo(defaultValue = "0")
    val addedAtMs: Long = 0,
)
