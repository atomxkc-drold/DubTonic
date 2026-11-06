package com.androidharness.musicplayer.feature.player

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.androidharness.musicplayer.ui.icons.PlayerIcons
import com.androidharness.musicplayer.ui.glass.BlurTwistBackground

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NowPlayingScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onBack: () -> Unit,
    viewModel: PlayerViewModel = hiltViewModel(),
) {
    val playback by viewModel.playback.collectAsStateWithLifecycle()
    val colors by viewModel.colors.collectAsStateWithLifecycle()
    val ui by viewModel.uiSettings.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        BlurTwistBackground(
            artworkUri = playback.artworkUri,
            colors = colors,
            blurRadiusDp = ui.blurRadiusDp,
            saturation = ui.saturation,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 24.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
                Spacer(Modifier.weight(1f))
                Text(
                    text = "NOW PLAYING",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White.copy(alpha = 0.6f),
                )
                Spacer(Modifier.weight(1f))
                Spacer(Modifier.size(48.dp))
            }

            Spacer(Modifier.weight(0.6f))

            with(sharedTransitionScope) {
                AsyncImage(
                    model = playback.artworkUri,
                    contentDescription = playback.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(0.82f)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(30.dp))
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = SHARED_ART_KEY),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
                )
            }

            Spacer(Modifier.weight(0.5f))

            Text(
                text = playback.title.ifBlank { "Nothing playing" },
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = listOf(playback.artist, playback.album)
                    .filter { it.isNotBlank() }
                    .joinToString(" — "),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.7f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(Modifier.height(20.dp))
            SeekBar(playback = playback, onSeek = viewModel::seekToFraction)
            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = viewModel::toggleShuffle) {
                    Icon(
                        PlayerIcons.Shuffle,                        contentDescription = "Shuffle",
                        tint = if (playback.shuffleEnabled) {
                            colors.primary
                        } else {
                            Color.White.copy(alpha = 0.55f)
                        },
                    )
                }
                IconButton(onClick = viewModel::previous) {
                    Icon(
                        PlayerIcons.SkipPrevious,
                        contentDescription = "Previous",
                        modifier = Modifier.size(34.dp),
                    )
                }
                FilledIconButton(
                    onClick = viewModel::playPause,
                    modifier = Modifier.size(68.dp),
                    shape = CircleShape,
                ) {
                    Icon(
                        imageVector = if (playback.isPlaying) {
                            PlayerIcons.Pause
                        } else {
                            Icons.Filled.PlayArrow
                        },
                        contentDescription = if (playback.isPlaying) "Pause" else "Play",
                        modifier = Modifier.size(34.dp),
                    )
                }
                IconButton(onClick = viewModel::next) {
                    Icon(
                        PlayerIcons.SkipNext,
                        contentDescription = "Next",
                        modifier = Modifier.size(34.dp),
                    )
                }
                IconButton(onClick = viewModel::cycleRepeat) {
                    Icon(
                        PlayerIcons.Repeat,
                        contentDescription = "Repeat",
                        tint = if (playback.repeatMode == 0) {
                            Color.White.copy(alpha = 0.55f)
                        } else {
                            colors.primary
                        },
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun SeekBar(
    playback: PlaybackUiState,
    onSeek: (Float) -> Unit,
) {
    var dragging by remember { mutableFloatStateOf(-1f) }
    val value = if (dragging >= 0f) dragging else playback.progress

    Column(modifier = Modifier.fillMaxWidth()) {
        Slider(
            value = value,
            onValueChange = { dragging = it },
            onValueChangeFinished = {
                if (dragging >= 0f) onSeek(dragging)
                dragging = -1f
            },
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.White,
                inactiveTrackColor = Color.White.copy(alpha = 0.24f),
            ),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = formatTime(playback.positionMs),
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.6f),
            )
            Text(
                text = formatTime(playback.durationMs),
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.6f),
            )
        }
    }
}

private fun formatTime(milliseconds: Long): String {
    val totalSeconds = (milliseconds.coerceAtLeast(0) / 1_000).toInt()
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%d:%02d".format(minutes, seconds)
}

@Suppress("unused")
private val AccentBrush = Brush.linearGradient(listOf(Color.White, Color.Transparent))
