package com.androidharness.musicplayer.feature.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidharness.musicplayer.core.model.Track

enum class AudioQuality { LOSSLESS, LOSSY, HI_RES, UNKNOWN }

private val losslessFormats = setOf(
    "flac", "alac", "wav", "aiff", "aif", "ape", "wv", "dsf", "dff", "dsd",
)

fun Track.audioQuality(): AudioQuality {
    val fmt = format?.lowercase() ?: return AudioQuality.UNKNOWN
    if (fmt !in losslessFormats) return AudioQuality.LOSSY
    val sampleRate = sampleRateHz ?: 0
    return if (sampleRate > 48_000) AudioQuality.HI_RES else AudioQuality.LOSSLESS
}

@Composable
fun QualityBadge(quality: AudioQuality, modifier: Modifier = Modifier) {
    if (quality == AudioQuality.UNKNOWN) return

    val (label, textColor, pillColor) = when (quality) {
        AudioQuality.LOSSLESS ->
            Triple("LOSSLESS", Color(0xFF22C55E), Color(0xFF000000))
        AudioQuality.LOSSY ->
            Triple("LOSSY", Color(0xFFEF4444), Color(0xFFFFFFFF))
        AudioQuality.HI_RES ->
            Triple("HI-RES", Color(0xFF0B0B0D), Color(0xFFFFC107))
        AudioQuality.UNKNOWN -> return
    }

    Text(
        text = label,
        color = textColor,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp,
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(pillColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
    )
}
