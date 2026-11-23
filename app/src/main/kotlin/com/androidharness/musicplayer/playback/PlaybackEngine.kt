package com.androidharness.musicplayer.playback

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
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

@OptIn(UnstableApi::class)
@Singleton
class PlaybackEngine @Inject constructor(
    @ApplicationContext private val context: Context,
    private val buffering: BufferingPolicy,
    private val settings: SettingsRepository,
    private val connectivity: ConnectivityMonitor,
    private val replayGain: ReplayGainController,
    private val dataSourceFactory: DataSource.Factory,
    private val streamFallback: StreamPlaybackFallback,
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

        // Fresh processor instances per player build. Media3 binds an AudioProcessor to one
        // audio sink, so reusing the injected singletons across a release()/rebuild fed the
        // new player buffers stamped by the old one — the source of the screeching tracks.
        val equalizer = ParametricEqProcessor().also { it.update(snapshot.playback.equalizer) }

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(C.USAGE_MEDIA)
            .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
            .build()

        val renderers = DspRenderersFactory(
            context,
            listOf(replayGain.newProcessor(), equalizer),
        ).setEnableAudioFloatOutput(true)

        val mediaSourceFactory = DefaultMediaSourceFactory(context)
            .setDataSourceFactory(dataSourceFactory)

        exoPlayer = ExoPlayer.Builder(context, renderers)
            .setMediaSourceFactory(mediaSourceFactory)
            .setLoadControl(buffering.build(snapshot, connectivity.current()))
            .setAudioAttributes(audioAttributes, /* handleAudioFocus = */ true)
            .setHandleAudioBecomingNoisy(snapshot.playback.pauseOnHeadphonesDisconnect)
            .setSeekBackIncrementMs(5_000)
            .setSeekForwardIncrementMs(15_000)
            .build()
            .apply {
                setWakeMode(C.WAKE_MODE_NETWORK)
                skipSilenceEnabled = snapshot.playback.skipSilence
                repeatMode = Player.REPEAT_MODE_OFF
            }

        // ExoPlayer is built on the service's main thread and rejects calls from any
        // other thread, so every settings write that touches the player stays on Main.
        settingsJob = scope.launch(Dispatchers.Main.immediate) {
            launch {
                settings.settings
                    .map { it.playback.equalizer }
                    .distinctUntilChanged()
                    .collect(equalizer::update)
            }
            launch {
                settings.settings
                    .map { it.playback.skipSilence }
                    .distinctUntilChanged()
                    .collect { enabled -> exoPlayer?.skipSilenceEnabled = enabled }
            }
        }
    }

    fun onTrackChanged(track: Track?) {
        // A previous track's fallback must not leak into the next song.
        streamFallback.preferOriginal = false
        replayGain.onTrack(track)
    }

    /**
     * Retries the current item twice, then moves on. The second retry asks for the
     * untranscoded stream, so a track the server cannot transcode on mobile data still
     * plays instead of being skipped. A dead network must not freeze the queue.
     */
    fun onPlaybackError(error: PlaybackException) {
        val player = exoPlayer ?: return
        consecutiveErrors += 1

        when {
            consecutiveErrors == 1 -> player.prepare()
            consecutiveErrors == 2 && !streamFallback.preferOriginal -> {
                streamFallback.preferOriginal = true
                player.prepare()
            }
            player.hasNextMediaItem() -> {
                consecutiveErrors = 0
                streamFallback.preferOriginal = false
                player.seekToNextMediaItem()
                player.prepare()
            }
            else -> {
                streamFallback.preferOriginal = false
                player.pause()
            }
        }
    }

    fun onPlaybackSuccess() {
        consecutiveErrors = 0
        streamFallback.preferOriginal = false
    }

    fun release() {
        settingsJob?.cancel()
        settingsJob = null
        exoPlayer?.release()
        exoPlayer = null
    }
}
