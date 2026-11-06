package com.androidharness.musicplayer.data.remote

import javax.inject.Inject
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Adds Subsonic token auth to REST calls. The token is only attached to the configured
 * server host, so a redirect or a third-party URL never receives the credentials.
 */
class SubsonicAuthInterceptor @Inject constructor(
    private val sessionProvider: SubsonicSessionProvider,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val session = sessionProvider.current()
            ?: return chain.proceed(chain.request())

        val request = chain.request()
        val serverUrl = runCatching { session.baseUrl.toHttpUrl() }.getOrNull()
            ?: return chain.proceed(request)

        if (request.url.host != serverUrl.host || request.url.port != serverUrl.port) {
            return chain.proceed(request)
        }
        if (request.url.queryParameter("u") != null) return chain.proceed(request)

        val url = request.url.newBuilder()
            .addQueryParameter("u", session.username)
            .addQueryParameter("t", session.token)
            .addQueryParameter("s", session.salt)
            .addQueryParameter("v", session.apiVersion)
            .addQueryParameter("c", session.clientId)
            .addQueryParameter("f", "json")
            .build()

        return chain.proceed(request.newBuilder().url(url).build())
    }
}
