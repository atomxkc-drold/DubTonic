package com.androidharness.musicplayer.playback.audio

import androidx.media3.common.audio.GainProcessor
import androidx.media3.common.util.UnstableApi
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.data.settings.ReplayGainMode
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.pow

/**
 * ReplayGain as sample-level gain automation through Media3's GainProcessor.
 * Track/album mode and preamp come from the settings engine.
 */
@UnstableApi
@Singleton
class ReplayGainController @Inject constructor(
    private val settings: SettingsRepository,
) {
    @Volatile
    private var gain = 1f

    private val gainProvider = object : GainProcessor.GainProvider {
        override fun getGainFactorAtSamplePosition(samplePosition: Long, sampleRate: Int): Float =
            gain

        override fun isUnityUntil(samplePosition: Long, sampleRate: Int): Long =
            if (gain == 1f) samplePosition else samplePosition + UNITY_WINDOW_SAMPLES
    }

    val processor: GainProcessor = GainProcessor(gainProvider)

    fun onTrack(track: Track?) {
        val replayGain = settings.settings.value.playback.replayGain
        if (!replayGain.enabled || replayGain.mode == ReplayGainMode.OFF) {
            gain = 1f
            return
        }

        val decibels = when (replayGain.mode) {
            ReplayGainMode.ALBUM -> track?.replayGainAlbumDb ?: track?.replayGainTrackDb
            ReplayGainMode.TRACK -> track?.replayGainTrackDb ?: track?.replayGainAlbumDb
            ReplayGainMode.OFF -> null
        } ?: 0f

        val linear = 10f.pow((decibels + replayGain.preampDb) / 20f)
        gain = if (replayGain.preventClipping) linear.coerceAtMost(1f) else linear
    }

    fun refresh() {
        gain = 1f
    }

    private companion object {
        const val UNITY_WINDOW_SAMPLES = 100_000_000L
    }
}
