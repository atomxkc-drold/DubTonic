package com.androidharness.musicplayer.feature.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.ui.icons.PlayerIcons

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

private data class QualityStyle(
    val label: String,
    val icon: ImageVector,
    val textColor: Color,
    val pillColor: Color,
)

@Composable
fun QualityBadge(quality: AudioQuality, modifier: Modifier = Modifier) {
    if (quality == AudioQuality.UNKNOWN) return

    val style = when (quality) {
        AudioQuality.LOSSLESS ->
            QualityStyle("LOSSLESS", PlayerIcons.Waveform, Color(0xFF3DDC84), Color(0xFF000000))
        AudioQuality.LOSSY ->
            QualityStyle("LOSSY", PlayerIcons.Equalizer, Color(0xFFEF4444), Color(0xFFFFFFFF))
        AudioQuality.HI_RES ->
            QualityStyle("HI-RES", PlayerIcons.Spectrum, Color(0xFF000000), Color(0xFFFFC107))
        AudioQuality.UNKNOWN -> return
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .background(style.pillColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = style.icon,
            contentDescription = null,
            tint = style.textColor,
            modifier = Modifier.size(12.dp),
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = style.label,
            color = style.textColor,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
        )
    }
}
