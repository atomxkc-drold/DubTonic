package com.androidharness.musicplayer.playback

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.data.connectivity.ConnectivityMonitor
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import com.androidharness.musicplayer.playback.audio.DspRenderersFactory
import com.androidharness.musicplayer.playback.audio.ParametricEqProcessor
import com.androidharness.musicplayer.playback.audio.ReplayGainController
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Singleton
class PlaybackEngine @Inject constructor(
    @ApplicationContext private val context: Context,
    private val buffering: BufferingPolicy,
    private val settings: SettingsRepository,
    private val connectivity: ConnectivityMonitor,
    private val equalizer: ParametricEqProcessor,
    private val replayGain: ReplayGainController,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private var settingsJob: Job? = null
    private var exoPlayer: ExoPlayer? = null
    private var consecutiveErrors = 0

    val player: ExoPlayer
        get() = checkNotNull(exoPlayer) { "PlaybackEngine.ensureCreated() was not called" }

    @OptIn(UnstableApi::class)
    fun ensureCreated() {
        if (exoPlayer != null) return

        val snapshot = settings.settings.value
        equalizer.update(snapshot.playback.equalizer)

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(C.USAGE_MEDIA)
            .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
            .build()

        val renderers = DspRenderersFactory(
            context,
            listOf(replayGain.processor, equalizer),
        ).setEnableAudioFloatOutput(true)

        exoPlayer = ExoPlayer.Builder(context, renderers)
            .setLoadControl(buffering.build(snapshot, connectivity.current()))
            .setAudioAttributes(audioAttributes, /* handleAudioFocus = */ true)
            .setHandleAudioBecomingNoisy(true)
            .setSeekBackIncrementMs(5_000)
            .setSeekForwardIncrementMs(15_000)
            .build()
            .apply {
                setWakeMode(C.WAKE_MODE_NETWORK)
                repeatMode = Player.REPEAT_MODE_OFF
            }

        settingsJob = scope.launch {
            settings.settings
                .map { it.playback.equalizer }
                .distinctUntilChanged()
                .collect(equalizer::update)
        }
    }

    fun onTrackChanged(track: Track?) {
        replayGain.onTrack(track)
    }

    /**
     * Retries the current item twice, then moves on. A dead network must not freeze the queue.
     */
    fun onPlaybackError(error: PlaybackException) {
        val player = exoPlayer ?: return
        consecutiveErrors += 1

        when {
            consecutiveErrors <= 2 -> player.prepare()
            player.hasNextMediaItem() -> {
                consecutiveErrors = 0
                player.seekToNextMediaItem()
                player.prepare()
            }
            else -> player.pause()
        }
    }

    fun onPlaybackSuccess() {
        consecutiveErrors = 0
    }

    fun release() {
        settingsJob?.cancel()
        settingsJob = null
        exoPlayer?.release()
        exoPlayer = null
    }
}
