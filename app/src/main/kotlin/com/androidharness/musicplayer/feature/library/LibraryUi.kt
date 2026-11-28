package com.androidharness.musicplayer.feature.library

import android.net.Uri
import com.androidharness.musicplayer.core.model.Album
import com.androidharness.musicplayer.core.model.Artist
import com.androidharness.musicplayer.core.model.Playlist
import com.androidharness.musicplayer.core.model.Track

enum class LibrarySection {
    TRACKS,
    ALBUMS,
    ARTISTS,
}

enum class TrackSort {
    TITLE,
    ARTIST,
    ALBUM,
    RECENT,
    DURATION,
}

data class AlbumUi(
    val album: Album,
    val artworkUri: Uri?,
)

data class ArtistUi(
    val artist: Artist,
    val artworkUri: Uri?,
)

data class PlaylistUi(
    val playlist: Playlist,
)

internal fun sortTracks(tracks: List<Track>, sort: TrackSort): List<Track> =
    tracks.sortedWith(trackComparator(sort))

internal fun albumOrder(tracks: List<Track>): List<Track> = tracks.sortedWith(
    compareBy<Track> { track -> track.discNumber ?: 1 }
        .thenBy { track: Track -> track.trackNumber ?: 0 }
        .thenBy(String.CASE_INSENSITIVE_ORDER) { track: Track -> track.title },
)

private fun trackComparator(sort: TrackSort): Comparator<Track> = when (sort) {
    TrackSort.TITLE -> compareBy(
        String.CASE_INSENSITIVE_ORDER,
        { track: Track -> track.title },
    )
        .thenBy(String.CASE_INSENSITIVE_ORDER) { track: Track -> track.artist }
        .thenBy(String.CASE_INSENSITIVE_ORDER) { track: Track -> track.album }

    TrackSort.ARTIST -> compareBy(
        String.CASE_INSENSITIVE_ORDER,
        { track: Track -> track.artist },
    )
        .thenBy(String.CASE_INSENSITIVE_ORDER) { track: Track -> track.album }
        .thenBy { track: Track -> track.discNumber ?: 1 }
        .thenBy { track: Track -> track.trackNumber ?: 0 }
        .thenBy(String.CASE_INSENSITIVE_ORDER) { track: Track -> track.title }

    TrackSort.ALBUM -> compareBy(
        String.CASE_INSENSITIVE_ORDER,
        { track: Track -> track.album },
    )
        .thenBy { track: Track -> track.discNumber ?: 1 }
        .thenBy { track: Track -> track.trackNumber ?: 0 }
        .thenBy(String.CASE_INSENSITIVE_ORDER) { track: Track -> track.title }

    TrackSort.RECENT -> compareByDescending<Track> { track -> track.addedAtMs }
        .thenBy(String.CASE_INSENSITIVE_ORDER) { track: Track -> track.title }

    TrackSort.DURATION -> compareByDescending<Track> { track -> track.durationMs }
        .thenBy(String.CASE_INSENSITIVE_ORDER) { track: Track -> track.title }
}
