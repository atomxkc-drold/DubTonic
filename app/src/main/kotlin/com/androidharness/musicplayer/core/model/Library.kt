package com.androidharness.musicplayer.core.model

data class Album(
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

data class Artist(
    val id: String,
    val name: String,
    val albumCount: Int,
    val coverArtId: String? = null,
    val isFavorite: Boolean = false,
)

data class Playlist(
    val id: String,
    val name: String,
    val owner: String? = null,
    val songCount: Int = 0,
    val durationMs: Long = 0,
    val isPublic: Boolean = false,
    val updatedAtMs: Long = 0,
)
