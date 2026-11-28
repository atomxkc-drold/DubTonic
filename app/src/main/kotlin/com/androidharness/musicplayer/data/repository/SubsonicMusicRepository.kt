package com.androidharness.musicplayer.data.repository

import android.content.Context
import android.net.Uri
import com.androidharness.musicplayer.core.model.Album
import com.androidharness.musicplayer.core.model.Artist
import com.androidharness.musicplayer.core.model.Playlist
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.core.result.AppError
import com.androidharness.musicplayer.core.result.AppResult
import com.androidharness.musicplayer.data.cache.AudioCacheEvictor
import com.androidharness.musicplayer.data.connectivity.ConnectivityMonitor
import com.androidharness.musicplayer.data.local.dao.AlbumDao
import com.androidharness.musicplayer.data.local.dao.ArtistDao
import com.androidharness.musicplayer.data.local.dao.PlayStatsDao
import com.androidharness.musicplayer.data.local.dao.PlaylistDao
import com.androidharness.musicplayer.data.local.dao.TrackDao
import com.androidharness.musicplayer.data.local.entity.PlayStatsEntity
import com.androidharness.musicplayer.data.local.toEntity
import com.androidharness.musicplayer.data.local.toModel
import com.androidharness.musicplayer.data.remote.SubsonicApi
import com.androidharness.musicplayer.data.remote.SubsonicCredentialsStore
import com.androidharness.musicplayer.data.remote.SubsonicUrls
import com.androidharness.musicplayer.data.remote.StreamUri
import com.androidharness.musicplayer.data.remote.subsonicCall
import com.androidharness.musicplayer.data.remote.toAlbum
import com.androidharness.musicplayer.data.remote.toArtist
import com.androidharness.musicplayer.data.remote.toPlaylist
import com.androidharness.musicplayer.data.remote.toTrack
import com.androidharness.musicplayer.data.remote.validated
import com.androidharness.musicplayer.domain.repository.MusicRepository
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import com.androidharness.musicplayer.domain.repository.SyncSummary
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

