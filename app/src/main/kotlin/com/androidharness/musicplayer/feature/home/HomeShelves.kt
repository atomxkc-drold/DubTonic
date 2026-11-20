package com.androidharness.musicplayer.feature.home

import android.net.Uri
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.feature.player.PlaybackUiState
import com.androidharness.musicplayer.ui.chrome.SubsonicBadge
import com.androidharness.musicplayer.ui.theme.SpringSpecs
import kotlin.math.abs

/**
 * Loose fan of covers with a springy float. Each card drifts on its own phase so the
 * hero keeps breathing without any pointer input.
 */
@Composable
fun AlbumFan(
    tracks: List<Track>,
    artworkFor: (Track) -> Uri?,
    ink: Color,
    accent: Color,
    animate: Boolean,
    modifier: Modifier = Modifier,
) {
    if (tracks.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(196.dp)
                .clip(RoundedCornerShape(26.dp))
                .background(
                    Brush.linearGradient(
                        listOf(ink.copy(alpha = 0.10f), accent.copy(alpha = 0.10f)),
                    )
                )
                .border(1.dp, ink.copy(alpha = 0.10f), RoundedCornerShape(26.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "♪",
                    color = ink.copy(alpha = 0.35f),
                    style = MaterialTheme.typography.displayMedium,
                )
                Text(
                    "Library is empty",
                    color = ink.copy(alpha = 0.5f),
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                )
            }
        }
        return
    }

    BoxWithConstraints(modifier = modifier.fillMaxWidth().height(204.dp)) {
        val cardWidth = (maxWidth * 0.37f).coerceAtMost(148.dp)
        val step = if (tracks.size > 1) (maxWidth - cardWidth) / (tracks.size - 1) else 0.dp

        BreathingHalo(
            color = accent,
            animate = animate,
            modifier = Modifier.fillMaxSize(),
        )

        tracks.forEachIndexed { index, track ->
            val fraction = if (tracks.size > 1) index.toFloat() / (tracks.size - 1) else 0.5f
            val rotation = (fraction - 0.5f) * 26f
            val x = if (tracks.size == 1) (maxWidth - cardWidth) / 2 else step * index
            val float = if (animate) {
                val transition = rememberInfiniteTransition(label = "fan$index")
                transition.animateFloat(
                    initialValue = 0f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 5_200 + index * 340,
                            easing = FastOutSlowInEasing,
                        ),
                        repeatMode = RepeatMode.Reverse,
                    ),
                    label = "fanFloat$index",
                ).value
            } else {
                0.5f
            }

            val shape = RoundedCornerShape(14.dp)
            Box(
                modifier = Modifier
                    .offset(
                        x = x,
                        y = (14f + abs(fraction - 0.5f) * 24f + float * 7f).dp,
                    )
                    .size(cardWidth)
                    .graphicsLayer { rotationZ = rotation }
                    .shadow(18.dp, shape)
                    .clip(shape)
                    .background(ink.copy(alpha = 0.16f))
                    .border(1.dp, Color.White.copy(alpha = 0.16f), shape),
            ) {
                AsyncImage(
                    model = artworkFor(track),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize().aspectRatio(1f),
                )
                SubsonicBadge(
                    size = 24.dp,
                    scrim = Color.Black.copy(alpha = 0.42f),
                    modifier = Modifier.align(Alignment.BottomStart).padding(7.dp),
                )
            }
        }
    }
}

/**
 * "Sonic Flow": one tap starts a queue ordered by feel. The wave draws the actual route
 * so the feature is visible before you press it.
 */
