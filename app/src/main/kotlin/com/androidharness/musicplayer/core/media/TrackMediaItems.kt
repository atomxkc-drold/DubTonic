package com.androidharness.musicplayer.core.media

import android.net.Uri
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.data.remote.SubsonicUrls
import javax.inject.Inject
import javax.inject.Singleton

/**
 * One factory for queue items so Home, Library and Playlists build identical
 * MediaItems (same id, metadata and artwork URL, so Coil cache keys match).
 */
@Singleton
class TrackMediaItems @Inject constructor(
    private val urls: SubsonicUrls,
) {
    fun artwork(coverArtId: String?, size: Int = 512): Uri? = coverArtId?.let {
        runCatching { urls.coverArtUrl(it, size).toUri() }.getOrNull()
    }

    fun toMediaItem(track: Track): MediaItem = MediaItem.Builder()
        .setMediaId(track.id)
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setTitle(track.title)
                .setArtist(track.artist)
                .setAlbumTitle(track.album)
                .setArtworkUri(artwork(track.coverArtId))
                .setDurationMs(track.durationMs)
                .setIsPlayable(true)
                .setIsBrowsable(false)
                .setMediaType(MediaMetadata.MEDIA_TYPE_MUSIC)
                .build()
        )
        .build()
}
