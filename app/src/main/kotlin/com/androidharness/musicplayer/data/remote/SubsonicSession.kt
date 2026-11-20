package com.androidharness.musicplayer.data.remote

import java.security.MessageDigest
import java.security.SecureRandom

data class SubsonicSession(
    val baseUrl: String,
    val username: String,
    val salt: String,
    val token: String,
    val apiVersion: String = "1.16.1",
    val clientId: String = "DubTonic",
)

interface SubsonicSessionProvider {
    fun current(): SubsonicSession?
}

object SubsonicAuth {

    private val random = SecureRandom()

    /** Single source of truth for Subsonic auth query parameters, used by Retrofit and ExoPlayer/Coil URLs. */
    fun applyParams(builder: okhttp3.HttpUrl.Builder, session: SubsonicSession) {
        builder.addQueryParameter("u", session.username)
        builder.addQueryParameter("t", session.token)
        builder.addQueryParameter("s", session.salt)
        builder.addQueryParameter("v", session.apiVersion)
        builder.addQueryParameter("c", session.clientId)
    }

    /**
     * Negotiates the API version with the server (from ping). Falls back to [fallback]
     * when the server reports nothing, and never exceeds [clientVersion].
     */
    fun negotiate(clientVersion: String, serverVersion: String?, fallback: String = "1.13.0"): String {
        val server = serverVersion?.trim()?.takeIf { it.isNotEmpty() } ?: return fallback
        return if (compareVersions(server, clientVersion) <= 0) server else clientVersion
    }

    private fun compareVersions(a: String, b: String): Int {
        val left = a.split('.').map { it.toIntOrNull() ?: 0 }
        val right = b.split('.').map { it.toIntOrNull() ?: 0 }
        for (i in 0 until maxOf(left.size, right.size)) {
            val diff = (left.getOrElse(i) { 0 } - right.getOrElse(i) { 0 })
            if (diff != 0) return diff
        }
        return 0
    }

    fun newSalt(): String {
        val bytes = ByteArray(8)
        random.nextBytes(bytes)
        return bytes.toHex()
    }

    fun deriveToken(password: String, salt: String): String = md5Hex(password + salt)

    fun md5Hex(value: String): String =
        MessageDigest.getInstance("MD5")
            .digest(value.toByteArray(Charsets.UTF_8))
            .toHex()

    private fun ByteArray.toHex(): String =
        joinToString(separator = "") { byte -> "%02x".format(byte.toInt() and 0xff) }
}
