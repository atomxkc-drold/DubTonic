package com.dubtonic.player.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dubtonic.player.data.LoadState
import com.dubtonic.player.data.api.ServerConfig
import com.dubtonic.player.data.model.Song
import com.dubtonic.player.di.AppContainer
import com.dubtonic.player.player.PlayerBus
import com.dubtonic.player.player.PlayerUiState
import com.dubtonic.player.player.QueueItem
import kotlinx.coroutines.delay

/**
 * PlayerScreen — now-playing screen matching the reference photos.
 *
 * Dark and light mode are handled automatically by the existing
 * DubTonicTheme wrapper (which selects DarkScheme / LightScheme based on
 * the user's theme setting). This screen simply reads MaterialTheme
 * colors so it adapts to either mode.
 *
 * Layout:
 *   Top bar: back, cast, sleep timer, EQ, queue, more
 *   Hero:    large album art covering the upper ~60% of the screen
 *   Info:    song title, artist, star rating, format/EQ badge
 *   Bar:     progress slider + time labels
 *   Controls: shuffle / prev / play-pause / next / repeat
 *   Bottom:  volume slider
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerScreen(
    container: AppContainer,
    onBack: () -> Unit,
    onOpenQueue: () -> Unit
) {
    val library = container.getLibrary()
    val server: ServerConfig? = container.getSettings().getServerNow()
    val imageLoader = container.getImageLoader()
    val playerState: PlayerUiState = PlayerBus.INSTANCE.getState()

    val queue: List<QueueItem> = playerState.queue
    val current: QueueItem? = playerState.current
    val song: Song? = current?.song

    val coverUrl: String? =
        if (song != null && server != null)
            com.dubtonic.player.data.LibraryRepository.coverArtUrl(
                library, server, song.id, 500
            )
        else null

    Column(modifier = Modifier.fillMaxSize()) {
        // ===== TOP BAR =====
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Cast, null, MaterialTheme.colorScheme.onSurface, Modifier.size(20.dp))
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Timer, null, MaterialTheme.colorScheme.onSurface, Modifier.size(20.dp))
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Eq, null, MaterialTheme.colorScheme.onSurface, Modifier.size(20.dp))
                }
                IconButton(onClick = onOpenQueue) {
                    Icon(Icons.Default.QueueMusic, null, MaterialTheme.colorScheme.onSurface, Modifier.size(20.dp))
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Default.MoreVert, null, MaterialTheme.colorScheme.onSurface, Modifier.size(20.dp))
                }
            }
        }

        // ===== HERO: ALBUM ART =====
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            if (coverUrl != null) {
                AsyncImage(
                    model = coverUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(320.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    imageLoader = imageLoader
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(320.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        }

        // ===== SONG INFO =====
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacingBy(8.dp)
        ) {
            Text(
                text = song?.title ?: "Bright Lights",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = song?.artist ?: "Derek Pope",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Star rating
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        modifier = androidx.compose.ui.Modifier.size(16.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        androidx.compose.material3.Divider()

        // ===== FORMAT / EQ INFO =====
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${song?.contentType?.uppercase() ?: "FLAC"}  ·  ${song?.bitRate ?: 1792} kbps",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Eq,
                    contentDescription = null,
                    modifier = androidx.compose.ui.Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "EQ",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "2/5",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // ===== PROGRESS BAR =====
        val position = playerState.positionMs
        val duration = playerState.durationMs
        val progress = if (duration > 0) position.toFloat() / duration else 0f

        Slider(
            value = progress.coerceIn(0f, 1f),
            onValueChange = {},
            enabled = duration > 0,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(4.dp),
            colors = androidx.compose.material3.SliderDefaults.colors(
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatMs(position),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = formatMs(duration),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // ===== PLAYBACK CONTROLS =====
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            IconButton(onClick = { onOpenQueue() }) {
                Icon(
                    imageVector = Icons.Default.Shuffle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(onClick = { onOpenQueue() }) {
                Icon(
                    imageVector = Icons.Default.SkipPrevious,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(
                onClick = { onOpenQueue() },
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = if (playerState.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    imageSize = 32.dp
                )
            }
            IconButton(onClick = { onOpenQueue() }) {
                Icon(
                    imageVector = Icons.Default.SkipNext,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(onClick = { onOpenQueue() }) {
                Icon(
                    imageVector = Icons.Default.Repeat,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // ===== VOLUME SLIDER =====
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 8.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.VolumeUp,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Slider(
                value = playerState.volume,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                colors = androidx.compose.material3.SliderDefaults.colors(
                    activeTrackColor = MaterialTheme.colorScheme.primary,
                    inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
            Text(
                text = "${(playerState.volume * 100).roundToInt()}%",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

private fun formatMs(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "$minutes:${String("%02d", seconds)}"
}
