package com.androidharness.musicplayer.data.repository

import java.io.File

/**
 * Decides whether an already downloaded file may serve playback.
 *
 * A stale `localUri` used to be trusted blindly, so a download that was evicted,
 * truncated or written for a different codec played as noise instead of falling back to
 * the stream. A local copy now has to exist and hold at least one byte to win.
 *
 * `network.preferLocalCopy` is the user's escape hatch for a stale or corrupt download:
 * turning it off forces the stream URL even when a local file exists.
 */
object PlaybackSource {

    fun localFor(localUri: String?, preferLocalCopy: Boolean): String? {
        if (!preferLocalCopy) return null
        val uri = localUri?.takeIf { it.isNotBlank() } ?: return null

        val path = when {
            uri.startsWith("file://") -> uri.removePrefix("file://")
            uri.startsWith("/") -> uri
            // content:// and other providers cannot be checked cheaply; trust them.
            else -> return uri
        }

        val file = File(path)
        return uri.takeIf { file.isFile && file.length() > 0L }
    }
}
