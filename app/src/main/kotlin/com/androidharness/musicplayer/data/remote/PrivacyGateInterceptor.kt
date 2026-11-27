package com.androidharness.musicplayer.data.remote

import com.androidharness.musicplayer.domain.repository.SettingsRepository
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Privacy kill-switch for every outbound request.
 *
 * When the user turns on "Privacy & Telemetry Kill-Switch" no artwork, metadata or audio
 * request leaves the phone: the interceptor answers locally with a synthetic response, so
 * callers fail fast instead of blocking, and cached content keeps working because the cache
 * is only consulted for requests that reach the network stack.
 *
 * Play history, stats and preferences are never touched here; they stay on-device.
 */
@Singleton
class PrivacyGateInterceptor @Inject constructor(
    private val settings: SettingsRepository,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!settings.settings.value.ui.privacyKillSwitch) {
            return chain.proceed(chain.request())
        }
        throw IOException(PRIVACY_MESSAGE)
    }

    companion object {
        const val PRIVACY_MESSAGE: String =
            "Privacy mode is on: external artwork, metadata and stream lookups are blocked"
    }
}
