package com.dubtonic.player.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.weight
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dubtonic.player.data.LoadState
import com.dubtonic.player.data.model.Album
import com.dubtonic.player.data.model.Song
import com.dubtonic.player.di.AppContainer
import com.dubtonic.player.player.PlayerBus
import com.dubtonic.player.player.PlayerUiState
import kotlinx.coroutines.delay

/**
 * HomeScreen — completely redesigned.
 *
 * Top hero section mirrors the now-playing screen from the reference photos:
 * a large album cover fills the upper portion, with song title, artist, star
 * rating, format/bitrate info and EQ badge overlaid. Below the hero the
 * familiar album grids remain.
 *
 * The hero album art auto-shuffles every 5 seconds: the cover instantly
 * swaps to a different album with NO animation — just a blink and the next
 * cover is shown.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    container: AppContainer,
    onOpenAlbum: (String) -> Unit,
    onOpenArtist: (String) -> Unit,
    onOpenPlayer: () -> Unit
) {
    val library = container.getLibrary()
    val server: com.dubtonic.player.data.api.ServerConfig? =
        container.getSettings().getServerNow()
    val imageLoader = container.getImageLoader()
    val playerState: PlayerUiState = PlayerBus.INSTANCE.getState()

    // ---- Album lists (existing data sources) ----
    val albumsState by library.getAlbums().collectAsState()
    val newestState by library.getNewest().collectAsState()
    val randomState by library.getRandomAlbums().collectAsState()

    val readyAlbums: List<Album> = (albumsState as? LoadState.Ready)?.getValue() ?: emptyList()
    val readyNewest: List<Album> = (newestState as? LoadState.Ready)?.getValue() ?: emptyList()
    val readyRandom: List<Album> = (randomState as? LoadState.Ready)?.getValue() ?: emptyList()

    // ---- Hero: now-playing style header ----
    val heroAlbums = if (readyRandom.isNotEmpty()) readyRandom else readyAlbums
    var heroIndex by remember { mutableStateOf(0) }

    // Auto-shuffle every 5 seconds — instant switch, no animation.
    LaunchedEffect(heroAlbums.size) {
        while (true) {
            if (heroAlbums.isNotEmpty()) {
                heroIndex = (heroIndex + 1) % heroAlbums.size
            }
            delay(5000L)
        }
    }

    val heroAlbum: Album? = if (heroAlbums.isNotEmpty()) heroAlbums[heroIndex] else null
    val heroCoverUrl: String? =
        if (heroAlbum != null && server != null)
            com.dubtonic.player.data.LibraryRepository.coverArtUrl(
                library, server, heroAlbum.id, 0
            )
        else null

    // Current song info (from player state) shown under the art when playing.
    val currentSong: Song? = playerState.current?.song

    Column(modifier = Modifier.fillMaxSize()) {
        // ===== HERO SECTION (top part of page — now-playing look) =====
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.BottomCenter
        ) {
            // Album art — large cover. Instant swap every 5s (no animation).
            if (heroCoverUrl != null) {
                AsyncImage(
                    model = heroCoverUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp)),
                    imageLoader = imageLoader
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            // Gradient overlay so text is readable over the art.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    background = Brush.LinearGradient(
                        colors = listOf(
                            Color(0x00000000),
                            MaterialTheme.colorScheme.background
                        )
                    )
                )

                // Song / album title + artist overlaid on the art.
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, bottom = 24.dp),
                    verticalArrangement = Arrangement.spacingBy(4.dp)
                ) {
                    Text(
                        text = heroAlbum?.name ?: "No album",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 1
                    )
                    Text(
                        text = heroAlbum?.artist ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        maxLines = 1
                    )
                }
            }
        }

        // ===== SONG INFO BAR (below the art) =====
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = currentSong?.title ?: heroAlbum?.name ?: "Bright Lights",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = currentSong?.artist ?: heroAlbum?.artist ?: "Derek Pope",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Star rating (☆☆☆☆☆)
            Row(verticalAlignment = Alignment.CenterVertically) {
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

        // ===== FORMAT / EQ / QUEUE INFO =====
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${currentSong?.contentType?.uppercase() ?: "FLAC"}  ·  ${currentSong?.bitRate ?: 1792} kbps",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
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
            verticalAlignment = Alignment.CenterVertically,
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
                .padding(horizontal = 32.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onOpenPlayer() }) {
                Icon(
                    imageVector = Icons.Default.Shuffle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(onClick = { onOpenPlayer() }) {
                Icon(
                    imageVector = Icons.Default.SkipPrevious,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(
                onClick = { onOpenPlayer() },
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = if (playerState.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    imageSize = 28.dp
                )
            }
            IconButton(onClick = { onOpenPlayer() }) {
                Icon(
                    imageVector = Icons.Default.SkipNext,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            IconButton(onClick = { onOpenPlayer() }) {
                Icon(
                    imageVector = Icons.Default.Repeat,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // Volume slider at bottom
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
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

        androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(16.dp))

        // ===== EXISTING CONTENT BELOW HERO =====
        LazyColumn(
            modifier = Modifier.fillMaxWeight(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Text(
                    text = "Newest",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            items(readyNewest.take(8)) { album ->
                AlbumRow(
                    album = album,
                    coverUrl = if (server != null)
                        com.dubtonic.player.data.LibraryRepository.coverArtUrl(
                            library, server, album.id, 0
                        )
                    else null,
                    onClick = { onOpenAlbum(album.id) },
                    imageLoader = imageLoader
                )
            }
        }
    }
}

/** Small album row used below the hero. */
@Composable
private fun AlbumRow(
    album: Album,
    coverUrl: String?,
    onClick: () -> Unit,
    imageLoader: coil.ImageLoader
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = coverUrl,
            contentDescription = null,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(8.dp)),
            imageLoader = imageLoader
        )
        Text(
            text = album.name,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp).weight(1f)
        )
        Text(
            text = album.artist,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun formatMs(ms: Long): String {
    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "$minutes:${String("%02d", seconds)}"
}
