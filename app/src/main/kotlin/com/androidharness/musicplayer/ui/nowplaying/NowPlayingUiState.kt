package com.androidharness.musicplayer.ui.nowplaying

import androidx.compose.ui.graphics.Color

data class NowPlayingUiState(
    val title: String = "",
    val artist: String = "",
    val album: String = "",
    val artworkUrl: String? = null,
    val sourceLabel: String = "bitXact", // e.g., "Subsonic", "Local", "bitXact"
    val codecLabel: String = "Hi-Res FLAC",
    val sampleRateLabel: String = "24/44.1 kHz",
    val bitrateLabel: String = "1750 kbps",
    val isLossless: Boolean = true,
    val isPlaying: Boolean = false,
    val isFavorite: Boolean = false,
    val isCachedOffline: Boolean = false,
    val positionMs: Long = 0L,
    val durationMs: Long = 1L,
    /** Static track amplitude envelope (0f..1f) for full-track waveform shape */
    val trackWaveformPeaks: List<Float> = emptyList(),
    /** Live audio energy per bar (0f..1f) modulated while playing */
    val liveSpectrumBars: List<Float> = emptyList(),
    /** Extracted from cover art via Palette API */
    val dominantColor: Color = Color(0xFFD9532F),
    val accentColor: Color = Color(0xFFFF7847)
) {
    val progress: Float
        get() = if (durationMs > 0L) (positionMs.toFloat() / durationMs).coerceIn(0f, 1f) else 0f
}
