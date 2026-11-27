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
import com.androidharness.musicplayer.data.settings.CodecDisplayMode
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
fun QualityBadge(
    quality: AudioQuality,
    mode: CodecDisplayMode = CodecDisplayMode.RAW_CONTAINER,
    modifier: Modifier = Modifier,
) {
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

    // SIMPLIFIED collapses to two tiers; HIGHLIGHTED keeps the tier but warns on upsampled
    // or transcoded streams by swapping to the accent pill.
    val label = if (mode == CodecDisplayMode.SIMPLIFIED && quality == AudioQuality.HI_RES) {
        "LOSSLESS"
    } else {
        style.label
    }
    val highlighted = mode == CodecDisplayMode.HIGHLIGHTED && quality == AudioQuality.HI_RES
    val pillColor = if (highlighted) Color(0xFFFFC107) else style.pillColor
    val textColor = if (highlighted) Color(0xFF000000) else style.textColor

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .background(pillColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = style.icon,
            contentDescription = null,
            tint = textColor,
            modifier = Modifier.size(12.dp),
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = label,
            color = textColor,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
        )
    }
}
