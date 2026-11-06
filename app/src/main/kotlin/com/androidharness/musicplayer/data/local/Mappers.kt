package com.androidharness.musicplayer.data.local

import com.androidharness.musicplayer.core.model.Album
import com.androidharness.musicplayer.core.model.Artist
import com.androidharness.musicplayer.core.model.Playlist
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.data.local.entity.AlbumEntity
import com.androidharness.musicplayer.data.local.entity.ArtistEntity
import com.androidharness.musicplayer.data.local.entity.PlaylistEntity
import com.androidharness.musicplayer.data.local.entity.TrackEntity

fun TrackEntity.toModel(): Track = Track(
    id = id,
    title = title,
    artist = artist,
    album = album,
    albumId = albumId,
    artistId = artistId,
    durationMs = durationMs,
    trackNumber = trackNumber,
    discNumber = discNumber,
    year = year,
    genre = genre,
    bitrateKbps = bitrateKbps,
    sampleRateHz = sampleRateHz,
    channels = channels,
    format = format,
    sizeBytes = sizeBytes,
    coverArtId = coverArtId,
    isFavorite = isFavorite,
    isDownloaded = isDownloaded,
    localUri = localUri,
    replayGainTrackDb = replayGainTrackDb,
    replayGainAlbumDb = replayGainAlbumDb,
    addedAtMs = addedAtMs,
    lastSyncedAtMs = lastSyncedAtMs,
)

fun Track.toEntity(rowId: Long = stableRowId(id)): TrackEntity = TrackEntity(
    rowId = rowId,
    id = id,
    title = title,
    artist = artist,
    album = album,
    albumId = albumId,
    artistId = artistId,
    durationMs = durationMs,
    trackNumber = trackNumber,
    discNumber = discNumber,
    year = year,
    genre = genre,
    bitrateKbps = bitrateKbps,
    sampleRateHz = sampleRateHz,
    channels = channels,
    format = format,
    sizeBytes = sizeBytes,
    coverArtId = coverArtId,
    isFavorite = isFavorite,
    isDownloaded = isDownloaded,
    localUri = localUri,
    replayGainTrackDb = replayGainTrackDb,
    replayGainAlbumDb = replayGainAlbumDb,
    addedAtMs = addedAtMs,
    lastSyncedAtMs = lastSyncedAtMs,
)

fun AlbumEntity.toModel(): Album = Album(
    id = id,
    name = name,
    artist = artist,
    artistId = artistId,
    coverArtId = coverArtId,
    songCount = songCount,
    durationMs = durationMs,
    year = year,
    addedAtMs = addedAtMs,
    lastSyncedAtMs = lastSyncedAtMs,
    isFavorite = isFavorite,
)

fun Album.toEntity(rowId: Long = stableRowId(id)): AlbumEntity = AlbumEntity(
    rowId = rowId,
    id = id,
    name = name,
    artist = artist,
    artistId = artistId,
    coverArtId = coverArtId,
    songCount = songCount,
    durationMs = durationMs,
    year = year,
    addedAtMs = addedAtMs,
    lastSyncedAtMs = lastSyncedAtMs,
    isFavorite = isFavorite,
)

fun ArtistEntity.toModel(): Artist = Artist(
    id = id,
    name = name,
    albumCount = albumCount,
    coverArtId = coverArtId,
    isFavorite = isFavorite,
)

fun Artist.toEntity(rowId: Long = stableRowId(id)): ArtistEntity = ArtistEntity(
    rowId = rowId,
    id = id,
    name = name,
    albumCount = albumCount,
    coverArtId = coverArtId,
    isFavorite = isFavorite,
)

fun PlaylistEntity.toModel(): Playlist = Playlist(
    id = id,
    name = name,
    owner = owner,
    songCount = songCount,
    durationMs = durationMs,
    isPublic = isPublic,
    updatedAtMs = updatedAtMs,
)

fun Playlist.toEntity(): PlaylistEntity = PlaylistEntity(
    id = id,
    name = name,
    owner = owner,
    songCount = songCount,
    durationMs = durationMs,
    isPublic = isPublic,
    updatedAtMs = updatedAtMs,
)
