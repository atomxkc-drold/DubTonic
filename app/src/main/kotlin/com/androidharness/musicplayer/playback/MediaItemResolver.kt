package com.androidharness.musicplayer.playback

import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.session.MediaSession
import com.androidharness.musicplayer.core.result.AppResult
import com.androidharness.musicplayer.domain.repository.MusicRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaItemResolver @Inject constructor(
    private val repository: MusicRepository,
    private val resumptionStore: PlaybackResumptionStore,
) {

    /**
     * Converts session media ids into playable URIs. Local downloads win over streams,
     * and streaming details follow the current network profile.
     */
    suspend fun toPlayable(items: List<MediaItem>): List<MediaItem> = items.mapNotNull { item ->
        val track = repository.track(item.mediaId) ?: return@mapNotNull null
        when (val uri = repository.playableUri(track)) {
            is AppResult.Success -> item.buildUpon().setUri(uri.value).build()
            is AppResult.Failure -> null
        }
    }

    suspend fun lastQueue(): MediaSession.MediaItemsWithStartPosition {
        val stored = resumptionStore.load() ?: return emptyQueue()
        if (stored.items.isEmpty()) return emptyQueue()

        val playable = toPlayable(stored.items.map(resumptionStore::toMediaItem))
        if (playable.isEmpty()) return emptyQueue()

        return MediaSession.MediaItemsWithStartPosition(
            playable,
            stored.index.coerceIn(0, playable.lastIndex),
            stored.positionMs,
        )
    }

    private fun emptyQueue() = MediaSession.MediaItemsWithStartPosition(
        emptyList(),
        C.INDEX_UNSET,
        C.TIME_UNSET,
    )
}
