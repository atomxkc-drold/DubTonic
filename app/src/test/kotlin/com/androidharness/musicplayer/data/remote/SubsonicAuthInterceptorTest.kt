package com.androidharness.musicplayer.data.remote

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class SubsonicAuthInterceptorTest {

    private lateinit var server: MockWebServer
    private lateinit var otherServer: MockWebServer
    private lateinit var client: OkHttpClient
    private lateinit var session: SubsonicSession

    @Before
    fun setUp() {
        server = MockWebServer().apply { start() }
        otherServer = MockWebServer().apply { start() }
        session = SubsonicSession(
            baseUrl = server.url("/").toString().trimEnd('/'),
            username = "listener",
            salt = "abcd1234",
            token = "0123456789abcdef0123456789abcdef",
        )
        val provider = object : SubsonicSessionProvider {
            override fun current(): SubsonicSession = session
        }
        client = OkHttpClient.Builder()
            .addInterceptor(SubsonicAuthInterceptor(provider))
            .build()
    }

    @After
    fun tearDown() {
        server.shutdown()
        otherServer.shutdown()
    }

    @Test
    fun authParametersAreAddedToConfiguredServer() {
        server.enqueue(MockResponse().setResponseCode(200).setBody("{}"))

        client.newCall(
            Request.Builder().url(server.url("/rest/ping.view")).build()
        ).execute().close()

        val request = server.takeRequest()
        val url = request.requestUrl!!
        assertEquals("listener", url.queryParameter("u"))
        assertEquals("0123456789abcdef0123456789abcdef", url.queryParameter("t"))
        assertEquals("abcd1234", url.queryParameter("s"))
        assertEquals("1.16.1", url.queryParameter("v"))
        assertEquals("HarnessMusic", url.queryParameter("c"))
        assertEquals("json", url.queryParameter("f"))
    }

    @Test
    fun credentialsAreNotSentToAnotherPort() {
        otherServer.enqueue(MockResponse().setResponseCode(200).setBody("{}"))

        client.newCall(
            Request.Builder().url(otherServer.url("/rest/ping.view")).build()
        ).execute().close()

        val url = otherServer.takeRequest().requestUrl!!
        assertNull(url.queryParameter("u"))
    }

    @Test
    fun existingAuthIsNotDuplicated() {
        server.enqueue(MockResponse().setResponseCode(200).setBody("{}"))

        client.newCall(
            Request.Builder().url(server.url("/rest/stream.view?u=explicit&id=1")).build()
        ).execute().close()

        val url = server.takeRequest().requestUrl!!
        assertEquals(listOf("explicit"), url.queryParameterValues("u"))
        assertEquals("1", url.queryParameter("id"))
    }
}
