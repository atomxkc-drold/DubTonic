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
    val bitDepth = bitDepth ?: 16
    return if (sampleRate > 48_000 || bitDepth >= 24) AudioQuality.HI_RES else AudioQuality.LOSSLESS
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

@Composable
fun CodecQualityBadge(
    track: Track,
    mode: CodecDisplayMode = CodecDisplayMode.RAW_CONTAINER,
    modifier: Modifier = Modifier,
) {
    val quality = track.audioQuality()
    val format = track.format?.uppercase() ?: "UNKNOWN"
    val bitrate = track.bitrateKbps ?: 0
    val sampleRate = track.sampleRateHz ?: 0
    val bitDepth = track.bitDepth ?: 16
    val isDownloaded = track.isDownloaded
    val isLocal = track.isLocal
    val isStreaming = !isDownloaded && !isLocal

    // Determine badge type
    val badgeType = when {
        quality == AudioQuality.HI_RES -> BadgeType.HI_RES
        quality == AudioQuality.LOSSLESS -> BadgeType.LOSSLESS
        format == "MP3" -> BadgeType.MP3
        format == "AAC" || format == "M4A" -> BadgeType.AAC
        format == "OPUS" -> BadgeType.OPUS
        format == "FLAC" -> BadgeType.FLAC
        else -> BadgeType.UNKNOWN
    }

    val (label, icon, textColor, pillColor) = when (badgeType) {
        BadgeType.HI_RES -> "HI-RES" to PlayerIcons.Spectrum to Color(0xFF000000) to Color(0xFFFFC107)
        BadgeType.LOSSLESS -> "LOSSLESS" to PlayerIcons.Waveform to Color(0xFF3DDC84) to Color(0xFF000000)
        BadgeType.FLAC -> "FLAC" to PlayerIcons.Waveform to Color(0xFF3DDC84) to Color(0xFF000000)
        BadgeType.MP3 -> "MP3" to PlayerIcons.Equalizer to Color(0xFFEF4444) to Color(0xFFFFFFFF)
        BadgeType.AAC -> "AAC" to PlayerIcons.Equalizer to Color(0xFFEF4444) to Color(0xFFFFFFFF)
        BadgeType.OPUS -> "OPUS" to PlayerIcons.Equalizer to Color(0xFFEF4444) to Color(0xFFFFFFFF)
        BadgeType.LOSSY -> "LOSSY" to PlayerIcons.Equalizer to Color(0xFFEF4444) to Color(0xFFFFFFFF)
        BadgeType.UNKNOWN -> "AUDIO" to PlayerIcons.Equalizer to Color(0xFFEF4444) to Color(0xFFFFFFFF)
    }

    val highlighted = mode == CodecDisplayMode.HIGHLIGHTED && quality == AudioQuality.HI_RES
    val finalPillColor = if (highlighted) Color(0xFFFFC107) else pillColor
    val finalTextColor = if (highlighted) Color(0xFF000000) else textColor

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .background(finalPillColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = finalTextColor,
            modifier = Modifier.size(12.dp),
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = label,
            color = finalTextColor,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
        )
    }
}

@Composable
fun SourceBadge(
    track: Track,
    modifier: Modifier = Modifier,
) {
    val isDownloaded = track.isDownloaded
    val isLocal = track.isLocal
    val isStreaming = !isDownloaded && !isLocal

    val (label, icon, textColor, pillColor) = when {
        isLocal -> "LOCAL" to PlayerIcons.Smartphone to Color(0xFF3DDC84) to Color(0xFF000000)
        isDownloaded -> "DOWNLOADED" to PlayerIcons.Check to Color(0xFF3DDC84) to Color(0xFF000000)
        isStreaming -> "SUBSONIC" to PlayerIcons.DubTonic to Color(0xFF000000) to Color(0xFFFFC107)
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .background(pillColor)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
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

@Composable
fun FullQualityLine(
    track: Track,
    mode: CodecDisplayMode = CodecDisplayMode.RAW_CONTAINER,
    modifier: Modifier = Modifier,
) {
    val quality = track.audioQuality()
    val format = track.format?.uppercase() ?: "UNKNOWN"
    val bitrate = track.bitrateKbps ?: 0
    val sampleRate = track.sampleRateHz ?: 0
    val bitDepth = track.bitDepth ?: 16

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        CodecQualityBadge(track, mode)
        if (mode == CodecDisplayMode.RAW_CONTAINER) {
            Text(
                text = format,
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 11.sp,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
            )
        }
        if (sampleRate > 0) {
            Text(
                text = sampleRateLabel(sampleRate),
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 11.sp,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
            )
        }
        if (bitrate > 0) {
            Text(
                text = "$bitrate kbps",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 11.sp,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
            )
        }
        if (bitDepth >= 24) {
            Text(
                text = "${bitDepth}bit",
                color = Color.White.copy(alpha = 0.6f),
                fontSize = 11.sp,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
            )
        }
        SourceBadge(track)
    }
}

private enum class BadgeType {
    HI_RES, LOSSLESS, FLAC, MP3, AAC, OPUS, LOSSY, UNKNOWN
}

private fun sampleRateLabel(hz: Int): String = when {
    hz % 1000 == 0 -> "${hz / 1000} kHz"
    else -> "${(hz / 100.0).toInt() / 10.0} kHz"
}