@Composable
fun SonicFlowCard(
    route: List<Track>,
    trackCount: Int,
    totalDurationMs: Long,
    accent: Color,
    glow: Color,
    ink: Color,
    muted: Color,
    animate: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(28.dp)
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val press by animateFloatAsState(
        targetValue = if (pressed) 0.985f else 1f,
        animationSpec = tween(durationMillis = 140),
        label = "flowPress",
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = press
                scaleY = press
            }
            .shadow(24.dp, shape)
            .clip(shape)
            .background(
                Brush.linearGradient(
                    listOf(
                        ink.copy(alpha = 0.13f),
                        glow.copy(alpha = 0.24f),
                        ink.copy(alpha = 0.08f),
                    )
                )
            )
            .border(
                1.dp,
                Brush.linearGradient(
                    listOf(Color.White.copy(alpha = 0.28f), Color.White.copy(alpha = 0.05f)),
                ),
                shape,
            )
            .clickable(interactionSource = interaction, indication = null, onClick = onClick)
            .padding(20.dp),
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "SONIC FLOW",
                    color = ink,
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing,
                )
                Spacer(Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .background(accent.copy(alpha = 0.22f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                ) {
                    Text(
                        text = "AUTO-ORDERED",
                        color = accent,
                        style = MaterialTheme.typography.labelSmall,
                        fontFamily = FontFamily.Monospace,
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            Text(
                text = "One continuous ride",
                color = ink,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Each track lands next to something that sounds close. " +
                    "Starts where you are, then drifts through the library.",
                color = muted,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(14.dp))
            FlowWave(
                route = route,
                accent = accent,
                glow = glow,
                animate = animate,
                modifier = Modifier.fillMaxWidth().height(58.dp),
            )
            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .background(accent, CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        Icons.Filled.PlayArrow,
                        contentDescription = null,
                        tint = Color(0xFF0B0F0E),
                        modifier = Modifier.size(22.dp),
                    )
                }
                Spacer(Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Start the ride",
                        color = ink,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = "$trackCount tracks · ${formatListenTime(totalDurationMs)}",
                        color = muted,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }
    }
}

@Composable
private fun FlowWave(
    route: List<Track>,
    accent: Color,
    glow: Color,
    animate: Boolean,
    modifier: Modifier = Modifier,
) {
    val points = remember(route) {
        if (route.isEmpty()) return@remember emptyList<Offset>()
        val sonic = route.map { sonicPoint(it) }
        val decades = sonic.map { it.decade }
        val minutes = sonic.map { it.minutes }
        val minDecade = decades.minOrNull() ?: 0f
        val maxDecade = decades.maxOrNull() ?: 0f
        val minMinutes = minutes.minOrNull() ?: 0f
        val maxMinutes = minutes.maxOrNull() ?: 0f
        sonic.mapIndexed { index, point ->
            val x = if (sonic.size == 1) 0.5f else index.toFloat() / (sonic.size - 1)
            val decadeSpan = (maxDecade - minDecade).coerceAtLeast(0.001f)
            val minuteSpan = (maxMinutes - minMinutes).coerceAtLeast(0.001f)
            val y = 1f - ((point.minutes - minMinutes) / minuteSpan) * 0.7f -
                ((point.decade - minDecade) / decadeSpan) * 0.3f
            Offset(x, y)
        }
    }

    val progress: Float by if (animate) {
        rememberInfiniteTransition(label = "flow").animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 9_500, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,
            ),
            label = "flowProgress",
        )
    } else {
        remember { mutableFloatStateOf(0.72f) }
    }

    Canvas(modifier = modifier) {
        if (points.size < 2) {
            drawLine(
                color = accent.copy(alpha = 0.4f),
                start = Offset(0f, size.height / 2f),
                end = Offset(size.width, size.height / 2f),
                strokeWidth = 2.dp.toPx(),
                cap = StrokeCap.Round,
            )
            return@Canvas
        }

        val absolute = points.map { Offset(it.x * size.width, it.y * size.height) }
        val line = Path().apply {
            moveTo(absolute.first().x, absolute.first().y)
            for (index in 1 until absolute.size) {
                val previous = absolute[index - 1]
                val current = absolute[index]
                val midX = (previous.x + current.x) / 2f
                cubicTo(midX, previous.y, midX, current.y, current.x, current.y)
            }
        }
        val fill = Path().apply {
            addPath(line)
            lineTo(absolute.last().x, size.height)
            lineTo(absolute.first().x, size.height)
            close()
        }

        drawPath(
            path = fill,
            brush = Brush.verticalGradient(
                listOf(accent.copy(alpha = 0.20f), Color.Transparent),
                startY = 0f,
                endY = size.height,
            ),
        )
        drawPath(
            path = line,
            brush = Brush.linearGradient(
                colors = listOf(accent, glow),
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
            ),
            style = Stroke(width = 2.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round),
        )

        val step = 1f / (absolute.size - 1)
        val markerIndex = (progress / step).toInt().coerceIn(0, absolute.size - 2)
        val local = ((progress - markerIndex * step) / step).coerceIn(0f, 1f)
        val from = absolute[markerIndex]
        val to = absolute[markerIndex + 1]
        val marker = Offset(
            x = from.x + (to.x - from.x) * local,
            y = from.y + (to.y - from.y) * local,
        )
        drawCircle(glow.copy(alpha = 0.22f), radius = 9.dp.toPx(), center = marker)
        drawCircle(Color.White.copy(alpha = 0.9f), radius = 3.2.dp.toPx(), center = marker)
    }
}

/** Horizontally scrolling generated mixes. */
@Composable
fun VibeStackRail(
    stacks: List<VibeStack>,
    artworkFor: (String?) -> Uri?,
    accentFor: (Int) -> Color,
    ink: Color,
    muted: Color,
    motion: SpringSpecs,
    onPlay: (VibeStack) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        itemsIndexed(stacks, key = { _, stack -> stack.id }) { index, stack ->
            VibeStackCard(
                stack = stack,
                index = index,
                artworkFor = artworkFor,
                accent = accentFor(stack.accentIndex),
                ink = ink,
                muted = muted,
                motion = motion,
                onPlay = { onPlay(stack) },
            )
        }
    }
}

