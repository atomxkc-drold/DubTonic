package com.androidharness.musicplayer.feature.player

import android.os.Build
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.Player
import coil3.compose.AsyncImage
import com.androidharness.musicplayer.ui.icons.PlayerIcons
import kotlin.math.roundToInt

/**
 * Full-bleed artwork player: the cover fills the screen, a vertical scrim carries the
 * type and controls, and everything sits on white ink so album colours never fight it.
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
    val track = details?.takeIf { it.id == playback.trackId }

    Box(Modifier.fillMaxSize().background(Color.Black)) {
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
                    0f to Color.Black.copy(alpha = 0.28f),
                    0.42f to Color.Black.copy(alpha = 0.52f),
                    0.72f to Color.Black.copy(alpha = 0.86f),
                    1f to Color.Black.copy(alpha = 0.96f),
                )
            )
        )

        Column(
            Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding()
                .padding(horizontal = 24.dp),
        ) {
            Row(
                Modifier.fillMaxWidth().height(56.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            }

            Spacer(Modifier.weight(1f))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = playback.title.ifBlank { "Nothing playing" },
                        color = Color.White,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 32.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = playback.artist.ifBlank { "Unknown artist" },
                        color = Color.White.copy(alpha = 0.72f),
                        fontSize = 17.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Spacer(Modifier.width(12.dp))
                RoundIconButton(
                    icon = if (track?.isFavorite == true) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    description = if (track?.isFavorite == true) "Remove from favorites" else "Add to favorites",
                    active = track?.isFavorite == true,
                    onClick = viewModel::toggleFavorite,
                )
                Spacer(Modifier.width(10.dp))
                RoundIconButton(
                    icon = PlayerIcons.Repeat,
                    description = "Repeat mode",
                    active = playback.repeatMode != Player.REPEAT_MODE_OFF,
                    onClick = viewModel::cycleRepeat,
                )
            }

            Spacer(Modifier.height(18.dp))

            Text(
                text = playback.album.ifBlank { " " },
                color = Color.White.copy(alpha = 0.52f),
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(Modifier.height(12.dp))

            SeekBar(
                positionMs = playback.positionMs,
                durationMs = playback.durationMs,
                onSeek = viewModel::seekToFraction,
            )

            Row(
                Modifier.fillMaxWidth().padding(top = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(clock(playback.positionMs), color = Color.White.copy(alpha = 0.55f), fontSize = 12.sp)
                Text(
                    text = "-${clock((playback.durationMs - playback.positionMs).coerceAtLeast(0L))}",
                    color = Color.White.copy(alpha = 0.55f),
                    fontSize = 12.sp,
                )
            }

            Row(
                Modifier.fillMaxWidth().padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(Modifier.weight(1f))
                IconButton(onClick = viewModel::previous, modifier = Modifier.size(64.dp)) {
                    Icon(
                        PlayerIcons.SkipPrevious, "Previous",
                        tint = Color.White, modifier = Modifier.size(40.dp),
                    )
                }
                Spacer(Modifier.width(20.dp))
                IconButton(onClick = viewModel::playPause, modifier = Modifier.size(76.dp)) {
                    Icon(
                        imageVector = if (playback.isPlaying) PlayerIcons.Pause else Icons.Filled.PlayArrow,
                        contentDescription = if (playback.isPlaying) "Pause" else "Play",
                        tint = Color.White,
                        modifier = Modifier.size(52.dp),
                    )
                }
                Spacer(Modifier.width(20.dp))
                IconButton(onClick = viewModel::next, modifier = Modifier.size(64.dp)) {
                    Icon(
                        PlayerIcons.SkipNext, "Next",
                        tint = Color.White, modifier = Modifier.size(40.dp),
                    )
                }
                Spacer(Modifier.weight(1f))
            }

            Row(
                Modifier.fillMaxWidth().padding(top = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PlainIconButton(PlayerIcons.Lyrics, "Lyrics") {}
                Spacer(Modifier.weight(1f))
                Row(
                    Modifier.height(46.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.White.copy(alpha = 0.10f))
                        .border(1.dp, Color.White.copy(alpha = 0.22f), RoundedCornerShape(50)),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    PillSegment(PlayerIcons.Smartphone, "Output device") {}
                    PillDivider()
                    PillSegment(
                        icon = PlayerIcons.Shuffle,
                        description = "Shuffle",
                        active = playback.shuffleEnabled,
                        onClick = viewModel::toggleShuffle,
                    )
                    PillDivider()
                    PillSegment(Icons.Filled.Person, "Profile") {}
                }
                Spacer(Modifier.weight(1f))
                PlainIconButton(PlayerIcons.Queue, "Queue") {}
            }

            Text(
                text = Build.MODEL ?: "This device",
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                color = Color.White.copy(alpha = 0.45f),
                fontSize = 11.sp,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun RoundIconButton(
    icon: ImageVector,
    description: String,
    active: Boolean,
    onClick: () -> Unit,
) {
    Box(
        Modifier.size(48.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = if (active) 0.18f else 0.06f))
            .border(1.dp, Color.White.copy(alpha = if (active) 0.60f else 0.30f), CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(icon, contentDescription = description, tint = Color.White, modifier = Modifier.size(21.dp))
    }
}

@Composable
private fun PlainIconButton(icon: ImageVector, description: String, onClick: () -> Unit) {
    Box(
        Modifier.size(44.dp).clip(CircleShape).clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            icon, contentDescription = description,
            tint = Color.White.copy(alpha = 0.85f), modifier = Modifier.size(24.dp),
        )
    }
}

@Composable
private fun PillSegment(
    icon: ImageVector,
    description: String,
    active: Boolean = false,
    onClick: () -> Unit,
) {
    Box(
        Modifier.width(78.dp).fillMaxHeight().clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            icon, contentDescription = description,
            tint = Color.White.copy(alpha = if (active) 1f else 0.80f),
            modifier = Modifier.size(22.dp),
        )
    }
}

@Composable
private fun PillDivider() {
    Box(Modifier.width(1.dp).height(22.dp).background(Color.White.copy(alpha = 0.18f)))
}

/**
 * Thin scrubber: no thumb, so the bar reads as a rule until you touch it.
 * Drag previews locally and only commits on release.
 */
