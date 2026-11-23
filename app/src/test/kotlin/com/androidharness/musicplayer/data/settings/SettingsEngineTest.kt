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

    @Test
    fun playbackAutomationDefaultsAreSafe() {
        val playback = AppSettings().playback
        assertFalse(playback.skipSilence)
        assertTrue(playback.pauseOnHeadphonesDisconnect)
        assertTrue(playback.resumeQueueOnStart)
        assertTrue(AppSettings().network.preferLocalCopy)
        assertFalse(AppSettings().ui.reducedMotion)
    }

    @Test
    fun newPlaybackFlagsRoundTrip() {
        val settings = AppSettings(
            playback = PlaybackSettings(
                skipSilence = true,
                pauseOnHeadphonesDisconnect = false,
                resumeQueueOnStart = false,
            ),
        )
        val decoded = SettingsEngine.decode(SettingsEngine.encode(settings))
        assertTrue(decoded.playback.skipSilence)
        assertFalse(decoded.playback.pauseOnHeadphonesDisconnect)
        assertFalse(decoded.playback.resumeQueueOnStart)
        assertEquals(AppSettings.CURRENT_SCHEMA_VERSION, decoded.schemaVersion)
    }

    @Test
    fun settingsStoredBeforeNewFlagsKeepDefaults() {
        val raw = """{"schemaVersion":1,"playback":{"crossfadeMs":2500}}"""
        val decoded = SettingsEngine.decode(raw)
        assertEquals(2500, decoded.playback.crossfadeMs)
        assertFalse(decoded.playback.skipSilence)
        assertTrue(decoded.playback.pauseOnHeadphonesDisconnect)
        assertTrue(decoded.playback.resumeQueueOnStart)
        assertEquals(AppSettings.CURRENT_SCHEMA_VERSION, decoded.schemaVersion)
    }

    @Test
    fun reducedMotionSurvivesEncoding() {
        val settings = AppSettings(ui = UiSettings(reducedMotion = true))
        val decoded = SettingsEngine.decode(SettingsEngine.encode(settings))
        assertTrue(decoded.ui.reducedMotion)
    }

    @Test
    fun v2OpusCellularTranscodeMigratesToMp3() {
        val raw = """
            {"schemaVersion":2,"network":{"cellularTranscode":{
                "enabled":true,"codec":"OPUS","maxBitrateKbps":128}}}
        """.trimIndent()
        val decoded = SettingsEngine.decode(raw)
        assertEquals(AudioCodec.MP3, decoded.network.cellularTranscode.codec)
        assertEquals(128, decoded.network.cellularTranscode.maxBitrateKbps)
        assertEquals(AppSettings.CURRENT_SCHEMA_VERSION, decoded.schemaVersion)
    }

    @Test
    fun v2OpusWifiTranscodeMigratesToMp3() {
        val raw = """
            {"schemaVersion":2,"network":{"wifiTranscode":{
                "enabled":true,"codec":"OPUS","maxBitrateKbps":320}}}
        """.trimIndent()
        val decoded = SettingsEngine.decode(raw)
        assertEquals(AudioCodec.MP3, decoded.network.wifiTranscode.codec)
    }

    @Test
    fun nonOpusTranscodeSurvivesMigration() {
        val raw = """
            {"schemaVersion":2,"network":{"cellularTranscode":{
                "enabled":true,"codec":"AAC","maxBitrateKbps":256}}}
        """.trimIndent()
        val decoded = SettingsEngine.decode(raw)
        assertEquals(AudioCodec.AAC, decoded.network.cellularTranscode.codec)
    }

    @Test
    fun defaultCellularTranscodeIsMp3() {
        assertEquals(AudioCodec.MP3, AppSettings().network.cellularTranscode.codec)
    }
}
