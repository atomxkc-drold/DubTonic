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

    /** Local play counts keyed by track id. */
    fun observePlayCounts(): Flow<Map<String, Int>>

    /** Tracks ordered by how often they were started, most played first. */
    fun observeTopPlayed(limit: Int): Flow<List<Track>>

    /** Tracks ordered by the last time they were started, newest first. */
    fun observeRecentlyPlayed(limit: Int): Flow<List<Track>>

    /** Tracks that were never started, newest additions first. */
    fun observeNeverPlayed(limit: Int): Flow<List<Track>>

    /** Counts a track start for History, Top 10 and Discover. */
    suspend fun recordPlay(trackId: String)

    fun search(query: String): Flow<List<Track>>

    suspend fun track(id: String): Track?

    fun observeTrack(id: String): Flow<Track?>

    suspend fun setRating(trackId: String, rating: Int)

    suspend fun playableUri(track: Track): AppResult<String>

    suspend fun syncLibrary(): AppResult<SyncSummary>

    suspend fun toggleFavorite(trackId: String, favorite: Boolean)

    suspend fun downloadTrack(trackId: String): AppResult<String>

    suspend fun removeDownload(trackId: String)

    /** Reports a completed play to the Subsonic server (scrobble). Best effort. */
    suspend fun scrobble(trackId: String)}
