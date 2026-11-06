package com.androidharness.musicplayer.data.remote

import com.androidharness.musicplayer.data.settings.AudioCodec
import com.androidharness.musicplayer.data.settings.TranscodeProfile
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

@Singleton
class SubsonicUrls @Inject constructor(
    private val sessionProvider: SubsonicSessionProvider,
) {

    fun rest(method: String): String = "${baseUrl()}/rest/$method.view"

    fun streamUrl(trackId: String, profile: TranscodeProfile): String {
        val session = requireSession()
        val builder = "${baseUrl()}/rest/stream.view".toHttpUrl().newBuilder()
            .addQueryParameter("id", trackId)

        if (profile.enabled && profile.codec != AudioCodec.ORIGINAL) {
            builder.addQueryParameter("format", profile.codec.parameterValue())
            if (profile.maxBitrateKbps > 0) {
                builder.addQueryParameter("maxBitRate", profile.maxBitrateKbps.toString())
            }
        }

        return builder.withAuth(session, includeJson = false).build().toString()
    }

    fun downloadUrl(trackId: String): String {
        val session = requireSession()
        return "${baseUrl()}/rest/download.view".toHttpUrl().newBuilder()
            .addQueryParameter("id", trackId)
            .withAuth(session, includeJson = false)
            .build()
            .toString()
    }

    fun coverArtUrl(coverArtId: String, size: Int? = null): String {
        val session = requireSession()
        val builder = "${baseUrl()}/rest/getCoverArt.view".toHttpUrl().newBuilder()
            .addQueryParameter("id", coverArtId)
        if (size != null) builder.addQueryParameter("size", size.toString())
        return builder.withAuth(session, includeJson = false).build().toString()
    }

    private fun baseUrl(): String =
        sessionProvider.current()?.baseUrl?.trimEnd('/')
            ?: throw SubsonicNotConfiguredException()

    private fun requireSession(): SubsonicSession =
        sessionProvider.current() ?: throw SubsonicNotConfiguredException()

    private fun HttpUrl.Builder.withAuth(
        session: SubsonicSession,
        includeJson: Boolean,
    ): HttpUrl.Builder = apply {
        addQueryParameter("u", session.username)
        addQueryParameter("t", session.token)
        addQueryParameter("s", session.salt)
        addQueryParameter("v", session.apiVersion)
        addQueryParameter("c", session.clientId)
        if (includeJson) addQueryParameter("f", "json")
    }
}

private fun AudioCodec.parameterValue(): String = when (this) {
    AudioCodec.ORIGINAL -> "raw"
    AudioCodec.MP3 -> "mp3"
    AudioCodec.AAC -> "aac"
    AudioCodec.OPUS -> "opus"
    AudioCodec.FLAC -> "flac"
}