@Composable
private fun VibeStackCard(
    stack: VibeStack,
    index: Int,
    artworkFor: (String?) -> Uri?,
    accent: Color,
    ink: Color,
    muted: Color,
    motion: SpringSpecs,
    onPlay: () -> Unit,
) {
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.955f else 1f,
        animationSpec = motion.snappy(),
        label = "stackPress",
    )
    val shape = RoundedCornerShape(26.dp)
    val covers = stack.coverArtIds

    Box(
        modifier = Modifier
            .width(178.dp)
            .height(218.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(shape)
            .background(
                Brush.linearGradient(
                    listOf(accent.copy(alpha = 0.55f), ink.copy(alpha = 0.18f)),
                )
            )
            .border(1.dp, ink.copy(alpha = 0.18f), shape)
            .clickable(interactionSource = interaction, indication = null, onClick = onPlay),
    ) {
        if (covers.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(
                        Brush.radialGradient(
                            listOf(accent.copy(alpha = 0.45f), Color.Transparent),
                        )
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "MIX",
                    color = ink.copy(alpha = 0.55f),
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                )
            }
        } else {
            covers.take(3).forEachIndexed { coverIndex, coverId ->
                val count = covers.take(3).size
                val spread = 18f * (coverIndex - (count - 1) / 2f)
                AsyncImage(
                    model = artworkFor(coverId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(x = spread.dp, y = (20 + coverIndex * 6).dp)
                        .size(126.dp)
                        .rotate(-9f + coverIndex * 9f)
                        .clip(RoundedCornerShape(16.dp))
                        .border(1.dp, Color.White.copy(alpha = 0.18f), RoundedCornerShape(16.dp)),
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0.30f to Color.Transparent,
                        0.72f to Color.Black.copy(alpha = 0.55f),
                        1f to Color.Black.copy(alpha = 0.82f),
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
        ) {
            Text(
                text = "MIX ${index + 1}",
                color = accent,
                style = MaterialTheme.typography.labelSmall,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = stack.title,
                color = Color(0xFFF5F3EC),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = stack.subtitle,
                color = Color(0xFFC9CFC8),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
                .size(30.dp)
                .background(Color.Black.copy(alpha = 0.35f), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                Icons.Filled.PlayArrow,
                contentDescription = null,
                tint = Color(0xFFF5F3EC),
                modifier = Modifier.size(17.dp),
            )
        }
    }
}

/** Slim "continue listening" band with live level bars. */
@Composable
fun NowPlayingStrip(
    playback: PlaybackUiState,
    accent: Color,
    ink: Color,
    muted: Color,
    animate: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (!playback.hasQueue) return
    val shape = RoundedCornerShape(22.dp)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(ink.copy(alpha = 0.10f))
            .border(1.dp, ink.copy(alpha = 0.12f), shape)
            .clickable(onClick = onClick)
            .padding(12.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(ink.copy(alpha = 0.10f)),
            ) {
                AsyncImage(
                    model = playback.artworkUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                SubsonicBadge(
                    size = 18.dp,
                    scrim = Color.Black.copy(alpha = 0.42f),
                    modifier = Modifier.align(Alignment.BottomStart).padding(2.dp),
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp),
            ) {
                Text(
                    text = "CONTINUE",
                    color = accent,
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = playback.title.ifBlank { "Nothing playing" },
                    color = ink,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = playback.artist,
                    color = muted,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            LevelBars(
                playing = playback.isPlaying,
                accent = accent,
                animate = animate,
                modifier = Modifier.size(width = 26.dp, height = 28.dp),
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(top = 2.dp)
                .height(2.dp)
                .background(ink.copy(alpha = 0.12f), RoundedCornerShape(2.dp)),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(playback.progress.coerceIn(0f, 1f))
                    .height(2.dp)
                    .background(accent, RoundedCornerShape(2.dp)),
            )
        }
    }
}

@Composable
private fun LevelBars(
    playing: Boolean,
    accent: Color,
    animate: Boolean,
    modifier: Modifier = Modifier,
) {
    val transition = if (animate) rememberInfiniteTransition(label = "levels") else null
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        repeat(4) { index ->
            val heightDp: Dp = if (transition != null && playing) {
                val value by transition.animateFloat(
                    initialValue = 0.25f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 620 + index * 190,
                            easing = FastOutSlowInEasing,
                        ),
                        repeatMode = RepeatMode.Reverse,
                    ),
                    label = "bar$index",
                )
                (8f + value * 16f).dp
            } else {
                (if (playing) 12f + index * 2f else 6f).dp
            }
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(heightDp)
                    .background(accent.copy(alpha = if (playing) 0.9f else 0.35f), CircleShape),
            )
        }
    }
}
