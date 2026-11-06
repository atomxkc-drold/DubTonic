package com.androidharness.musicplayer.playback

import android.net.Uri
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.androidharness.musicplayer.core.model.Album
import com.androidharness.musicplayer.core.model.Artist
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.data.remote.SubsonicUrls
import com.androidharness.musicplayer.domain.repository.MusicRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

/**
 * In-memory browse tree for MediaLibrarySession. Callbacks run on the session thread,
 * so every lookup here is a snapshot read, never a database or network call.
 */
@Singleton
class LibraryTree @Inject constructor(
    repository: MusicRepository,
    private val urls: SubsonicUrls,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val tracks = repository.observeTracks()
        .stateIn(scope, SharingStarted.Eagerly, emptyList())
    private val albums = repository.observeAlbums()
        .stateIn(scope, SharingStarted.Eagerly, emptyList())
    private val artists = repository.observeArtists()
        .stateIn(scope, SharingStarted.Eagerly, emptyList())

    fun root(): MediaItem = MediaItem.Builder()
        .setMediaId(ROOT_ID)
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setTitle("Library")
                .setIsBrowsable(true)
                .setIsPlayable(false)
                .setMediaType(MediaMetadata.MEDIA_TYPE_FOLDER_MIXED)
                .build()
        )
        .build()

    fun children(parentId: String, page: Int, pageSize: Int): List<MediaItem> {
        val all = when {
            parentId == ROOT_ID -> listOf(
                category(TRACKS_ID, "Tracks"),
                category(ALBUMS_ID, "Albums"),
                category(ARTISTS_ID, "Artists"),
            )
            parentId == TRACKS_ID -> tracks.value.map(::trackItem)
            parentId == ALBUMS_ID -> albums.value.map(::albumItem)
            parentId == ARTISTS_ID -> artists.value.map(::artistItem)
            parentId.startsWith(ALBUM_PREFIX) -> {
                val albumId = parentId.removePrefix(ALBUM_PREFIX)
                tracks.value
                    .filter { it.albumId == albumId }
                    .sortedWith(compareBy({ it.discNumber ?: 0 }, { it.trackNumber ?: 0 }))
                    .map(::trackItem)
            }
            parentId.startsWith(ARTIST_PREFIX) -> {
                val artistId = parentId.removePrefix(ARTIST_PREFIX)
                tracks.value
                    .filter { it.artistId == artistId }
                    .map(::trackItem)
            }
            else -> emptyList()
        }

        val safePage = page.coerceAtLeast(0)
        val safeSize = pageSize.coerceAtLeast(1)
        return all.drop(safePage * safeSize).take(safeSize)
    }

    private fun category(id: String, title: String): MediaItem = MediaItem.Builder()
        .setMediaId(id)
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setTitle(title)
                .setIsBrowsable(true)
                .setIsPlayable(false)
                .setMediaType(MediaMetadata.MEDIA_TYPE_FOLDER_MIXED)
                .build()
        )
        .build()

    private fun trackItem(track: Track): MediaItem = MediaItem.Builder()
        .setMediaId(track.id)
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setTitle(track.title)
                .setArtist(track.artist)
                .setAlbumTitle(track.album)
                .setArtworkUri(artwork(track.coverArtId))
                .setDurationMs(track.durationMs)
                .setIsBrowsable(false)
                .setIsPlayable(true)
                .setMediaType(MediaMetadata.MEDIA_TYPE_MUSIC)
                .build()
        )
        .build()

    private fun albumItem(album: Album): MediaItem = MediaItem.Builder()
        .setMediaId("$ALBUM_PREFIX${album.id}")
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setTitle(album.name)
                .setArtist(album.artist)
                .setArtworkUri(artwork(album.coverArtId))
                .setIsBrowsable(true)
                .setIsPlayable(false)
                .setMediaType(MediaMetadata.MEDIA_TYPE_FOLDER_ALBUMS)
                .build()
        )
        .build()

    private fun artistItem(artist: Artist): MediaItem = MediaItem.Builder()
        .setMediaId("$ARTIST_PREFIX${artist.id}")
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setTitle(artist.name)
                .setArtworkUri(artwork(artist.coverArtId))
                .setIsBrowsable(true)
                .setIsPlayable(false)
                .setMediaType(MediaMetadata.MEDIA_TYPE_FOLDER_MIXED)
                .build()
        )
        .build()

    private fun artwork(coverArtId: String?): Uri? = coverArtId?.let {
        runCatching { urls.coverArtUrl(it, size = 512).toUri() }.getOrNull()
    }

    companion object {
        const val ROOT_ID = "root"
        const val TRACKS_ID = "category:tracks"
        const val ALBUMS_ID = "category:albums"
        const val ARTISTS_ID = "category:artists"
        const val ALBUM_PREFIX = "album:"
        const val ARTIST_PREFIX = "artist:"
    }
}
