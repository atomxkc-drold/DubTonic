package com.androidharness.musicplayer.data.remote

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Stores a salted token, never the account password. The token keeps working across
 * restarts exactly like a password would, but a preferences leak does not expose the secret.
 */
@Singleton
class SubsonicCredentialsStore @Inject constructor(
    @ApplicationContext context: Context,
) : SubsonicSessionProvider {

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    @Volatile
    private var cached: SubsonicSession? = readSession()

    override fun current(): SubsonicSession? = cached

    fun require(): SubsonicSession = cached ?: throw SubsonicNotConfiguredException()

    fun save(baseUrl: String, username: String, password: String): SubsonicSession {
        val salt = SubsonicAuth.newSalt()
        val session = SubsonicSession(
            baseUrl = normalizeBaseUrl(baseUrl),
            username = username.trim(),
            salt = salt,
            token = SubsonicAuth.deriveToken(password, salt),
        )
        persist(session)
        return session
    }

    fun saveRaw(baseUrl: String, username: String, salt: String, token: String): SubsonicSession {
        val session = SubsonicSession(
            baseUrl = normalizeBaseUrl(baseUrl),
            username = username,
            salt = salt,
            token = token,
        )
        persist(session)
        return session
    }

    fun clear() {
        preferences.edit().clear().apply()
        cached = null
    }

    private fun persist(session: SubsonicSession) {
        preferences.edit()
            .putString(KEY_BASE_URL, session.baseUrl)
            .putString(KEY_USERNAME, session.username)
            .putString(KEY_SALT, session.salt)
            .putString(KEY_TOKEN, session.token)
            .apply()
        cached = session
    }

    private fun readSession(): SubsonicSession? {
        val baseUrl = preferences.getString(KEY_BASE_URL, null) ?: return null
        val username = preferences.getString(KEY_USERNAME, null) ?: return null
        val salt = preferences.getString(KEY_SALT, null) ?: return null
        val token = preferences.getString(KEY_TOKEN, null) ?: return null
        return SubsonicSession(
            baseUrl = normalizeBaseUrl(baseUrl),
            username = username,
            salt = salt,
            token = token,
        )
    }

    private fun normalizeBaseUrl(value: String): String {
        var normalized = value.trim().trimEnd('/')
        if (!normalized.startsWith("http://") && !normalized.startsWith("https://")) {
            normalized = "https://$normalized"
        }
        return normalized
    }

    private companion object {
        const val PREFS_NAME = "subsonic_session"
        const val KEY_BASE_URL = "base_url"
        const val KEY_USERNAME = "username"
        const val KEY_SALT = "salt"
        const val KEY_TOKEN = "token"
    }
}

class SubsonicNotConfiguredException : IllegalStateException("Subsonic server is not configured")
