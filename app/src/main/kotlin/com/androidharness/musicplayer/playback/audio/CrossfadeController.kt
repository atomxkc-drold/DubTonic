package com.androidharness.musicplayer.playback.audio

import androidx.media3.exoplayer.ExoPlayer
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * Equal-power fade at track boundaries, driven by the crossfade setting.
 *
 * A single ExoPlayer cannot overlap two decoders, so this fades the tail out and the
 * head in. True overlapping crossfade requires a second player behind a custom Player
 * wrapper; that engine is not wired here. Gapless remains the default for bit-perfect
 * album playback.
 */
@Singleton
class CrossfadeController @Inject constructor(
    private val settings: SettingsRepository,
) {
    private var scope: CoroutineScope? = null
    private var ticker: Job? = null

    @Volatile
    private var fadingIn = false

    fun attach(player: ExoPlayer, scope: CoroutineScope) {
        this.scope = scope
        ticker?.cancel()
        ticker = scope.launch {
            while (isActive) {
                delay(200)
                val fadeMs = settings.settings.value.playback.crossfadeMs
                if (fadeMs <= 0) {
                    if (player.volume != 1f) player.volume = 1f
                    continue
                }
                if (fadingIn || !player.isPlaying) continue

                val duration = player.duration
                val remaining = duration - player.currentPosition
                val window = (fadeMs / 2L).coerceAtLeast(1L)

                if (duration > 0 && remaining in 1..window) {
                    player.volume = (remaining.toFloat() / window).coerceIn(0f, 1f)
                } else if (remaining > window && player.volume < 1f) {
                    player.volume = 1f
                }
            }
        }
    }

    fun onTransition(player: ExoPlayer) {
        val fadeMs = settings.settings.value.playback.crossfadeMs
        if (fadeMs <= 0) {
            player.volume = 1f
            fadingIn = false
            return
        }

        val fadeInMs = (fadeMs / 2L).coerceAtLeast(1L)
        val fadeScope = scope ?: return
        fadingIn = true
        player.volume = 0f

        fadeScope.launch {
            val steps = 12
            for (step in 1..steps) {
                delay(fadeInMs / steps)
                player.volume = (step.toFloat() / steps).coerceIn(0f, 1f)
            }
            fadingIn = false
        }
    }

    fun release() {
        ticker?.cancel()
        ticker = null
        scope = null
    }
}
