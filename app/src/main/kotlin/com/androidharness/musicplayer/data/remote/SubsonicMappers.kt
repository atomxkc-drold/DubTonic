package com.androidharness.musicplayer.data.remote

import com.androidharness.musicplayer.core.model.Album
import com.androidharness.musicplayer.core.model.Artist
import com.androidharness.musicplayer.core.model.Playlist
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.data.remote.dto.AlbumDto
import com.androidharness.musicplayer.data.remote.dto.ArtistDto
import com.androidharness.musicplayer.data.remote.dto.PlaylistDto
import com.androidharness.musicplayer.data.remote.dto.SongDto
import java.time.Instant
import java.time.format.DateTimeParseException

fun SongDto.toTrack(syncedAtMs: Long = System.currentTimeMillis()): Track = Track(
    id = id,
    title = title?.takeIf { it.isNotBlank() } ?: "Unknown title",
    artist = artist?.takeIf { it.isNotBlank() } ?: "Unknown artist",
    album = album?.takeIf { it.isNotBlank() } ?: "Unknown album",
    albumId = parent,
    artistId = artistId,
    durationMs = (duration ?: 0).toLong() * 1_000L,
    trackNumber = track,
    discNumber = discNumber,
    year = year,
    genre = genre,
    bitrateKbps = bitRate,
    sampleRateHz = samplingRate,
    channels = channelCount,
    format = suffix,
    sizeBytes = size,
    coverArtId = coverArt,
    replayGainTrackDb = replayGain?.trackGain,
    replayGainAlbumDb = replayGain?.albumGain,
    addedAtMs = parseTimestamp(created),
    lastSyncedAtMs = syncedAtMs,
)

fun AlbumDto.toAlbum(syncedAtMs: Long = System.currentTimeMillis()): Album = Album(
    id = id,
    name = name,
    artist = artist?.takeIf { it.isNotBlank() } ?: "Unknown artist",
    artistId = artistId,
    coverArtId = coverArt,
    songCount = songCount ?: 0,
    durationMs = (duration ?: 0).toLong() * 1_000L,
    year = year,
    addedAtMs = parseTimestamp(created),
    lastSyncedAtMs = syncedAtMs,
)

fun ArtistDto.toArtist(): Artist = Artist(
    id = id,
    name = name,
    albumCount = albumCount ?: 0,
    coverArtId = coverArt,
)

fun PlaylistDto.toPlaylist(): Playlist = Playlist(
    id = id,
    name = name,
    owner = owner,
    songCount = songCount ?: 0,
    durationMs = (duration ?: 0).toLong() * 1_000L,
    isPublic = public == true,
    updatedAtMs = parseTimestamp(changed),
)

internal fun parseTimestamp(value: String?): Long {
    if (value.isNullOrBlank()) return 0
    return try {
        Instant.parse(value).toEpochMilli()
    } catch (_: DateTimeParseException) {
        0
    }
}