@Composable
private fun SeekBar(
    positionMs: Long,
    durationMs: Long,
    onSeek: (Float) -> Unit,
) {
    val actual = if (durationMs > 0L) {
        (positionMs.toFloat() / durationMs.toFloat()).coerceIn(0f, 1f)
    } else {
        0f
    }
    var widthPx by remember { mutableFloatStateOf(1f) }
    var dragging by remember { mutableStateOf(false) }
    var dragFraction by remember { mutableFloatStateOf(0f) }
    val shown = if (dragging) dragFraction else actual

    Box(
        Modifier.fillMaxWidth()
            .height(28.dp)
            .onSizeChanged { widthPx = it.width.toFloat().coerceAtLeast(1f) }
            .pointerInput(widthPx) {
                detectTapGestures { offset ->
                    onSeek((offset.x / widthPx).coerceIn(0f, 1f))
                }
            }
            .pointerInput(widthPx) {
                detectHorizontalDragGestures(
                    onDragStart = { offset ->
                        dragging = true
                        dragFraction = (offset.x / widthPx).coerceIn(0f, 1f)
                    },
                    onDragEnd = {
                        dragging = false
                        onSeek(dragFraction)
                    },
                    onDragCancel = { dragging = false },
                    onHorizontalDrag = { change, _ ->
                        dragFraction = (change.position.x / widthPx).coerceIn(0f, 1f)
                    },
                )
            },
        contentAlignment = Alignment.Center,
    ) {
        Box(
            Modifier.fillMaxWidth().height(3.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(Color.White.copy(alpha = 0.22f))
        )
        Box(
            Modifier.fillMaxWidth(shown).height(3.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(Color.White.copy(alpha = 0.92f))
        )
    }
}

private fun clock(ms: Long): String {
    val totalSeconds = (ms / 1000L).toInt().coerceAtLeast(0)
    val minutes = totalSeconds / 60
    val seconds = (totalSeconds % 60).toString().padStart(2, '0')
    return "$minutes:$seconds"
}
