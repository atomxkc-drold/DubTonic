package com.androidharness.musicplayer.data.remote

import com.androidharness.musicplayer.data.remote.dto.PingBody
import com.androidharness.musicplayer.data.remote.dto.SearchBody
import com.androidharness.musicplayer.data.remote.dto.SubsonicEnvelope
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class SubsonicParsingTest {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        explicitNulls = false
    }

    @Test
    fun searchResponseMapsToDomainTrack() {
        val payload = """
            {"subsonic-response":{
              "status":"ok","version":"1.16.1",
              "searchResult3":{
                "song":[{
                  "id":"300","parent":"200","title":"Night Drive","album":"Neon",
                  "artist":"Kavinsky","artistId":"100","coverArt":"cover-200",
                  "duration":180,"bitRate":320,"samplingRate":44100,"channelCount":2,
                  "suffix":"flac","size":12345678,"track":3,"discNumber":1,"year":2013,
                  "genre":"Synthwave","created":"2016-03-21T14:54:54.000Z",
                  "replayGain":{"trackGain":-4.5,"albumGain":-3.25}
                }]
              }
            }}
        """.trimIndent()

        val body = json
            .decodeFromString<SubsonicEnvelope<SearchBody>>(payload)
            .validated()

        val song = body.searchResult3!!.song.single()
        val track = song.toTrack(syncedAtMs = 1_234L)

        assertEquals("Night Drive", track.title)
        assertEquals(180_000L, track.durationMs)
        assertEquals("Neon", track.album)
        assertEquals(320, track.bitrateKbps)
        assertEquals("flac", track.format)
        assertEquals(-4.5f, track.replayGainTrackDb!!, 0f)
        assertEquals(1_234L, track.lastSyncedAtMs)
        assertEquals(1_458_572_094_000L, track.addedAtMs)
    }

    @Test
    fun failedStatusThrowsSubsonicException() {
        val payload =
            """{"subsonic-response":{"status":"failed","error":{"code":40,"message":"Wrong username or password"}}}"""

        val envelope = json.decodeFromString<SubsonicEnvelope<PingBody>>(payload)
        val error = assertThrows(SubsonicException::class.java) { envelope.validated() }
        assertEquals(40, error.code)
        assertEquals("Wrong username or password", error.message)
    }
}
