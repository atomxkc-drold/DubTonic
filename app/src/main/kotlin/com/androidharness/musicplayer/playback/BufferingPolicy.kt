package com.androidharness.musicplayer.playback

import androidx.media3.common.C
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.upstream.DefaultAllocator
import com.androidharness.musicplayer.core.model.NetworkType
import com.androidharness.musicplayer.data.settings.AppSettings
import com.androidharness.musicplayer.data.settings.BufferProfile
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Turns the user's buffer settings into a Media3 DefaultLoadControl.
 *
 * Media3's supported customization point is the builder. Time thresholds stay authoritative
 * over the byte ceiling, so a large MB setting cannot starve the next track's preload.
 */
@Singleton
class BufferingPolicy @Inject constructor() {

    fun build(settings: AppSettings, network: NetworkType): DefaultLoadControl {
        val buffer = settings.playback.bufferFor(network)
        return DefaultLoadControl.Builder()
            .setAllocator(DefaultAllocator(true, C.DEFAULT_BUFFER_SEGMENT_SIZE))
            .setBufferDurationsMsForStreaming(
                buffer.minBufferMs.toInt(),
                buffer.maxBufferMs.toInt(),
                buffer.bufferForPlaybackMs.toInt(),
                buffer.bufferForPlaybackAfterRebufferMs.toInt(),
            )
            .setBufferDurationsMsForLocalPlayback(
                buffer.minBufferMs.toInt(),
                buffer.maxBufferMs.toInt(),
                buffer.bufferForPlaybackMs.toInt(),
                buffer.bufferForPlaybackAfterRebufferMs.toInt(),
            )
            .setTargetBufferBytes(buffer.targetBytes())
            .setPrioritizeTimeOverSizeThresholdsForStreaming(true)
            .setPrioritizeTimeOverSizeThresholdsForLocalPlayback(false)
            .setBackBuffer(buffer.backBufferMs.toInt(), true)
            .build()
    }
}

private fun BufferProfile.targetBytes(): Int =
    (targetBufferMb.coerceIn(8, 512).toLong() * 1024L * 1024L)
        .coerceAtMost(Int.MAX_VALUE.toLong())
        .toInt()
