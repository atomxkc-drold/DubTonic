package com.androidharness.musicplayer.playback

import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.ResolvingDataSource
import com.androidharness.musicplayer.data.connectivity.ConnectivityMonitor
import com.androidharness.musicplayer.data.remote.PrivacyGateInterceptor
import com.androidharness.musicplayer.data.remote.StreamUri
import com.androidharness.musicplayer.data.remote.SubsonicNotConfiguredException
import com.androidharness.musicplayer.data.remote.SubsonicUrls
import com.androidharness.musicplayer.data.settings.TranscodeProfile
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Turns a queued [StreamUri] into a real Subsonic request at open time.
 *
 * Resolution here, and not when the item is queued, is what makes the network profile
 * apply to the song that is starting right now: a queue built on WiFi switches to the
 * cellular transcode and buffer settings as soon as mobile data is the active network.
 *
 * The explicit cache key keeps read-through cache entries per track *and* per profile, so
 * a lossless copy cached on WiFi is never served as if it were the transcoded stream.
 */
@OptIn(UnstableApi::class)
@Singleton
class StreamUrlResolver @Inject constructor(
    private val urls: SubsonicUrls,
    private val settings: SettingsRepository,
    private val connectivity: ConnectivityMonitor,
    private val fallback: StreamPlaybackFallback,
) : ResolvingDataSource.Resolver {

    override fun resolveDataSpec(dataSpec: DataSpec): DataSpec {
        val trackId = StreamUri.trackIdOf(dataSpec.uri.toString()) ?: return dataSpec
        if (privacyBlocksNetwork()) {
            throw IOException(PrivacyGateInterceptor.PRIVACY_MESSAGE)
        }
        val profile = profile()
        val streamUrl = try {
            urls.streamUrl(trackId, profile)
        } catch (error: SubsonicNotConfiguredException) {
            throw IOException("Subsonic server is not configured", error)
        }
        return dataSpec.buildUpon()
            .setUri(streamUrl)
            .setKey(cacheKey(trackId, profile))
            .build()
    }

    /** Transcode profile that applies to the network the phone is on right now. */
    fun profile(): TranscodeProfile {
        if (fallback.preferOriginal) return TranscodeProfile()
        return settings.settings.value.network.transcodeFor(connectivity.current())
    }

    /**
     * A queued stream still resolves when privacy mode is on, but only to a track that is
     * already downloaded: the request never leaves the phone.
     */
    fun privacyBlocksNetwork(): Boolean = settings.settings.value.ui.privacyKillSwitch

    private fun cacheKey(trackId: String, profile: TranscodeProfile): String {
        val codec = if (profile.enabled) profile.codec.name else "ORIGINAL"
        return "dubtonic-stream|$trackId|$codec|${profile.maxBitrateKbps}"
    }
}

/**
 * One-shot switch that asks the next stream request for the untranscoded file.
 *
 * Set by [PlaybackEngine] when a transcoded stream keeps failing, so a single track the
 * server cannot transcode does not end the queue on mobile data.
 */
@Singleton
class StreamPlaybackFallback @Inject constructor() {

    @Volatile
    var preferOriginal: Boolean = false
}
