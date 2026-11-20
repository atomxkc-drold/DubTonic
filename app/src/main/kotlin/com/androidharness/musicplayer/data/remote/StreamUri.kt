package com.androidharness.musicplayer.data.remote

import java.net.URLDecoder
import java.net.URLEncoder

/**
 * Credential-free playback URI for a streamed track.
 *
 * The queue stores this stable URI instead of a fully built Subsonic URL, so the stream
 * request is built when the song actually opens, using the network profile and auth token
 * that are current at that moment. A URL baked in on WiFi would otherwise keep asking for
 * an untranscoded lossless file after the phone has moved to mobile data.
 *
 * The URI carries no token, no salt and no transcode parameters, so it is safe to keep in
 * the player queue, in the media session and in the persisted resumption store.
 */
object StreamUri {

    const val SCHEME: String = "dubtonic"

    private const val PREFIX = "$SCHEME://stream/"

    fun forTrack(trackId: String): String =
        PREFIX + URLEncoder.encode(trackId, Charsets.UTF_8.name())

    /** Returns the track id encoded in [uri], or null for any other URI. */
    fun trackIdOf(uri: String): String? {
        if (!uri.startsWith(PREFIX)) return null
        val encoded = uri.removePrefix(PREFIX).substringBefore('?').substringBefore('#')
        if (encoded.isEmpty()) return null
        return runCatching { URLDecoder.decode(encoded, Charsets.UTF_8.name()) }.getOrNull()
    }
}
