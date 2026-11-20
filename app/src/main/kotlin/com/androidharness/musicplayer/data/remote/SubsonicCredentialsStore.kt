package com.androidharness.musicplayer.data.remote

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Stores a salted token, never the account password. The token keeps working across
 * restarts exactly like a password would, but a preferences leak does not expose the secret.
 */
@Singleton
class SubsonicCredentialsStore @Inject constructor(
    @ApplicationContext context: Context,
) : SubsonicSessionProvider {

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val state = MutableStateFlow(readSession())

    /** Observable form of [current], so headers can show the signed-in user. */
    val session: StateFlow<SubsonicSession?> = state.asStateFlow()

    override fun current(): SubsonicSession? = state.value

    fun require(): SubsonicSession = state.value ?: throw SubsonicNotConfiguredException()

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

    /** Updates the negotiated API version after a successful ping. */
    fun updateApiVersion(apiVersion: String) {
        val current = state.value ?: return
        if (current.apiVersion == apiVersion) return
        persist(current.copy(apiVersion = apiVersion))
    }

    fun clear() {
        preferences.edit().clear().apply()
        state.value = null
    }

    private fun persist(session: SubsonicSession) {
        preferences.edit()
            .putString(KEY_BASE_URL, session.baseUrl)
            .putString(KEY_USERNAME, session.username)
            .putString(KEY_SALT, session.salt)
            .putString(KEY_TOKEN, session.token)
            .putString(KEY_API_VERSION, session.apiVersion)
            .apply()
        state.value = session
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
            apiVersion = preferences.getString(KEY_API_VERSION, null) ?: DEFAULT_API_VERSION,
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
        const val KEY_API_VERSION = "api_version"
        const val DEFAULT_API_VERSION = "1.16.1"
    }
}

class SubsonicNotConfiguredException : IllegalStateException("Subsonic server is not configured")
