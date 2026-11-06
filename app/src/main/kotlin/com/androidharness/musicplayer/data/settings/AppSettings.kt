package com.androidharness.musicplayer.data.settings

import com.androidharness.musicplayer.core.model.NetworkType
import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val schemaVersion: Int = CURRENT_SCHEMA_VERSION,
    val playback: PlaybackSettings = PlaybackSettings(),
    val ui: UiSettings = UiSettings(),
    val library: LibrarySettings = LibrarySettings(),
    val cache: CacheSettings = CacheSettings(),
    val network: NetworkSettings = NetworkSettings(),
) {
    companion object {
        const val CURRENT_SCHEMA_VERSION: Int = 1
    }
}

@Serializable
data class BufferProfile(
    val targetBufferMb: Int = 64,
    val minBufferMs: Long = 30_000,
    val maxBufferMs: Long = 600_000,
    val bufferForPlaybackMs: Long = 1_500,
    val bufferForPlaybackAfterRebufferMs: Long = 3_000,
    val backBufferMs: Long = 30_000,
)

@Serializable
data class PlaybackSettings(
    val wifiBuffer: BufferProfile = BufferProfile(),
    val cellularBuffer: BufferProfile = BufferProfile(
        targetBufferMb = 24,
        minBufferMs = 15_000,
        maxBufferMs = 180_000,
        bufferForPlaybackMs = 1_500,
        bufferForPlaybackAfterRebufferMs = 4_000,
        backBufferMs = 15_000,
    ),
    val gaplessEnabled: Boolean = true,
    val crossfadeMs: Int = 0,
    val replayGain: ReplayGainSettings = ReplayGainSettings(),
    val equalizer: EqualizerSettings = EqualizerSettings(),
) {
    fun bufferFor(network: NetworkType): BufferProfile =
        if (network == NetworkType.WIFI) wifiBuffer else cellularBuffer
}

@Serializable
enum class ReplayGainMode {
    OFF,
    TRACK,
    ALBUM,
}

@Serializable
data class ReplayGainSettings(
    val enabled: Boolean = true,
    val mode: ReplayGainMode = ReplayGainMode.TRACK,
    val preampDb: Float = 0f,
    val preventClipping: Boolean = true,
)

@Serializable
enum class BandType {
    PEAKING,
    LOW_SHELF,
    HIGH_SHELF,
}

@Serializable
data class EqualizerBand(
    val frequencyHz: Float = 1_000f,
    val gainDb: Float = 0f,
    val q: Float = 1.41f,
    val type: BandType = BandType.PEAKING,
)

val DefaultEqualizerBands: List<EqualizerBand> = listOf(
    31f, 62f, 125f, 250f, 500f, 1_000f, 2_000f, 4_000f, 8_000f, 16_000f,
).map { frequency ->
    EqualizerBand(
        frequencyHz = frequency,
        type = when (frequency) {
            31f -> BandType.LOW_SHELF
            16_000f -> BandType.HIGH_SHELF
            else -> BandType.PEAKING
        },
    )
}

@Serializable
data class EqualizerSettings(
    val enabled: Boolean = false,
    val preampDb: Float = 0f,
    val bands: List<EqualizerBand> = DefaultEqualizerBands,
)

@Serializable
enum class PaletteMode {
    DYNAMIC,
    NEUTRAL,
}

@Serializable
data class UiSettings(
    val blurRadiusDp: Int = 40,
    val saturation: Float = 1.05f,
    val glassAlpha: Float = 0.18f,
    val animationScale: Float = 1f,
    val paletteMode: PaletteMode = PaletteMode.DYNAMIC,
    val reducedMotion: Boolean = false,
)

@Serializable
data class LibrarySettings(
    val syncIntervalMinutes: Int = 360,
    val syncOnWifiOnly: Boolean = true,
    val autoDownloadFavorites: Boolean = false,
    val scanLocalFolders: Boolean = true,
)

@Serializable
enum class EvictionPolicy {
    LRU,
    OLDEST_FIRST,
    PINNED_FIRST,
}

@Serializable
data class CacheSettings(
    val imageDiskMb: Int = 512,
    val imageMemoryPercent: Int = 20,
    val audioCacheMb: Int = 4_096,
    val evictionPolicy: EvictionPolicy = EvictionPolicy.LRU,
    val maxAgeDays: Int = 30,
)

@Serializable
enum class AudioCodec {
    ORIGINAL,
    MP3,
    AAC,
    OPUS,
    FLAC,
}

@Serializable
data class TranscodeProfile(
    val enabled: Boolean = false,
    val codec: AudioCodec = AudioCodec.ORIGINAL,
    val maxBitrateKbps: Int = 0,
)

@Serializable
data class NetworkSettings(
    val wifiTranscode: TranscodeProfile = TranscodeProfile(),
    val cellularTranscode: TranscodeProfile = TranscodeProfile(
        enabled = true,
        codec = AudioCodec.OPUS,
        maxBitrateKbps = 128,
    ),
    val allowMeteredStreaming: Boolean = true,
    val preferLocalCopy: Boolean = true,
) {
    fun transcodeFor(network: NetworkType): TranscodeProfile =
        if (network == NetworkType.WIFI) wifiTranscode else cellularTranscode
}
