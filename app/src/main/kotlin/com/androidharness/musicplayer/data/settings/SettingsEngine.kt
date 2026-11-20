package com.androidharness.musicplayer.data.settings

import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

/**
 * Single place that turns arbitrary persisted JSON into a safe settings object.
 * Every boundary value is clamped here, so downstream code never re-validates.
 */
object SettingsEngine {

    val json: Json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        coerceInputValues = true
        explicitNulls = false
    }

    fun decode(raw: String?): AppSettings {
        if (raw.isNullOrBlank()) return AppSettings()
        return try {
            normalize(json.decodeFromString(AppSettings.serializer(), raw))
        } catch (_: SerializationException) {
            AppSettings()
        } catch (_: IllegalArgumentException) {
            AppSettings()
        }
    }

    fun encode(settings: AppSettings): String =
        json.encodeToString(AppSettings.serializer(), normalize(settings))

    fun normalize(settings: AppSettings): AppSettings = settings.copy(
        schemaVersion = AppSettings.CURRENT_SCHEMA_VERSION,
        playback = settings.playback.normalized(),
        ui = settings.ui.normalized(),
        library = settings.library.normalized(),
        cache = settings.cache.normalized(),
        network = settings.network.normalized(),
    )

    private fun PlaybackSettings.normalized(): PlaybackSettings = copy(
        wifiBuffer = wifiBuffer.normalized(),
        cellularBuffer = cellularBuffer.normalized(),
        crossfadeMs = crossfadeMs.coerceIn(0, 15_000),
        replayGain = replayGain.copy(
            preampDb = replayGain.preampDb.finite(0f).coerceIn(-15f, 15f),
            mode = if (replayGain.enabled) replayGain.mode else ReplayGainMode.OFF,
        ),
        equalizer = equalizer.copy(
            preampDb = equalizer.preampDb.finite(0f).coerceIn(-12f, 12f),
            bands = equalizer.bands
                .ifEmpty { DefaultEqualizerBands }
                .take(16)
                .map { it.normalized() },
        ),
    )

    private fun BufferProfile.normalized(): BufferProfile {
        val safeMin = minBufferMs.coerceIn(1_000L, 600_000L)
        return copy(
            targetBufferMb = targetBufferMb.coerceIn(8, 512),
            minBufferMs = safeMin,
            maxBufferMs = maxBufferMs.coerceIn(safeMin, 1_800_000L),
            bufferForPlaybackMs = bufferForPlaybackMs.coerceIn(250L, safeMin),
            bufferForPlaybackAfterRebufferMs =
                bufferForPlaybackAfterRebufferMs.coerceIn(250L, safeMin),
            backBufferMs = backBufferMs.coerceIn(0L, 120_000L),
        )
    }

    private fun EqualizerBand.normalized(): EqualizerBand = copy(
        frequencyHz = frequencyHz.finite(1_000f).coerceIn(20f, 20_000f),
        gainDb = gainDb.finite(0f).coerceIn(-12f, 12f),
        q = q.finite(1.41f).coerceIn(0.1f, 10f),
    )

    private fun UiSettings.normalized(): UiSettings = copy(
        blurRadiusDp = blurRadiusDp.coerceIn(0, 100),
        saturation = saturation.finite(1f).coerceIn(0f, 2f),
        glassAlpha = glassAlpha.finite(0.18f).coerceIn(0f, 1f),
        animationScale = animationScale.finite(1f).coerceIn(0.25f, 2f),
    )

    private fun LibrarySettings.normalized(): LibrarySettings = copy(
        syncIntervalMinutes = syncIntervalMinutes.coerceIn(15, 10_080),
    )

    private fun CacheSettings.normalized(): CacheSettings = copy(
        imageDiskMb = imageDiskMb.coerceIn(64, 8_192),
        imageMemoryPercent = imageMemoryPercent.coerceIn(5, 50),
        audioCacheMb = audioCacheMb.coerceIn(128, 32_768),
        maxAgeDays = maxAgeDays.coerceIn(1, 365),
    )

    private fun NetworkSettings.normalized(): NetworkSettings = copy(
        wifiTranscode = wifiTranscode.normalized(),
        cellularTranscode = cellularTranscode.normalized(),
    )

    private fun TranscodeProfile.normalized(): TranscodeProfile {
        val original = codec == AudioCodec.ORIGINAL
        return copy(
            enabled = enabled && !original,
            maxBitrateKbps = if (original) 0 else maxBitrateKbps.coerceIn(32, 1_411),
        )
    }

    private fun Float.finite(fallback: Float): Float = if (isFinite()) this else fallback
}
