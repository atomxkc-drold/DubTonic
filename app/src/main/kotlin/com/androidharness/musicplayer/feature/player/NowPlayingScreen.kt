package com.androidharness.musicplayer.feature.player

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.Player
import coil3.compose.AsyncImage
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.ui.icons.PlayerIcons
import kotlin.math.abs

/**
 * Full-bleed artwork player.
 *
 * Gestures: double-tap the cover to favourite, swipe left/right to skip, pull down to
 * close. Everything else is a real control — nothing here is a dead button.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NowPlayingScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onBack: () -> Unit,
    onOpenSettings: () -> Unit,
    viewModel: PlayerViewModel = hiltViewModel(),
) {
    val playback by viewModel.playback.collectAsStateWithLifecycle()
    val details by viewModel.details.collectAsStateWithLifecycle()
    val colors by viewModel.colors.collectAsStateWithLifecycle()
    val track = details?.takeIf { it.id == playback.trackId }
    val accent = colors.primary

    var dragX by remember { mutableFloatStateOf(0f) }
    var dragY by remember { mutableFloatStateOf(0f) }

    Box(Modifier.fillMaxSize().background(Color.Black)) {
        Box(
            Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onDoubleTap = { viewModel.toggleFavorite() })
                }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragEnd = {
                            when {
                                abs(dragX) > abs(dragY) && abs(dragX) > 90f ->
                                    if (dragX > 0) viewModel.previous() else viewModel.next()
                                dragY > 140f -> onBack()
                            }
                            dragX = 0f
                            dragY = 0f
                        },
                        onDrag = { _, drag ->
                            dragX += drag.x
                            dragY += drag.y
                        },
                    )
                },
        ) {
            playback.artworkUri?.let { uri ->
                AsyncImage(
                    model = uri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
            }
            Box(
                Modifier.fillMaxSize().background(
                    Brush.verticalGradient(
                        0f to Color.Black.copy(alpha = 0.30f),
                        0.45f to Color.Black.copy(alpha = 0.58f),
                        0.75f to Color.Black.copy(alpha = 0.88f),
                        1f to Color.Black.copy(alpha = 0.97f),
                    )
                )
            )
        }

        Column(
            Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(Modifier.fillMaxWidth().height(52.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White.copy(alpha = 0.85f),
                    )
                }
                Spacer(Modifier.weight(1f))
            }

            Spacer(Modifier.weight(1f))

            BadgePill(text = sourceLabel(track))

            Spacer(Modifier.height(14.dp))

            Text(
                text = playback.title.ifBlank { "Nothing playing" },
                color = Color.White,
                fontSize = 21.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 26.sp,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = subtitle(playback.artist, playback.album),
                color = Color.White.copy(alpha = 0.62f),
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(Modifier.height(14.dp))

            QualityLine(track, accent)

            Spacer(Modifier.height(14.dp))

            WaveformScrubber(
                trackId = playback.trackId,
                positionMs = playback.positionMs,
                durationMs = playback.durationMs,
                isPlaying = playback.isPlaying,
                accent = accent,
                onSeek = viewModel::seekToFraction,
            )

            Row(
                Modifier.fillMaxWidth().padding(top = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = clock(playback.positionMs),
                    color = Color.White.copy(alpha = 0.55f),
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                )
                Text(
                    text = clock(playback.durationMs),
                    color = Color.White.copy(alpha = 0.55f),
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                )
            }

            Spacer(Modifier.height(18.dp))

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                QuickAction(
                    icon = PlayerIcons.Sparkle,
                    description = "Shuffle",
                    highlighted = playback.shuffleEnabled,
                    accent = accent,
                    onClick = viewModel::toggleShuffle,
                )
                QuickAction(
                    icon = PlayerIcons.Download,
                    description = "Downloads and cache",
                    onClick = onOpenSettings,
                )
                QuickAction(
                    icon = PlayerIcons.Equalizer,
                    description = "Equalizer",
                    onClick = onOpenSettings,
                )
                QuickAction(
                    icon = PlayerIcons.Spectrum,
                    description = "Audio spectrum",
                    onClick = onOpenSettings,
                )
                QuickAction(
                    icon = Icons.Filled.Info,
                    description = "Track details",
                    onClick = onOpenSettings,
                )
                QuickAction(
                    icon = Icons.Filled.MoreVert,
                    description = "More options",
                    onClick = onOpenSettings,
                )
            }

            Spacer(Modifier.height(18.dp))

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(onClick = onBack, modifier = Modifier.size(48.dp)) {
                    Icon(
                        PlayerIcons.Queue, "Open library",
                        tint = Color.White.copy(alpha = 0.85f), modifier = Modifier.size(24.dp),
                    )
                }
                IconButton(onClick = viewModel::previous, modifier = Modifier.size(56.dp)) {
                    Icon(
                        PlayerIcons.SkipPrevious, "Previous",
                        tint = Color.White, modifier = Modifier.size(34.dp),
                    )
                }
                Box(
                    Modifier.size(72.dp).clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.10f))
                        .border(1.dp, Color.White.copy(alpha = 0.22f), CircleShape)
                        .clickable(onClick = viewModel::playPause),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = if (playback.isPlaying) PlayerIcons.Pause else Icons.Filled.PlayArrow,
                        contentDescription = if (playback.isPlaying) "Pause" else "Play",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp),
                    )
                }
                IconButton(onClick = viewModel::next, modifier = Modifier.size(56.dp)) {
                    Icon(
                        PlayerIcons.SkipNext, "Next",
                        tint = Color.White, modifier = Modifier.size(34.dp),
                    )
                }
                IconButton(onClick = viewModel::toggleFavorite, modifier = Modifier.size(48.dp)) {
                    val favorite = track?.isFavorite == true
                    Icon(
                        imageVector = if (favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = if (favorite) "Remove from favorites" else "Add to favorites",
                        tint = if (favorite) accent else Color.White.copy(alpha = 0.85f),
                        modifier = Modifier.size(24.dp),
                    )
                }
            }

            Spacer(Modifier.weight(1f))

            SpectrumDots(accent = accent, active = playback.isPlaying)
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
private fun BadgePill(text: String) {
    Text(
        text = text,
        color = Color.White.copy(alpha = 0.78f),
        fontSize = 10.sp,
        fontFamily = FontFamily.Monospace,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Color.White.copy(alpha = 0.12f))
            .border(1.dp, Color.White.copy(alpha = 0.18f), RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 5.dp),
    )
}

@Composable
private fun QualityLine(track: Track?, accent: Color) {
    val quality = track?.audioQuality() ?: AudioQuality.UNKNOWN
    val parts = buildList {
        if (quality != AudioQuality.UNKNOWN) {
            add(
                when (quality) {
                    AudioQuality.HI_RES -> "HI-RES"
                    AudioQuality.LOSSLESS -> "LOSSLESS"
                    AudioQuality.LOSSY -> "LOSSY"
                    AudioQuality.UNKNOWN -> "AUDIO"
                }
            )
        }
        track?.format?.uppercase()?.takeIf { it.isNotBlank() }?.let(::add)
        track?.sampleRateHz?.takeIf { it > 0 }?.let { add(sampleRateLabel(it)) }
        track?.bitrateKbps?.takeIf { it > 0 }?.let { add("$it kbps") }
    }
    if (parts.isEmpty()) return

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(7.dp).clip(CircleShape).background(accent))
        Spacer(Modifier.width(8.dp))
        Text(
            text = parts.joinToString(" · "),
            color = accent,
            fontSize = 11.sp,
            fontFamily = FontFamily.Monospace,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun QuickAction(
    icon: ImageVector,
    description: String,
    highlighted: Boolean = false,
    accent: Color = Color.White,
    onClick: () -> Unit,
) {
    Box(
        Modifier.size(46.dp).clip(CircleShape)
            .background(
                if (highlighted) accent.copy(alpha = 0.24f) else Color.White.copy(alpha = 0.07f)
            )
            .border(
                1.dp,
                if (highlighted) accent.copy(alpha = 0.65f) else Color.White.copy(alpha = 0.16f),
                CircleShape,
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            icon,
            contentDescription = description,
            tint = if (highlighted) accent else Color.White.copy(alpha = 0.82f),
            modifier = Modifier.size(20.dp),
        )
    }
}

/** Decorative spectrum strip along the bottom edge. */
@Composable
private fun SpectrumDots(accent: Color, active: Boolean) {
    val palette = listOf(
        accent, Color(0xFF4ADE80), Color(0xFFFACC15),
        Color(0xFFF97316), Color(0xFFEC4899), Color(0xFF60A5FA),
    )
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(26) { index ->
            val color = palette[index % palette.size]
            Box(
                Modifier
                    .size(width = 4.dp, height = if (active && index % 3 == 0) 9.dp else 5.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = if (active) 0.85f else 0.28f))
            )
        }
    }
}

private fun sourceLabel(track: Track?): String = when {
    track == null -> "bitXact"
    track.localUri != null -> "Offline copy"
    else -> "bitXact"
}

private fun subtitle(artist: String, album: String): String = when {
    artist.isBlank() && album.isBlank() -> " "
    album.isBlank() -> artist
    artist.isBlank() -> album
    else -> "$artist · $album"
}

private fun sampleRateLabel(hz: Int): String = when {
    hz % 1000 == 0 -> "${hz / 1000} kHz"
    else -> "${(hz / 100.0).toInt() / 10.0} kHz"
}

private fun clock(ms: Long): String {
    val totalSeconds = (ms / 1000L).toInt().coerceAtLeast(0)
    val minutes = totalSeconds / 60
    val seconds = (totalSeconds % 60).toString().padStart(2, '0')
    return "$minutes:$seconds"
}
