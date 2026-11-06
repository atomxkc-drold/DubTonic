package com.androidharness.musicplayer.playback

import android.content.Context
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.androidharness.musicplayer.data.settings.SettingsEngine
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.Serializable

@Serializable
data class StoredQueueItem(
    val id: String,
    val title: String? = null,
    val artist: String? = null,
    val artworkUri: String? = null,
)

@Serializable
data class StoredQueue(
    val items: List<StoredQueueItem> = emptyList(),
    val index: Int = 0,
    val positionMs: Long = 0,
)

@Singleton
class PlaybackResumptionStore @Inject constructor(
    @ApplicationContext context: Context,
) {
    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun save(items: List<MediaItem>, index: Int, positionMs: Long) {
        val capped = items.take(MAX_ITEMS).map { item ->
            StoredQueueItem(
                id = item.mediaId,
                title = item.mediaMetadata.title?.toString(),
                artist = item.mediaMetadata.artist?.toString(),
                artworkUri = item.mediaMetadata.artworkUri?.toString(),
            )
        }
        val queue = StoredQueue(
            items = capped,
            index = index.coerceIn(0, capped.lastIndex.coerceAtLeast(0)),
            positionMs = positionMs.coerceAtLeast(0),
        )
        preferences.edit()
            .putString(KEY_QUEUE, SettingsEngine.json.encodeToString(StoredQueue.serializer(), queue))
            .apply()
    }

    fun load(): StoredQueue? {
        val raw = preferences.getString(KEY_QUEUE, null) ?: return null
        return runCatching {
            SettingsEngine.json.decodeFromString(StoredQueue.serializer(), raw)
        }.getOrNull()
    }

    fun toMediaItem(stored: StoredQueueItem): MediaItem = MediaItem.Builder()
        .setMediaId(stored.id)
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setTitle(stored.title)
                .setArtist(stored.artist)
                .setArtworkUri(stored.artworkUri?.toUri())
                .setIsPlayable(true)
                .setIsBrowsable(false)
                .setMediaType(MediaMetadata.MEDIA_TYPE_MUSIC)
                .build()
        )
        .build()

    private companion object {
        const val PREFS_NAME = "playback_resumption"
        const val KEY_QUEUE = "queue"
        const val MAX_ITEMS = 1_000
    }
}
