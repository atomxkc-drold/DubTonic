package com.androidharness.musicplayer.playback.audio

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.audio.AudioProcessor
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.audio.AudioOffloadSupport
import androidx.media3.exoplayer.audio.AudioSink
import androidx.media3.exoplayer.audio.DefaultAudioSink

/**
 * Builds the audio sink with ReplayGain then the parametric EQ in front of the
 * platform output. DefaultAudioProcessorChain places user processors before
 * silence skipping and speed adjustment.
 */
@OptIn(UnstableApi::class)
class DspRenderersFactory(
    context: Context,
    private val processors: List<AudioProcessor>,
) : DefaultRenderersFactory(context) {

    override fun buildAudioSink(
        context: Context,
        enableFloatOutput: Boolean,
        enableAudioOutputPlaybackParams: Boolean,
    ): AudioSink = DefaultAudioSink.Builder(context)
        .setEnableFloatOutput(enableFloatOutput)
        .setEnableAudioOutputPlaybackParameters(enableAudioOutputPlaybackParams)
        // Force software AudioTrack. Hardware offload is enabled by default on capable
        // devices and has historically glitched when the sample rate changes between
        // gapless tracks (chipmunk / pitch-shifted audio). Software mode reconfigures the
        // AudioTrack cleanly for every format change.
        .setAudioOffloadSupportProvider { _, _ -> AudioOffloadSupport.DEFAULT_UNSUPPORTED }
        .setAudioProcessorChain(
            DefaultAudioSink.DefaultAudioProcessorChain(*processors.toTypedArray())
        )
        .build()
}
