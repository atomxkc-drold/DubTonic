package com.androidharness.musicplayer.data.repository

/**
 * Decides whether an already downloaded file may serve playback.
 *
 * `network.preferLocalCopy` is the user's escape hatch for a stale or corrupt download:
 * turning it off forces the stream URL even when a local file exists.
 */
object PlaybackSource {

    fun localFor(localUri: String?, preferLocalCopy: Boolean): String? {
        if (!preferLocalCopy) return null
        return localUri?.takeIf { it.isNotBlank() }
    }
}
