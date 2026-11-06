package com.androidharness.musicplayer.data.remote

import java.security.MessageDigest
import java.security.SecureRandom

data class SubsonicSession(
    val baseUrl: String,
    val username: String,
    val salt: String,
    val token: String,
    val apiVersion: String = "1.16.1",
    val clientId: String = "HarnessMusic",
)

interface SubsonicSessionProvider {
    fun current(): SubsonicSession?
}

object SubsonicAuth {

    private val random = SecureRandom()

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
