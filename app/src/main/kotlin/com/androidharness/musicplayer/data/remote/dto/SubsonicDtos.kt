package com.androidharness.musicplayer.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubsonicEnvelope<T>(
    @SerialName("subsonic-response") val response: T,
)

interface SubsonicBody {
    val status: String
    val error: SubsonicErrorDto?
}

@Serializable
data class SubsonicErrorDto(
    val code: Int,
    val message: String? = null,
)

@Serializable
data class PingBody(
    override val status: String,
    override val error: SubsonicErrorDto? = null,
    val version: String? = null,
) : SubsonicBody

@Serializable
data class BasicBody(
    override val status: String,
    override val error: SubsonicErrorDto? = null,
) : SubsonicBody

@Serializable
data class ArtistsBody(
    override val status: String,
    override val error: SubsonicErrorDto? = null,
    val artists: ArtistsDto? = null,
) : SubsonicBody

@Serializable
data class ArtistsDto(
    val index: List<ArtistIndexDto> = emptyList(),
)

@Serializable
data class ArtistIndexDto(
    val name: String = "",
    val artist: List<ArtistDto> = emptyList(),
)

@Serializable
data class ArtistDto(
    val id: String,
    val name: String,
    val albumCount: Int? = null,
    val coverArt: String? = null,
    val artistImageUrl: String? = null,
)

@Serializable
data class ArtistBody(
    override val status: String,
    override val error: SubsonicErrorDto? = null,
    val artist: ArtistWithAlbumsDto? = null,
) : SubsonicBody

@Serializable
data class ArtistWithAlbumsDto(
    val id: String,
    val name: String,
    val albumCount: Int? = null,
    val coverArt: String? = null,
    val album: List<AlbumDto> = emptyList(),
)

@Serializable
data class AlbumDto(
    val id: String,
    val name: String,
    val artist: String? = null,
    val artistId: String? = null,
    val coverArt: String? = null,
    val songCount: Int? = null,
    val duration: Int? = null,
    val year: Int? = null,
    val created: String? = null,
)

@Serializable
data class AlbumBody(
    override val status: String,
    override val error: SubsonicErrorDto? = null,
    val album: AlbumWithSongsDto? = null,
) : SubsonicBody

@Serializable
data class AlbumWithSongsDto(
    val id: String,
    val name: String,
    val artist: String? = null,
    val artistId: String? = null,
    val coverArt: String? = null,
    val songCount: Int? = null,
    val duration: Int? = null,
    val year: Int? = null,
    val created: String? = null,
    val song: List<SongDto> = emptyList(),
)

@Serializable
data class SongDto(
    val id: String,
    val parent: String? = null,
    val title: String? = null,
    val album: String? = null,
    val artist: String? = null,
    val artistId: String? = null,
    val coverArt: String? = null,
    val duration: Int? = null,
    val bitRate: Int? = null,
    val samplingRate: Int? = null,
    val channelCount: Int? = null,
    val suffix: String? = null,
    val size: Long? = null,
    val track: Int? = null,
    val discNumber: Int? = null,
    val year: Int? = null,
    val genre: String? = null,
    val created: String? = null,
    val isDir: Boolean? = null,
    val replayGain: ReplayGainDto? = null,
)

@Serializable
data class ReplayGainDto(
    val trackGain: Float? = null,
    val trackPeak: Float? = null,
    val albumGain: Float? = null,
    val albumPeak: Float? = null,
)

@Serializable
data class AlbumListBody(
    override val status: String,
    override val error: SubsonicErrorDto? = null,
    val albumList2: AlbumListDto? = null,
) : SubsonicBody

@Serializable
data class AlbumListDto(
    val album: List<AlbumDto> = emptyList(),
)

@Serializable
data class SearchBody(
    override val status: String,
    override val error: SubsonicErrorDto? = null,
    val searchResult3: SearchResultDto? = null,
) : SubsonicBody

@Serializable
data class SearchResultDto(
    val artist: List<ArtistDto> = emptyList(),
    val album: List<AlbumDto> = emptyList(),
    val song: List<SongDto> = emptyList(),
)

@Serializable
data class PlaylistsBody(
    override val status: String,
    override val error: SubsonicErrorDto? = null,
    val playlists: PlaylistsDto? = null,
) : SubsonicBody

@Serializable
data class PlaylistsDto(
    val playlist: List<PlaylistDto> = emptyList(),
)

@Serializable
data class PlaylistDto(
    val id: String,
    val name: String,
    val owner: String? = null,
    val songCount: Int? = null,
    val duration: Int? = null,
    val public: Boolean? = null,
    val changed: String? = null,
)

@Serializable
data class PlaylistBody(
    override val status: String,
    override val error: SubsonicErrorDto? = null,
    val playlist: PlaylistWithSongsDto? = null,
) : SubsonicBody

@Serializable
data class PlaylistWithSongsDto(
    val id: String,
    val name: String,
    val owner: String? = null,
    val songCount: Int? = null,
    val duration: Int? = null,
    val public: Boolean? = null,
    val changed: String? = null,
    val entry: List<SongDto> = emptyList(),
)

@Serializable
data class ScanStatusBody(
    override val status: String,
    override val error: SubsonicErrorDto? = null,
    val scanStatus: ScanStatusDto? = null,
) : SubsonicBody

@Serializable
data class ScanStatusDto(
    val scanning: Boolean = false,
    val count: Int? = null,
    val lastScan: String? = null,
)
