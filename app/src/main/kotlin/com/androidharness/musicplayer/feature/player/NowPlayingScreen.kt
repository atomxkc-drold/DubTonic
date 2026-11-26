package com.androidharness.musicplayer.feature.player

import android.net.Uri
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import coil3.compose.AsyncImage
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.playback.audio.AudioLevelTap
import com.androidharness.musicplayer.ui.icons.PlayerIcons
import kotlin.math.abs

/**
 * Full-bleed artwork player.
 *
 * The cover fills the top half and is mirrored, blurred and dimmed underneath so the lower
 * half reads as a reflection of the same image rather than a flat gradient. The controls
 * sit low, directly above a bar visualiser driven by the audio pipeline.
 *
 * Gestures: double-tap the cover to favourite, swipe left/right to skip, pull down to close.
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
    val levels by viewModel.levels.collectAsStateWithLifecycle()
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
            ArtworkBackdrop(playback.artworkUri)
        }

        Column(
            Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.Start,
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

            // Pushes the whole control stack down to sit just above the visualiser.
            Spacer(Modifier.weight(1f))

            Text(
                text = playback.title.ifBlank { "Nothing playing" },
                color = Color.White,
                fontSize = 21.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 26.sp,
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(Modifier.height(5.dp))

            Text(
                text = subtitle(playback.artist, playback.album),
                color = Color.White.copy(alpha = 0.62f),
                fontSize = 13.sp,
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(Modifier.height(12.dp))

            QualityLine(track, accent)

            Spacer(Modifier.height(10.dp))

            WaveformScrubber(
                trackId = playback.trackId,
                positionMs = playback.positionMs,
                durationMs = playback.durationMs,
                isPlaying = playback.isPlaying,
                accent = accent,
                onSeek = viewModel::seekToFraction,
            )

            Row(
                Modifier.fillMaxWidth(),
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

            Spacer(Modifier.height(16.dp))

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

            Spacer(Modifier.height(16.dp))

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

            Spacer(Modifier.height(18.dp))

            BeatVisualizer(
                levels = levels,
                accent = accent,
                active = playback.isPlaying,
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

/**
 * Sharp cover across the top, then the same image mirrored and blurred underneath it,
 * fading to black — the reflection bleeds the art into the controls instead of a hard edge.
 */
@Composable
private fun BoxScope.ArtworkBackdrop(uri: Uri?) {
    if (uri == null) {
        Box(Modifier.fillMaxSize().background(Color.Black))
        return
    }

    AsyncImage(
        model = uri,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                scaleY = -1f
                scaleX = 1.08f
                alpha = 0.90f
            }
            .blur(38.dp),
    )

    Box(
        Modifier.fillMaxSize().background(
            Brush.verticalGradient(
                0f to Color.Black.copy(alpha = 0.10f),
                0.30f to Color.Black.copy(alpha = 0.28f),
                0.52f to Color.Black.copy(alpha = 0.62f),
                0.78f to Color.Black.copy(alpha = 0.86f),
                1f to Color.Black.copy(alpha = 0.96f),
            )
        )
    )

    Box(Modifier.fillMaxWidth().fillMaxHeight(0.44f).align(Alignment.TopCenter)) {
        AsyncImage(
            model = uri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        // Bring the blurred reflection up over the bottom edge of the cover so it melts into
        // the reflection below instead of ending on a hard line.
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0f to Color.Transparent,
                        0.30f to Color.Black.copy(alpha = 0.40f),
                        1f to Color.Black,
                    )
                )
                .blur(26.dp),
        )
    }
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

    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
        if (quality == AudioQuality.UNKNOWN) {
            Text(
                text = parts.joinToString(" · "),
                color = accent,
                fontSize = 11.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Start,
            )
        } else {
            QualityBadge(quality)
            if (parts.size > 1) {
                Spacer(Modifier.width(6.dp))
                Text(
                    text = parts.drop(1).joinToString(" · "),
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 11.sp,
                    fontFamily = FontFamily.Monospace,
                    textAlign = TextAlign.Start,
                )
            }
        }
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

/**
 * Bar visualiser fed by [com.androidharness.musicplayer.playback.audio.VisualizerProcessor].
 *
 * Bars rise fast and fall slowly so kicks punch through and tails settle, and everything
 * decays to silence when playback stops.
 */
@Composable
private fun BeatVisualizer(
    levels: FloatArray,
    accent: Color,
    active: Boolean,
) {
    val smoothed = remember { FloatArray(AudioLevelTap.BAR_COUNT) }
    val current by rememberUpdatedState(levels)
    var tick by remember { mutableIntStateOf(0) }

    LaunchedEffect(active) {
        while (true) {
            withFrameNanos { }
            val target = if (active) current else SILENCE
            for (index in smoothed.indices) {
                val goal = target.getOrElse(index) { 0f }
                val rate = if (goal > smoothed[index]) 0.5f else 0.14f
                smoothed[index] += (goal - smoothed[index]) * rate
            }
            tick++
        }
    }

    Canvas(Modifier.fillMaxWidth().height(52.dp)) {
        // Reading the frame counter inside the draw scope invalidates only the draw pass.
        @Suppress("UNUSED_VARIABLE")
        val frame = tick

        val count = smoothed.size
        val gap = size.width / (count * 3.2f)
        val barWidth = ((size.width - gap * (count - 1)) / count).coerceAtLeast(1f)

        for (index in 0 until count) {
            val value = smoothed[index].coerceIn(0f, 1f)
            val barHeight = (size.height * (0.05f + 0.95f * value)).coerceAtLeast(2f)
            val x = index * (barWidth + gap)
            drawRoundRect(
                brush = Brush.verticalGradient(
                    colors = listOf(accent, accent.copy(alpha = 0.35f)),
                    startY = size.height - barHeight,
                    endY = size.height,
                ),
                topLeft = Offset(x, size.height - barHeight),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(barWidth / 2f, barWidth / 2f),
            )
        }
    }
}

private val SILENCE = FloatArray(AudioLevelTap.BAR_COUNT)

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
