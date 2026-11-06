package com.androidharness.musicplayer.feature.library

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.androidharness.musicplayer.feature.player.MiniPlayer
import com.androidharness.musicplayer.feature.player.PlayerViewModel
import com.androidharness.musicplayer.ui.glass.BlurTwistBackground
import com.androidharness.musicplayer.ui.image.AlbumColors
import com.androidharness.musicplayer.ui.icons.PlayerIcons

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun LibraryScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onOpenPlayer: () -> Unit,
    onOpenSettings: () -> Unit,
    viewModel: LibraryViewModel = hiltViewModel(),
    playerViewModel: PlayerViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val playback by playerViewModel.playback.collectAsStateWithLifecycle()
    val colors by playerViewModel.colors.collectAsStateWithLifecycle()
    val ui by playerViewModel.uiSettings.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        BlurTwistBackground(
            artworkUri = playback.artworkUri,
            colors = colors.takeIf { playback.hasQueue } ?: AlbumColors(),
            blurRadiusDp = ui.blurRadiusDp,
            saturation = ui.saturation,
        )

        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = {
                sharedTransitionScope.MiniPlayer(
                    state = playback,
                    animatedVisibilityScope = animatedVisibilityScope,
                    onClick = onOpenPlayer,
                    onPlayPause = playerViewModel::playPause,
                    modifier = Modifier.navigationBarsPadding(),
                )
            },
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "HARNESS",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White.copy(alpha = 0.55f),
                        )
                        Text(
                            text = "Library",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color.White,
                        )
                    }
                    if (state.isSyncing) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.White,
                            strokeWidth = 2.dp,
                        )
                        Spacer(Modifier.size(12.dp))
                    } else {
                        IconButton(onClick = { viewModel.onIntent(LibraryIntent.Sync) }) {
                            Icon(
                                Icons.Filled.Refresh,
                                contentDescription = "Sync library",
                                tint = Color.White,
                            )
                        }
                    }
                    IconButton(onClick = onOpenSettings) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Settings",
                            tint = Color.White,
                        )
                    }
                }

                OutlinedTextField(
                    value = state.query,
                    onValueChange = { viewModel.onIntent(LibraryIntent.QueryChanged(it)) },
                    placeholder = { Text("Search title, artist, album") },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
                    singleLine = true,
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.White.copy(alpha = 0.45f),
                        unfocusedBorderColor = Color.White.copy(alpha = 0.18f),
                        focusedPlaceholderColor = Color.White.copy(alpha = 0.4f),
                        unfocusedPlaceholderColor = Color.White.copy(alpha = 0.4f),
                        focusedLeadingIconColor = Color.White.copy(alpha = 0.7f),
                        unfocusedLeadingIconColor = Color.White.copy(alpha = 0.7f),
                    ),
                    modifier = Modifier.fillMaxWidth(),
                )

                state.message?.let { message ->
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.75f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color.White.copy(alpha = 0.08f))
                            .clickable { viewModel.onIntent(LibraryIntent.DismissMessage) }
                            .padding(12.dp),
                    )
                }

                Spacer(Modifier.height(12.dp))

                if (state.tracks.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = if (state.query.isBlank()) {
                                "No library yet. Connect a Subsonic server, then sync."
                            } else {
                                "No matches for \"${state.query}\"."
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.6f),
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        itemsIndexed(
                            items = state.tracks,
                            key = { _, item -> item.track.id },
                        ) { index, item ->
                            TrackRow(
                                item = item,
                                onPlay = { viewModel.onIntent(LibraryIntent.Play(index)) },
                                onFavorite = {
                                    viewModel.onIntent(
                                        LibraryIntent.ToggleFavorite(
                                            trackId = item.track.id,
                                            favorite = !item.track.isFavorite,
                                        )
                                    )
                                },
                                onDownload = {
                                    viewModel.onIntent(
                                        if (item.track.isDownloaded) {
                                            LibraryIntent.RemoveDownload(item.track.id)
                                        } else {
                                            LibraryIntent.Download(item.track.id)
                                        }
                                    )
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TrackRow(
    item: TrackUi,
    onPlay: () -> Unit,
    onFavorite: () -> Unit,
    onDownload: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White.copy(alpha = 0.07f))
            .clickable(onClick = onPlay)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = item.artworkUri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp)),
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = item.track.title,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = item.track.artist,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.6f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Text(
            text = formatDuration(item.track.durationMs),
            style = MaterialTheme.typography.labelSmall,
            color = Color.White.copy(alpha = 0.55f),
        )
        IconButton(onClick = onDownload) {
            Icon(
                imageVector = if (item.track.isDownloaded) {
                    PlayerIcons.Check
                } else {
                    PlayerIcons.Download
                },
                contentDescription = if (item.track.isDownloaded) {
                    "Remove download"
                } else {
                    "Download"
                },
                tint = if (item.track.isDownloaded) {
                    Color(0xFF9FD8CB)
                } else {
                    Color.White.copy(alpha = 0.5f)
                },
            )
        }
        IconButton(onClick = onFavorite) {
            Icon(
                imageVector = if (item.track.isFavorite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Filled.FavoriteBorder
                },
                contentDescription = "Favorite",
                tint = if (item.track.isFavorite) {
                    Color(0xFFE8A0A0)
                } else {
                    Color.White.copy(alpha = 0.5f)
                },
            )
        }
    }
}

private fun formatDuration(milliseconds: Long): String {
    val seconds = (milliseconds.coerceAtLeast(0) / 1_000).toInt()
    return "%d:%02d".format(seconds / 60, seconds % 60)
}

@Suppress("unused")
private val BackgroundBrush = Brush.verticalGradient(listOf(Color.Black, Color.Transparent))
