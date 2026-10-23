package com.dubtonic.player.player

import android.content.Context
import androidx.media3.common.AudioAttributes
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.ExoPlayer.Builder
import com.dubtonic.player.data.model.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DelayKt
import kotlinx.coroutines.SupervisorKt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.StateFlowKt
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Smart Crossfade & Gapless Transition Engine — completely original feature for DubTonic.
 *
 * Manages two ExoPlayer instances (primary + secondary) so that when the current track
 * nears its end, the next track is preloaded and volume-faded in while the current
 * track fades out. The result is gapless, DJ-style crossfade playback instead of the
 * default hard stop between tracks.
 *
 * Integration with PlayerController:
 *   1. Create the engine once the primary ExoPlayer exists:
 *        val crossfade = CrossfadeEngine(context, scope, primaryPlayer)
 *        crossfade.setEnabled(true, 3000)
 *   2. Before advancing to the next track:
 *        crossfade.prepareNext(nextSong, streamUrl)
 *   3. On STATE_ENDED (or manually), call crossfade.completeCrossfade() which fades
 *      primary out and secondary in, then promote the secondary via getSecondary().
 */
class CrossfadeEngine private constructor(
    context: Context,
    private val scope: CoroutineScope,
    private val primary: ExoPlayer
) {
    private val secondary = Builder(context).build().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build(),
            true
        )
    }
    private val _crossfadeEnabled = MutableStateFlow(false)
    val crossfadeEnabled: StateFlow<Boolean> = _crossfadeEnabled.asStateFlow()
    private val _crossfadeProgress = MutableStateFlow(0f)
    val crossfadeProgress: StateFlow<Float> = _crossfadeProgress.asStateFlow()

    private var crossfadeDurationMs = 3000L
    private var isFading = AtomicBoolean(false)
    private var pendingSong: Song? = null

    private val listener = object : Player.Listener {
        override fun onPlaybackStateChanged(@Player.StateInt state: Int) {
            if (state == Player.STATE_ENDED && _crossfadeEnabled.value) {
                scope.launch { completeCrossfade() }
            }
        }
    }

    init {
        primary.addListener(listener)
    }

    /** Enable or disable crossfade with a configurable duration (ms). */
    fun setEnabled(enabled: Boolean, durationMs: Long = 3000) {
        this.crossfadeDurationMs = durationMs.coerceIn(500, 15000)
        _crossfadeEnabled.value = enabled
    }

    /** Preload the next track on the secondary player before a transition. */
    fun prepareNext(song: Song, streamUrl: String) {
        pendingSong = song
        val mediaItem = MediaItem.fromUri(streamUrl)
        secondary.setMediaItem(mediaItem)
        secondary.prepare()
        secondary.setVolume(0f)
    }

    /** Execute the crossfade: fade primary out, fade secondary in, then swap. */
    suspend fun completeCrossfade() {
        if (!isFading.compareAndSet(false, true)) return
        try {
            val song = pendingSong ?: return
            if (!_crossfadeEnabled.value) {
                // No crossfade: swap immediately.
                secondary.setVolume(1f)
                secondary.play()
                return
            }

            val start = System.currentTimeMillis()
            val end = start + crossfadeDurationMs
            val primaryStartVol = primary.volume
            while (true) {
                val now = System.currentTimeMillis()
                val t = if (now >= end) 1f else ((now - start).toFloat() / crossfadeDurationMs.toFloat())
                val p = t.coerceIn(0f, 1f)
                _crossfadeProgress.value = p

                primary.setVolume(primaryStartVol * (1f - p))
                secondary.setVolume(p)

                if (p >= 1f) break
                DelayKt.delay(16) // ~60fps
            }

            // Promote secondary: stop primary, play secondary at full volume.
            primary.stop()
            primary.clearMediaItems()
            secondary.setVolume(1f)
            secondary.play()
            pendingSong = null
        } finally {
            isFading.set(false)
            _crossfadeProgress.value = 0f
        }
    }

    fun getSecondary(): ExoPlayer = secondary
    fun getPrimary(): ExoPlayer = primary

    fun release() {
        primary.removeListener(listener)
        primary.release()
        secondary.release()
    }
}

/** Holder so PlayerController can rebuild the engine around whichever player is active. */
class CrossfadeHolder(
    context: Context,
    scope: CoroutineScope,
    initialPlayer: ExoPlayer
) {
    private var engine: CrossfadeEngine? = null
    private var active: ExoPlayer = initialPlayer

    fun init() {
        engine = CrossfadeEngine(context, scope, active)
    }

    val engineOrNull: CrossfadeEngine? get() = engine
    val activePlayer: ExoPlayer get() = active

    /** Rebuild the engine around the secondary player after a crossfade promotes it. */
    fun promoteSecondary() {
        val sec = engine?.getSecondary()
        if (sec != null) {
            active = sec
            engine?.release()
            engine = CrossfadeEngine(context, scope, active)
            engine?.init()
        }
    }

    fun release() {
        engine?.release()
        active.release()
    }
}
