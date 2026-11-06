package com.androidharness.musicplayer.data.remote

import com.androidharness.musicplayer.data.settings.AudioCodec
import com.androidharness.musicplayer.data.settings.TranscodeProfile
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class SubsonicUrlsTest {

    private val session = SubsonicSession(
        baseUrl = "https://music.example.com/",
        username = "listener",
        salt = "abcd1234",
        token = "0123456789abcdef0123456789abcdef",
    )

    private val provider = object : SubsonicSessionProvider {
        override fun current(): SubsonicSession = session
    }

    private val urls = SubsonicUrls(provider)

    @Test
    fun restUrlUsesMethodView() {
        assertEquals("https://music.example.com/rest/getArtists.view", urls.rest("getArtists"))
    }

    @Test
    fun streamUrlCarriesAuthAndTranscodeParameters() {
        val url = urls.streamUrl(
            trackId = "42",
            profile = TranscodeProfile(
                enabled = true,
                codec = AudioCodec.OPUS,
                maxBitrateKbps = 128,
            ),
        ).toHttpUrl()

        assertEquals("/rest/stream.view", url.encodedPath)
        assertEquals("42", url.queryParameter("id"))
        assertEquals("listener", url.queryParameter("u"))
        assertEquals("abcd1234", url.queryParameter("s"))
        assertEquals("0123456789abcdef0123456789abcdef", url.queryParameter("t"))
        assertEquals("opus", url.queryParameter("format"))
        assertEquals("128", url.queryParameter("maxBitRate"))
    }

    @Test
    fun originalProfileDoesNotRequestTranscoding() {
        val url = urls.streamUrl(
            trackId = "42",
            profile = TranscodeProfile(enabled = false, codec = AudioCodec.ORIGINAL),
        ).toHttpUrl()

        assertNull(url.queryParameter("format"))
        assertNull(url.queryParameter("maxBitRate"))
    }

    @Test
    fun coverArtUrlIncludesAuth() {
        val url = urls.coverArtUrl("cover-7", size = 600).toHttpUrl()
        assertEquals("/rest/getCoverArt.view", url.encodedPath)
        assertEquals("cover-7", url.queryParameter("id"))
        assertEquals("600", url.queryParameter("size"))
        assertEquals("listener", url.queryParameter("u"))
    }
}
