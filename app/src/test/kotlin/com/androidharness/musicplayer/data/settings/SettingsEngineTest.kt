package com.androidharness.musicplayer.data.settings

import com.androidharness.musicplayer.core.model.NetworkType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SettingsEngineTest {

    @Test
    fun defaultsRoundTrip() {
        val encoded = SettingsEngine.encode(AppSettings())
        assertEquals(AppSettings(), SettingsEngine.decode(encoded))
    }

    @Test
    fun malformedJsonFallsBackToDefaults() {
        assertEquals(AppSettings(), SettingsEngine.decode("{not valid json"))
        assertEquals(AppSettings(), SettingsEngine.decode(null))
        assertEquals(AppSettings(), SettingsEngine.decode(""))
    }

    @Test
    fun unknownFieldsAndInvalidEnumsAreTolerated() {
        val raw = """{"schemaVersion":1,"unknown":"x","playback":{"crossfadeMs":2500}}"""
        val decoded = SettingsEngine.decode(raw)
        assertEquals(2500, decoded.playback.crossfadeMs)
    }

    @Test
    fun bufferValuesAreClampedToSafeRanges() {
        val normalized = SettingsEngine.normalize(
            AppSettings(
                playback = PlaybackSettings(
                    wifiBuffer = BufferProfile(
                        targetBufferMb = 9_999,
                        minBufferMs = 50,
                        maxBufferMs = 10,
                        bufferForPlaybackMs = 9_000,
                        bufferForPlaybackAfterRebufferMs = 9_000,
                        backBufferMs = 999_999,
                    ),
                    crossfadeMs = 99_999,
                ),
                ui = UiSettings(
                    blurRadiusDp = -5,
                    saturation = 5f,
                    glassAlpha = -1f,
                    animationScale = 0f,
                ),
                library = LibrarySettings(syncIntervalMinutes = 1),
                cache = CacheSettings(
                    imageDiskMb = 1,
                    imageMemoryPercent = 99,
                    audioCacheMb = 1,
                    maxAgeDays = 0,
                ),
                network = NetworkSettings(
                    cellularTranscode = TranscodeProfile(
                        enabled = true,
                        codec = AudioCodec.ORIGINAL,
                        maxBitrateKbps = 9_999,
                    ),
                ),
            )
        )

        val buffer = normalized.playback.wifiBuffer
        assertEquals(512, buffer.targetBufferMb)
        assertEquals(1_000L, buffer.minBufferMs)
        assertEquals(1_000L, buffer.maxBufferMs)
        assertEquals(1_000L, buffer.bufferForPlaybackMs)
        assertEquals(120_000L, buffer.backBufferMs)
        assertEquals(15_000, normalized.playback.crossfadeMs)
        assertEquals(0, normalized.ui.blurRadiusDp)
        assertEquals(2f, normalized.ui.saturation, 0f)
        assertEquals(0.25f, normalized.ui.animationScale, 0f)
        assertEquals(15, normalized.library.syncIntervalMinutes)
        assertEquals(64, normalized.cache.imageDiskMb)
        assertEquals(50, normalized.cache.imageMemoryPercent)
        assertEquals(128, normalized.cache.audioCacheMb)
        assertEquals(1, normalized.cache.maxAgeDays)
        assertFalse(normalized.network.cellularTranscode.enabled)
        assertEquals(0, normalized.network.cellularTranscode.maxBitrateKbps)
    }

    @Test
    fun nonFiniteFloatsAreReplaced() {
        val normalized = SettingsEngine.normalize(
            AppSettings(
                playback = PlaybackSettings(
                    replayGain = ReplayGainSettings(preampDb = Float.NaN),
                    equalizer = EqualizerSettings(
                        preampDb = Float.POSITIVE_INFINITY,
                        bands = emptyList(),
                    ),
                ),
                ui = UiSettings(saturation = Float.NaN),
            )
        )
        assertEquals(0f, normalized.playback.replayGain.preampDb, 0f)
        assertEquals(0f, normalized.playback.equalizer.preampDb, 0f)
        assertEquals(DefaultEqualizerBands, normalized.playback.equalizer.bands)
        assertTrue(normalized.ui.saturation.isFinite())
    }

    @Test
    fun bufferProfileFollowsNetworkType() {
        val settings = AppSettings()
        assertEquals(settings.playback.wifiBuffer, settings.playback.bufferFor(NetworkType.WIFI))
        assertEquals(
            settings.playback.cellularBuffer,
            settings.playback.bufferFor(NetworkType.CELLULAR),
        )
        assertTrue(settings.network.transcodeFor(NetworkType.CELLULAR).enabled)
    }
}
