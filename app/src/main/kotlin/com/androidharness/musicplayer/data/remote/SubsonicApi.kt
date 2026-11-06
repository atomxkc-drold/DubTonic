package com.androidharness.musicplayer.data.remote

import com.androidharness.musicplayer.data.remote.dto.AlbumBody
import com.androidharness.musicplayer.data.remote.dto.AlbumListBody
import com.androidharness.musicplayer.data.remote.dto.ArtistBody
import com.androidharness.musicplayer.data.remote.dto.ArtistsBody
import com.androidharness.musicplayer.data.remote.dto.BasicBody
import com.androidharness.musicplayer.data.remote.dto.PingBody
import com.androidharness.musicplayer.data.remote.dto.PlaylistBody
import com.androidharness.musicplayer.data.remote.dto.PlaylistsBody
import com.androidharness.musicplayer.data.remote.dto.ScanStatusBody
import com.androidharness.musicplayer.data.remote.dto.SearchBody
import com.androidharness.musicplayer.data.remote.dto.SubsonicEnvelope
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Streaming
import retrofit2.http.Url

interface SubsonicApi {

    @GET
    suspend fun ping(@Url url: String): SubsonicEnvelope<PingBody>

    @GET
    suspend fun getArtists(@Url url: String): SubsonicEnvelope<ArtistsBody>

    @GET
    suspend fun getArtist(
        @Url url: String,
        @Query("id") id: String,
    ): SubsonicEnvelope<ArtistBody>

    @GET
    suspend fun getAlbum(
        @Url url: String,
        @Query("id") id: String,
    ): SubsonicEnvelope<AlbumBody>

    @GET
    suspend fun getAlbumList2(
        @Url url: String,
        @Query("type") type: String = "alphabeticalByName",
        @Query("size") size: Int = 500,
        @Query("offset") offset: Int = 0,
    ): SubsonicEnvelope<AlbumListBody>

    @GET
    suspend fun search3(
        @Url url: String,
        @Query("query") query: String,
        @Query("songCount") songCount: Int = 100,
        @Query("albumCount") albumCount: Int = 50,
        @Query("artistCount") artistCount: Int = 50,
    ): SubsonicEnvelope<SearchBody>

    @GET
    suspend fun getPlaylists(@Url url: String): SubsonicEnvelope<PlaylistsBody>

    @GET
    suspend fun getPlaylist(
        @Url url: String,
        @Query("id") id: String,
    ): SubsonicEnvelope<PlaylistBody>

    @GET
    suspend fun getScanStatus(@Url url: String): SubsonicEnvelope<ScanStatusBody>

    @GET
    suspend fun scrobble(
        @Url url: String,
        @Query("id") id: String,
        @Query("submission") submission: Boolean = true,
    ): SubsonicEnvelope<BasicBody>

    @Streaming
    @GET
    suspend fun stream(
        @Url url: String,
        @QueryMap params: Map<String, String> = emptyMap(),
    ): ResponseBody

    @Streaming
    @GET
    suspend fun coverArt(
        @Url url: String,
        @QueryMap params: Map<String, String> = emptyMap(),
    ): ResponseBody
}
