package com.androidharness.musicplayer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Play history kept apart from the track rows. Library sync rewrites every track column on
 * each sync, so counts stored on the track itself would be reset; a separate table survives.
 */
@Entity(tableName = "play_stats")
data class PlayStatsEntity(
    @PrimaryKey
    val trackId: String,
    val playCount: Int = 0,
    val lastPlayedAtMs: Long = 0,
)
