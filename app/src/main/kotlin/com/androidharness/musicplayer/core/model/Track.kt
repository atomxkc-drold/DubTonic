package com.androidharness.musicplayer.core.model

data class Track(
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
) {
    val isLocal: Boolean get() = localUri != null
}