@Singleton
class SubsonicMusicRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val trackDao: TrackDao,
    private val albumDao: AlbumDao,
    private val artistDao: ArtistDao,
    private val playlistDao: PlaylistDao,
    private val playStatsDao: PlayStatsDao,
    private val api: SubsonicApi,
    private val urls: SubsonicUrls,
    private val credentials: SubsonicCredentialsStore,
    private val settings: SettingsRepository,
    private val connectivity: ConnectivityMonitor,
    private val httpClient: OkHttpClient,
    private val evictor: AudioCacheEvictor,
) : MusicRepository {

    override fun observeTracks(): Flow<List<Track>> =
        trackDao.observeAll().map { entities -> entities.map { it.toModel() } }

    override fun observeAlbums(): Flow<List<Album>> =
        albumDao.observeAll().map { entities -> entities.map { it.toModel() } }

    override fun observeArtists(): Flow<List<Artist>> =
        artistDao.observeAll().map { entities -> entities.map { it.toModel() } }

    override fun observePlaylists(): Flow<List<Playlist>> =
        playlistDao.observeAll().map { entities -> entities.map { it.toModel() } }

    override fun observePlaylistTracks(playlistId: String): Flow<List<Track>> =
        playlistDao.observeTracks(playlistId).map { entities -> entities.map { it.toModel() } }

    override fun observePlayCounts(): Flow<Map<String, Int>> =
        playStatsDao.observeAll().map { rows -> rows.associate { it.trackId to it.playCount } }

    override fun observeTopPlayed(limit: Int): Flow<List<Track>> =
        playStatsDao.observeTopPlayed(limit).map { entities -> entities.map { it.toModel() } }

    override fun observeRecentlyPlayed(limit: Int): Flow<List<Track>> =
        playStatsDao.observeRecentlyPlayed(limit).map { entities -> entities.map { it.toModel() } }

    override fun observeNeverPlayed(limit: Int): Flow<List<Track>> =
        playStatsDao.observeUnplayed(limit).map { entities -> entities.map { it.toModel() } }

    override suspend fun recordPlay(trackId: String) {
        if (trackId.isBlank()) return
        val now = System.currentTimeMillis()
        if (playStatsDao.bump(trackId, now) == 0) {
            playStatsDao.insert(PlayStatsEntity(trackId = trackId, playCount = 1, lastPlayedAtMs = now))
        }
    }

    override fun search(query: String): Flow<List<Track>> {
        val ftsQuery = query.toFtsQuery()
        if (ftsQuery.isBlank()) return flowOf(emptyList())
        return trackDao.search(ftsQuery).map { entities -> entities.map { it.toModel() } }
    }

    override suspend fun track(id: String): Track? = trackDao.byId(id)?.toModel()

    override fun observeTrack(id: String): Flow<Track?> =
        trackDao.observeById(id).map { it?.toModel() }

    override suspend fun playableUri(track: Track): AppResult<String> {
        val network = connectivity.current()
        val networkSettings = settings.settings.value.network
        PlaybackSource
            .localFor(track.localUri, networkSettings.preferLocalCopy)
            ?.let { localUri ->
                // LRU hint: a played download must survive the next eviction pass.
                evictor.touch(localUri)
                return AppResult.Success(localUri)
            }

        if (network.isMetered && !networkSettings.allowMeteredStreaming) {
            return AppResult.Failure(
                AppError.NotConfigured("Metered streaming is disabled in settings")
            )
        }
        // A stable, credential-free URI: the request itself is built at playback time from
        // the network profile in force when the song opens, not when it was queued.
        return AppResult.Success(StreamUri.forTrack(track.id))
    }

    override suspend fun syncLibrary(): AppResult<SyncSummary> {
        if (settings.settings.value.ui.privacyKillSwitch) {
            return AppResult.Failure(AppError.Network("Privacy mode blocks library metadata sync"))
        }
        val now = System.currentTimeMillis()
        return subsonicCall {
            credentials.require()

            // Full sync: paginate through all artists until exhausted.
            // Subsonic getArtists returns paginated results; keep fetching until
            // we hit an empty page so libraries larger than any single page are covered.
            val artistList = mutableListOf<ArtistDto>()
            var artistOffset = 0
            var artistPageSize = 500
            var hasMoreArtists = true

            while (hasMoreArtists) {
                val page = api.getArtistsPage(
                    urls.rest("getArtists"),
                    size = artistPageSize,
                    offset = artistOffset,
                ).validated()
                val pageArtists = page.artists?.index?.orEmpty()
                    ?.flatMap { it.artist }
                    ?.distinctBy { it.id }
                    ?: emptyList()
                artistList.addAll(pageArtists)
                if (pageArtists.size < artistPageSize) {
                    hasMoreArtists = false
                } else {
                    artistOffset += artistPageSize
                }
            }

            artistDao.upsertAll(artistList.map { it.toArtist().toEntity() })

            var albumCount = 0
            var trackCount = 0

            // Full sync: paginate through albums per artist until exhausted.
            for (artist in artistList) {
                val artistDetail = api.getArtist(urls.rest("getArtist"), artist.id)
                    .validated()
                    .artist ?: continue

                val albums = artistDetail.album.distinctBy { it.id }
                albumDao.upsertAll(albums.map { it.toAlbum(now).toEntity() })
                albumCount += albums.size

                // Paginate through albums for this artist until exhausted.
                var albumOffset = 0
                var hasMoreAlbums = true
                val albumsToProcess = mutableListOf<AlbumDto>()

                while (hasMoreAlbums) {
                    val albumPage = api.getArtistAlbumsPage(
                        urls.rest("getArtist"),
                        artist.id,
                        size = 500,
                        offset = albumOffset,
                    ).validated()
                    val pageAlbums = albumPage.album?.distinctBy { it.id } ?: emptyList()
                    albumsToProcess.addAll(pageAlbums)
                    if (pageAlbums.size < 500) {
                        hasMoreAlbums = false
                    } else {
                        albumOffset += 500
                    }
                }

                for (album in albumsToProcess) {
                    val detail = api.getAlbum(urls.rest("getAlbum"), album.id)
                        .validated()
                        .album ?: continue

                    val songs = detail.song
                        .filter { it.isDir != true }
                        .distinctBy { it.id }

                    trackDao.upsertSynced(songs.map { it.toTrack(now).toEntity() })
                    trackCount += songs.size
                }
            }

            val playlists = api.getPlaylists(urls.rest("getPlaylists"))
                .validated()
                .playlists
                ?.playlist
                .orEmpty()

            playlistDao.upsertAll(playlists.map { it.toPlaylist().toEntity() })

            for (playlist in playlists) {
                val entries = api.getPlaylist(urls.rest("getPlaylist"), playlist.id)
                    .validated()
                    .playlist
                    ?.entry
                    .orEmpty()
                    .map { it.id }
                playlistDao.replaceEntries(playlist.id, entries)
            }

            SyncSummary(
                artists = artistList.size,
                albums = albumCount,
                tracks = trackCount,
            )
        }
    }

    override suspend fun toggleFavorite(trackId: String, favorite: Boolean) {
        trackDao.setFavorite(trackId, favorite)
    }

    override suspend fun setRating(trackId: String, rating: Int) {
        trackDao.setRating(trackId, rating.coerceIn(0, 5))
    }

    override suspend fun downloadTrack(trackId: String): AppResult<String> =
        withContext(Dispatchers.IO) {
            if (settings.settings.value.ui.privacyKillSwitch) {
                return@withContext AppResult.Failure(
                    AppError.Network("Privacy mode blocks external audio downloads")
                )
            }
            val track = trackDao.byId(trackId)?.toModel()
                ?: return@withContext AppResult.Failure(
                    AppError.Subsonic(70, "Track not found")
                )

            try {
                val request = Request.Builder().url(urls.downloadUrl(trackId)).build()
                httpClient.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        return@withContext AppResult.Failure(
                            AppError.Http(response.code, "Download failed")
                        )
                    }
                    val body = response.body
                        ?: return@withContext AppResult.Failure(
                            AppError.Parse("Empty download body")
                        )

                    val directory = evictor.downloadsDir.apply { mkdirs() }
                    val extension = track.format?.takeIf { it.matches(Regex("[A-Za-z0-9]{1,8}")) } ?: "audio"
                    val target = File(directory, "${trackId.safeFileName()}.$extension")
                    val temporary = File(directory, "${trackId.safeFileName()}.part")
                    val expectedBytes = body.contentLength()

                    try {
                        body.byteStream().use { input ->
                            temporary.outputStream().use { output -> input.copyTo(output) }
                        }
                        if (expectedBytes >= 0 && temporary.length() != expectedBytes) {
                            return@withContext AppResult.Failure(
                                AppError.Network("Download truncated: expected $expectedBytes bytes")
                            )
                        }
                        if (!temporary.renameTo(target)) {
                            temporary.copyTo(target, overwrite = true)
                        }
                    } finally {
                        temporary.delete()
                    }

                    val uri = Uri.fromFile(target).toString()
                    trackDao.markDownloaded(trackId, uri)
                    evictor.evictNow()
                    AppResult.Success(uri)
                }
            } catch (error: IOException) {
                AppResult.Failure(AppError.Network(error.message ?: "Download failed", error))
            }
        }

    override suspend fun scrobble(trackId: String) {
        if (settings.settings.value.ui.privacyKillSwitch) return
        runCatching {
            subsonicCall {
                api.scrobble(urls.rest("scrobble"), trackId, submission = true).validated()
            }
        }
    }

    override suspend fun removeDownload(trackId: String) {
        val entity = trackDao.byId(trackId) ?: return
        val path = entity.localUri?.let { Uri.parse(it).path }
        if (path != null) File(path).delete()
        trackDao.markNotDownloaded(trackId)
    }

    private fun String.safeFileName(): String =
        replace(Regex("[^A-Za-z0-9._-]"), "_").take(120)

    private companion object {
        const val MAX_ARTISTS_PER_SYNC = 200
        const val MAX_ALBUMS_PER_ARTIST = 100
        const val DOWNLOAD_NOTIFICATION_ID = 1001
    }
}

internal fun String.toFtsQuery(): String =
    trim()
        .split(Regex("\\s+"))
        .asSequence()
        .filter { it.isNotBlank() }
        .map { token -> "\"" + token.replace("\"", "") + "\"*" }
        .joinToString(" ")
