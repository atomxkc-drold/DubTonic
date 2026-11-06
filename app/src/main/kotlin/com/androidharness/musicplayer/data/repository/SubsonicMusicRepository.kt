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
import com.androidharness.musicplayer.data.local.dao.PlaylistDao
import com.androidharness.musicplayer.data.local.dao.TrackDao
import com.androidharness.musicplayer.data.local.toEntity
import com.androidharness.musicplayer.data.local.toModel
import com.androidharness.musicplayer.data.remote.SubsonicApi
import com.androidharness.musicplayer.data.remote.SubsonicCredentialsStore
import com.androidharness.musicplayer.data.remote.SubsonicUrls
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

    override fun search(query: String): Flow<List<Track>> {
        val ftsQuery = query.toFtsQuery()
        if (ftsQuery.isBlank()) return flowOf(emptyList())
        return trackDao.search(ftsQuery).map { entities -> entities.map { it.toModel() } }
    }

    override suspend fun track(id: String): Track? = trackDao.byId(id)?.toModel()

    override suspend fun playableUri(track: Track): AppResult<String> {
        track.localUri?.let { return AppResult.Success(it) }

        val network = connectivity.current()
        val networkSettings = settings.settings.value.network
        if (network.isMetered && !networkSettings.allowMeteredStreaming) {
            return AppResult.Failure(
                AppError.NotConfigured("Metered streaming is disabled in settings")
            )
        }
        return try {
            AppResult.Success(
                urls.streamUrl(track.id, networkSettings.transcodeFor(network))
            )
        } catch (error: IllegalStateException) {
            AppResult.Failure(AppError.NotConfigured(error.message ?: "Server not configured"))
        }
    }

    override suspend fun syncLibrary(): AppResult<SyncSummary> {
        val now = System.currentTimeMillis()
        return subsonicCall {
            credentials.require()

            val artistList = api.getArtists(urls.rest("getArtists"))
                .validated()
                .artists
                ?.index
                .orEmpty()
                .flatMap { it.artist }
                .distinctBy { it.id }

            artistDao.upsertAll(artistList.map { it.toArtist().toEntity() })

            var albumCount = 0
            var trackCount = 0

            for (artist in artistList.take(MAX_ARTISTS_PER_SYNC)) {
                val artistDetail = api.getArtist(urls.rest("getArtist"), artist.id)
                    .validated()
                    .artist ?: continue

                val albums = artistDetail.album.distinctBy { it.id }
                albumDao.upsertAll(albums.map { it.toAlbum(now).toEntity() })
                albumCount += albums.size

                for (album in albums.take(MAX_ALBUMS_PER_ARTIST)) {
                    val detail = api.getAlbum(urls.rest("getAlbum"), album.id)
                        .validated()
                        .album ?: continue

                    val songs = detail.song
                        .filter { it.isDir != true }
                        .distinctBy { it.id }

                    trackDao.upsertAll(songs.map { it.toTrack(now).toEntity() })
                    trackCount += songs.size
                }
            }

            val playlists = api.getPlaylists(urls.rest("getPlaylists"))
                .validated()
                .playlists
                ?.playlist
                .orEmpty()

            playlistDao.upsertAll(playlists.map { it.toPlaylist().toEntity() })

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

    override suspend fun downloadTrack(trackId: String): AppResult<String> =
        withContext(Dispatchers.IO) {
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
                    val extension = track.format?.takeIf { it.isNotBlank() } ?: "audio"
                    val target = File(directory, "${trackId.safeFileName()}.$extension")
                    val temporary = File(directory, "${trackId.safeFileName()}.part")

                    body.byteStream().use { input ->
                        temporary.outputStream().use { output -> input.copyTo(output) }
                    }
                    if (!temporary.renameTo(target)) {
                        temporary.copyTo(target, overwrite = true)
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
    }
}

internal fun String.toFtsQuery(): String =
    trim()
        .split(Regex("\\s+"))
        .asSequence()
        .filter { it.isNotBlank() }
        .map { token -> "\"" + token.replace("\"", "") + "\"*" }
        .joinToString(" ")
