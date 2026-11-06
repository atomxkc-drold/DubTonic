package com.androidharness.musicplayer.domain.repository

import com.androidharness.musicplayer.core.model.Album
import com.androidharness.musicplayer.core.model.Artist
import com.androidharness.musicplayer.core.model.Playlist
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.core.result.AppResult
import kotlinx.coroutines.flow.Flow

data class SyncSummary(
    val artists: Int,
    val albums: Int,
    val tracks: Int,
)

interface MusicRepository {
    fun observeTracks(): Flow<List<Track>>

    fun observeAlbums(): Flow<List<Album>>

    fun observeArtists(): Flow<List<Artist>>

    fun observePlaylists(): Flow<List<Playlist>>

    fun observePlaylistTracks(playlistId: String): Flow<List<Track>>

    fun search(query: String): Flow<List<Track>>

    suspend fun track(id: String): Track?

    suspend fun playableUri(track: Track): AppResult<String>

    suspend fun syncLibrary(): AppResult<SyncSummary>

    suspend fun toggleFavorite(trackId: String, favorite: Boolean)

    suspend fun downloadTrack(trackId: String): AppResult<String>

    suspend fun removeDownload(trackId: String)
}
