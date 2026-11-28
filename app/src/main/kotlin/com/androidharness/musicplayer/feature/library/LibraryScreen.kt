package com.androidharness.musicplayer.feature.library

import android.net.Uri
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidharness.musicplayer.ui.motion.motionBlurScroll
import coil3.compose.AsyncImage
import com.androidharness.musicplayer.feature.player.AudioQuality
import com.androidharness.musicplayer.feature.player.MiniPlayer
import com.androidharness.musicplayer.feature.player.PlayerViewModel
import com.androidharness.musicplayer.feature.player.QualityBadge
import com.androidharness.musicplayer.feature.player.audioQuality
import com.androidharness.musicplayer.ui.chrome.ChromeBackdrop
import com.androidharness.musicplayer.ui.chrome.ChromeTopBar
import com.androidharness.musicplayer.ui.chrome.LocalChrome
import com.androidharness.musicplayer.ui.chrome.chromeFieldColors
import com.androidharness.musicplayer.ui.icons.PlayerIcons
import com.androidharness.musicplayer.ui.theme.SpringSpecs

private val Ink: Color
    @Composable get() = LocalChrome.current.base
private val PillBase: Color
    @Composable get() = LocalChrome.current.ink.copy(alpha = 0.08f)
private val StarYellow: Color
    @Composable get() = LocalChrome.current.accent
private val AccentTeal: Color
    @Composable get() = LocalChrome.current.accent
private val InkText: Color
    @Composable get() = LocalChrome.current.ink
private val DefaultArtColor = Color(0xFF4A5A70)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun LibraryScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onBack: () -> Unit,
    onOpenPlayer: () -> Unit,
    onOpenSettings: () -> Unit,
    viewModel: LibraryViewModel = hiltViewModel(),
    playerViewModel: PlayerViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val playback by playerViewModel.playback.collectAsStateWithLifecycle()
    val colors by playerViewModel.colors.collectAsStateWithLifecycle()
    val ui by playerViewModel.uiSettings.collectAsStateWithLifecycle()
    val session by playerViewModel.session.collectAsStateWithLifecycle()
    val motion = remember(ui) { SpringSpecs(ui) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Ink)
    ) {
        ChromeBackdrop(
            artwork = playback.artworkUri,
            primary = colors.primary.takeIf { playback.hasQueue },
            secondary = colors.secondary.takeIf { playback.hasQueue },
            blurRadiusDp = ui.blurRadiusDp,
            reducedMotion = ui.reducedMotion,
        )

        Scaffold(
            containerColor = Color.Transparent,
            // The column below applies statusBarsPadding itself; Scaffold's own top inset
            // would add a second status-bar-sized gap under the notification shade.
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
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
                    .motionBlurScroll(
                        enabled = ui.scrollMotionBlur && !ui.reducedMotion,
                        intensityPercent = ui.motionBlurIntensity,
                    )
                    .padding(horizontal = 16.dp),
            ) {
                Header(
                    subtitle = state.subtitle(),
                    username = session?.username,
                    isSyncing = state.isSyncing,
                    onSync = { viewModel.onIntent(LibraryIntent.Sync) },
                    onBack = onBack,
                    onOpenSettings = onOpenSettings,
                )

                Spacer(Modifier.height(10.dp))

                OutlinedTextField(
                    value = state.query,
                    onValueChange = { viewModel.onIntent(LibraryIntent.QueryChanged(it)) },
                    placeholder = { Text("Search library") },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = null,
                            tint = InkText.copy(alpha = 0.55f),
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(28.dp),
                    colors = chromeFieldColors(),
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(Modifier.height(10.dp))

                SectionSelector(
                    selected = state.section,
                    onSelect = { viewModel.onIntent(LibraryIntent.SectionChanged(it)) },
                )

                if (state.section == LibrarySection.TRACKS) {
                    Spacer(Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        FilterChipPill(
                            label = "All",
                            selected = state.filter == LibraryFilter.ALL,
                            onClick = {
                                viewModel.onIntent(LibraryIntent.FilterChanged(LibraryFilter.ALL))
                            },
                        )
                        FilterChipPill(
                            label = "Favorites",
                            selected = state.filter == LibraryFilter.FAVORITES,
                            onClick = {
                                viewModel.onIntent(
                                    LibraryIntent.FilterChanged(LibraryFilter.FAVORITES)
                                )
                            },
                        )
                        FilterChipPill(
                            label = "Offline",
                            selected = state.filter == LibraryFilter.DOWNLOADED,
                            onClick = {
                                viewModel.onIntent(
                                    LibraryIntent.FilterChanged(LibraryFilter.DOWNLOADED)
                                )
                            },
                        )
                    }
                }

                state.message?.let { message ->
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodySmall,
                        color = InkText.copy(alpha = 0.75f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(18.dp))
                            .background(PillBase)
                            .clickable { viewModel.onIntent(LibraryIntent.DismissMessage) }
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                    )
                }

                Spacer(Modifier.height(12.dp))

                when (state.section) {
                    LibrarySection.TRACKS -> TracksContent(
                        state = state,
                        motion = motion,
                        colorFor = viewModel::colorFor,
                        onIntent = viewModel::onIntent,
                    )
                    LibrarySection.ALBUMS -> AlbumsContent(
                        state = state,
                        motion = motion,
                        colorFor = viewModel::colorFor,
                        onIntent = viewModel::onIntent,
                    )
                    LibrarySection.ARTISTS -> ArtistsContent(
                        state = state,
                        motion = motion,
                        colorFor = viewModel::colorFor,
                        onIntent = viewModel::onIntent,
                    )
                    LibrarySection.PLAYLISTS -> PlaylistsContent(
                        state = state,
                        motion = motion,
                        onIntent = viewModel::onIntent,
                    )
                }
            }
        }
    }
}

private fun LibraryUiState.subtitle(): String = when (section) {
    LibrarySection.TRACKS -> "${tracks.size} songs · $artistCount artists"
    LibrarySection.ALBUMS -> "${albums.size} albums"
    LibrarySection.ARTISTS -> "${artists.size} artists"
    LibrarySection.PLAYLISTS -> "${playlists.size} playlists"
}

@Composable
private fun ColumnScope.TracksContent(
    state: LibraryUiState,
    motion: SpringSpecs,
    colorFor: suspend (Uri?) -> Color,
    onIntent: (LibraryIntent) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TracksToolbar(
            sort = state.trackSort,
            onSortChanged = { onIntent(LibraryIntent.TrackSortChanged(it)) },
            onShuffle = { onIntent(LibraryIntent.ShuffleAll) },
        )
        Spacer(Modifier.height(10.dp))
    }

    if (state.tracks.isEmpty()) {
        EmptyState(
            query = state.query,
            filter = state.filter,
            section = state.section,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )
    } else {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            itemsIndexed(
                items = state.tracks,
                key = { _, item -> item.track.id },
            ) { index, item ->
                TrackPill(
                    item = item,
                    motion = motion,
                    colorFor = colorFor,
                    onPlay = { onIntent(LibraryIntent.Play(index)) },
                    onFavorite = {
                        onIntent(
                            LibraryIntent.ToggleFavorite(
                                trackId = item.track.id,
                                favorite = !item.track.isFavorite,
                            )
                        )
                    },
                    onDownload = {
                        onIntent(
                            if (item.track.isDownloaded) {
                                LibraryIntent.RemoveDownload(item.track.id)
                            } else {
                                LibraryIntent.Download(item.track.id)
                            }
                        )
                    },
                    onGoToArtist = {
                        onIntent(
                            LibraryIntent.PlayArtist(item.track.artistId)
                        )
                    },
                    onGoToAlbum = {
                        onIntent(
                            LibraryIntent.PlayAlbum(item.track.albumId)
                        )
                    },
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.AlbumsContent(
    state: LibraryUiState,
    motion: SpringSpecs,
    colorFor: suspend (Uri?) -> Color,
    onIntent: (LibraryIntent) -> Unit,
) {
    if (state.albums.isEmpty()) {
        EmptyState(
            query = state.query,
            filter = state.filter,
            section = state.section,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )
    } else {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(
                items = state.albums,
                key = { it.album.id },
            ) { item ->
                AlbumPill(
                    item = item,
                    motion = motion,
                    colorFor = colorFor,
                    onPlay = { onIntent(LibraryIntent.PlayAlbum(item.album.id)) },
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.ArtistsContent(
    state: LibraryUiState,
    motion: SpringSpecs,
    colorFor: suspend (Uri?) -> Color,
    onIntent: (LibraryIntent) -> Unit,
) {
    if (state.artists.isEmpty()) {
        EmptyState(
            query = state.query,
            filter = state.filter,
            section = state.section,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )
    } else {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(
                items = state.artists,
                key = { it.artist.id },
            ) { item ->
                ArtistPill(
    item = item,
    motion = motion,
    colorFor = colorFor,
    onPlay = { onIntent(LibraryIntent.PlayArtist(item.artist.id)) },
    isSyncing = state.isSyncing,
)
            }
        }
    }
}

@Composable
private fun ColumnScope.PlaylistsContent(
    state: LibraryUiState,
    motion: SpringSpecs,
    onIntent: (LibraryIntent) -> Unit,
) {
    if (state.playlists.isEmpty()) {
        EmptyState(
            query = state.query,
            filter = state.filter,
            section = state.section,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )
    } else {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(
                items = state.playlists,
                key = { it.playlist.id },
            ) { item ->
                PlaylistPill(
                    item = item,
                    motion = motion,
                    onPlay = { onIntent(LibraryIntent.PlayPlaylist(item.playlist.id)) },
                )
            }
        }
    }
}

@Composable
private fun TracksToolbar(
    sort: TrackSort,
    onSortChanged: (TrackSort) -> Unit,
    onShuffle: () -> Unit,
) {
    var menuOpen by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(PillBase)
                    .clickable { menuOpen = true }
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = sort.label(),
                    color = InkText,
                    style = MaterialTheme.typography.labelLarge,
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Sort",
                    tint = InkText.copy(alpha = 0.6f),
                )
            }
            // Glassmorphic dropdown menu with proper backdrop scrim
            DropdownMenu(
                expanded = menuOpen,
                onDismissRequest = { menuOpen = false },
                // Use a glass surface with backdrop blur and scrim to prevent bleed-through
                containerColor = PillBase,
                // Apply backdrop filter to prevent underlying content bleed
                modifier = Modifier
                    .background(
                        GlassSurface(
                            shape = RoundedCornerShape(20.dp),
                            alpha = 0.22f,
                            borderAlpha = 0.35f,
                        )
                    ),
            ) {
                TrackSort.entries.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = option.label(),
                                color = if (option == sort) AccentTeal else InkText,
                            )
                        },
                        onClick = {
                            menuOpen = false
                            onSortChanged(option)
                        },
                    )
                }
            }
        }

        Spacer(Modifier.weight(1f))

        FilledIconButton(
            onClick = onShuffle,
            modifier = Modifier.size(40.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = PillBase,
                contentColor = InkText,
            ),
        ) {
            Icon(
                imageVector = PlayerIcons.Shuffle,
                contentDescription = "Shuffle all",
                modifier = Modifier.size(20.dp),
            )
        }
    }
}

@Composable
private fun SectionSelector(
    selected: LibrarySection,
    onSelect: (LibrarySection) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        LibrarySection.entries.forEach { section ->
            FilterChipPill(
                label = section.label(),
                selected = section == selected,
                onClick = { onSelect(section) },
            )
        }
    }
}

private fun LibrarySection.label(): String = when (this) {
    LibrarySection.TRACKS -> "Songs"
    LibrarySection.ALBUMS -> "Albums"
    LibrarySection.ARTISTS -> "Artists"
    LibrarySection.PLAYLISTS -> "Playlists"
}

private fun TrackSort.label(): String = when (this) {
    TrackSort.TITLE -> "Title"
    TrackSort.ARTIST -> "Artist"
    TrackSort.ALBUM -> "Album"
    TrackSort.RECENT -> "Recently added"
    TrackSort.DURATION -> "Duration"
}

@Composable
private fun Header(
    subtitle: String,
    username: String?,
    isSyncing: Boolean,
    onSync: () -> Unit,
    onBack: () -> Unit,
    onOpenSettings: () -> Unit,
) {
    ChromeTopBar(
        title = "Library",
        kicker = subtitle,
        onBack = onBack,
        onSettings = onOpenSettings,
        username = username,
        action = {
            FilledIconButton(
                onClick = onSync,
                enabled = !isSyncing,
                modifier = Modifier.size(44.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = AccentTeal,
                    contentColor = LocalChrome.current.accentInk,
                    disabledContainerColor = AccentTeal.copy(alpha = 0.6f),
                    disabledContentColor = LocalChrome.current.accentInk,
                ),
            ) {
                if (isSyncing) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        color = LocalChrome.current.accentInk,
                        strokeWidth = 2.dp,
                    )
                } else {
                    Icon(Icons.Filled.Refresh, contentDescription = "Sync library")
                }
            }
        },
    )
}

@Composable
private fun FilterChipPill(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                label,
                color = InkText.copy(alpha = if (selected) 1f else 0.7f),
                style = MaterialTheme.typography.labelLarge,
            )
        },
        shape = RoundedCornerShape(50),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = PillBase,
            selectedContainerColor = AccentTeal.copy(alpha = 0.22f),
            disabledContainerColor = PillBase,
            labelColor = InkText,
            selectedLabelColor = InkText,
            disabledLabelColor = InkText.copy(alpha = 0.4f),
        ),
        border = null,
    )
}

@Composable
private fun TrackPill(
    item: TrackUi,
    motion: SpringSpecs,
    colorFor: suspend (Uri?) -> Color,
    onPlay: () -> Unit,
    onFavorite: () -> Unit,
    onDownload: () -> Unit,
    onGoToArtist: () -> Unit,
    onGoToAlbum: () -> Unit,
) {
    var albumColor by remember(item.track.id) { mutableStateOf(DefaultArtColor) }
    var menuOpen by remember { mutableStateOf(false) }

    LaunchedEffect(item.artworkUri) {
        albumColor = runCatching { colorFor(item.artworkUri) }
            .getOrDefault(DefaultArtColor)
    }

    val pillColor by animateColorAsState(
        targetValue = albumColor.copy(alpha = 0.22f).compositeOver(PillBase),
        animationSpec = motion.slow(),
        label = "pill",
    )
    val borderColor by animateColorAsState(
        targetValue = albumColor.copy(alpha = 0.3f),
        animationSpec = motion.slow(),
        label = "pillBorder",
    )

    val shape = RoundedCornerShape(30.dp)
    val track = item.track
    // Bitrate only rides along when the row is wide enough for it; codec + duration
    // come first so a narrow phone still shows what matters.
    val wideRow = LocalConfiguration.current.screenWidthDp >= 400
    val meta = listOfNotNull(
        clockTime(track.durationMs),
        track.format?.uppercase()?.takeIf { it.isNotBlank() },
        track.bitrateKbps?.takeIf { it > 0 && wideRow }?.let { "$it kbps" },
    ).joinToString(" · ")
    val quality = track.audioQuality()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(pillColor)
            .border(1.dp, borderColor, shape)
            .clickable(onClick = onPlay)
            .padding(start = 10.dp, end = 6.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AlbumThumb(
            artworkUri = item.artworkUri,
            albumColor = albumColor,
            title = track.title,
            size = 46.dp,
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp),
        ) {
            Text(
                text = track.title,
                color = InkText,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "${track.artist} · ${track.album}",
                color = InkText.copy(alpha = 0.55f),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(Modifier.height(5.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = PlayerIcons.DubTonic,
                    contentDescription = null,
                    tint = InkText.copy(alpha = 0.7f),
                    modifier = Modifier.size(14.dp),
                )
                Spacer(Modifier.width(5.dp))
                Text(
                    text = meta,
                    color = InkText.copy(alpha = 0.6f),
                    fontSize = 10.sp,
                    letterSpacing = 0.2.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
                if (quality != AudioQuality.UNKNOWN) {
                    Spacer(Modifier.width(4.dp))
                    QualityBadge(quality = quality)
                }
                Spacer(Modifier.width(2.dp))
                MetaAction(
                    icon = if (track.isDownloaded) PlayerIcons.Check else PlayerIcons.Download,
                    contentDescription = if (track.isDownloaded) {
                        "Remove download"
                    } else {
                        "Download"
                    },
                    tint = if (track.isDownloaded) AccentTeal else InkText.copy(alpha = 0.5f),
                    onClick = onDownload,
                )
            }
        }

        Box {
            IconButton(onClick = { menuOpen = true }, modifier = Modifier.size(34.dp)) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More options for ${track.title}",
                    tint = InkText.copy(alpha = 0.55f),
                    modifier = Modifier.size(20.dp),
                )
            }
            // Glassmorphic dropdown menu with proper backdrop scrim
            DropdownMenu(
                expanded = menuOpen,
                onDismissRequest = { menuOpen = false },
                containerColor = PillBase,
                modifier = Modifier
                    .background(
                        GlassSurface(
                            shape = RoundedCornerShape(20.dp),
                            alpha = 0.22f,
                            borderAlpha = 0.35f,
                        )
                    ),
            ) {
                TrackMenuItem(
                    label = if (track.isFavorite) "Unfavorite" else "Favorite",
                    icon = if (track.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                ) {
                    menuOpen = false
                    onFavorite()
                }
                TrackMenuItem(
                    label = "Go to ${track.artist}",
                    icon = PlayerIcons.Artist,
                ) {
                    menuOpen = false
                    onGoToArtist()
                }
                TrackMenuItem(
                    label = "Go to ${track.album}",
                    icon = PlayerIcons.Album,
                ) {
                    menuOpen = false
                    onGoToAlbum()
                }
                TrackMenuItem(
                    label = if (track.isDownloaded) "Remove download" else "Download",
                    icon = if (track.isDownloaded) PlayerIcons.Check else PlayerIcons.Download,
                ) {
                    menuOpen = false
                    onDownload()
                }
            }
        }
    }
}

@Composable
private fun MetaAction(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    tint: Color,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick, modifier = Modifier.size(28.dp)) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(16.dp),
        )
    }
}

@Composable
private fun TrackMenuItem(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
) {
    DropdownMenuItem(
        text = { Text(text = label, color = InkText) },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = InkText.copy(alpha = 0.7f),
                modifier = Modifier.size(18.dp),
            )
        },
        onClick = onClick,
    )
}

private fun clockTime(durationMs: Long): String? {
    if (durationMs <= 0L) return null
    val totalSeconds = durationMs / 1000L
    val minutes = totalSeconds / 60L
    val seconds = totalSeconds % 60L
    return "%d:%02d".format(minutes, seconds)
}

@Composable
private fun AlbumPill(
    item: AlbumUi,
    motion: SpringSpecs,
    colorFor: suspend (Uri?) -> Color,
    onPlay: () -> Unit,
) {
    var albumColor by remember(item.album.id) { mutableStateOf(DefaultArtColor) }

    LaunchedEffect(item.artworkUri) {
        albumColor = runCatching { colorFor(item.artworkUri) }
            .getOrDefault(DefaultArtColor)
    }

    val pillColor by animateColorAsState(
        targetValue = albumColor.copy(alpha = 0.22f).compositeOver(PillBase),
        animationSpec = motion.slow(),
        label = "albumPill",
    )
    val borderColor by animateColorAsState(
        targetValue = albumColor.copy(alpha = 0.3f),
        animationSpec = motion.slow(),
        label = "albumPillBorder",
    )

    val shape = RoundedCornerShape(30.dp)
    val meta = listOfNotNull(
        item.album.artist.takeIf { it.isNotBlank() },
        item.album.year?.toString(),
        "${item.album.songCount} songs",
    ).joinToString(" · ")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(pillColor)
            .border(1.dp, borderColor, shape)
            .clickable(onClick = onPlay)
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AlbumThumb(
            artworkUri = item.artworkUri,
            albumColor = albumColor,
            title = item.album.name,
            size = 56.dp,
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = item.album.name,
                color = InkText,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = meta,
                color = InkText.copy(alpha = 0.55f),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        PlayAffordance(onPlay = onPlay)
    }
}

@Composable
private fun ArtistPill(
    item: ArtistUi,
    motion: SpringSpecs,
    colorFor: suspend (Uri?) -> Color,
    onPlay: () -> Unit,
    isSyncing: Boolean = false,
) {
    var artistColor by remember(item.artist.id) { mutableStateOf(DefaultArtColor) }

    LaunchedEffect(item.artworkUri) {
        artistColor = runCatching { colorFor(item.artworkUri) }
            .getOrDefault(DefaultArtColor)
    }

    val pillColor by animateColorAsState(
        targetValue = artistColor.copy(alpha = 0.22f).compositeOver(PillBase),
        animationSpec = motion.slow(),
        label = "artistPill",
    )
    val borderColor by animateColorAsState(
        targetValue = artistColor.copy(alpha = 0.3f),
        animationSpec = motion.slow(),
        label = "artistPillBorder",
    )

    val shape = RoundedCornerShape(30.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(pillColor)
            .border(1.dp, borderColor, shape)
            .clickable(onClick = onPlay)
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ArtistAvatar(
            artworkUri = item.artworkUri,
            artistColor = artistColor,
            name = item.artist.name,
            size = 56.dp,
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = item.artist.name,
                color = InkText,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (item.artist.albumCount > 0) {
                Text(
                    text = "${item.artist.albumCount} albums",
                    color = InkText.copy(alpha = 0.55f),
                    style = MaterialTheme.typography.bodySmall,
                )
            } else if (isSyncing) {
                Text(
                    text = "Pending",
                    color = InkText.copy(alpha = 0.55f),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }

        PlayAffordance(onPlay = onPlay)
    }
}

@Composable
private fun PlaylistPill(
    item: PlaylistUi,
    motion: SpringSpecs,
    onPlay: () -> Unit,
) {
    var playlistColor by remember(item.playlist.id) { mutableStateOf(DefaultArtColor) }

    val pillColor by animateColorAsState(
        targetValue = playlistColor.copy(alpha = 0.22f).compositeOver(PillBase),
        animationSpec = motion.slow(),
        label = "playlistPill",
    )
    val borderColor by animateColorAsState(
        targetValue = playlistColor.copy(alpha = 0.3f),
        animationSpec = motion.slow(),
        label = "playlistPillBorder",
    )

    val shape = RoundedCornerShape(30.dp)
    val meta = listOfNotNull(
        item.playlist.owner?.takeIf { it.isNotBlank() },
        "${item.playlist.songCount} songs",
    ).joinToString(" · ")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(pillColor)
            .border(1.dp, borderColor, shape)
            .clickable(onClick = onPlay)
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(playlistColor.copy(alpha = 0.45f)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = PlayerIcons.DubTonic,
                contentDescription = null,
                tint = InkText.copy(alpha = 0.8f),
                modifier = Modifier.size(26.dp),
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = item.playlist.name,
                color = InkText,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = meta,
                color = InkText.copy(alpha = 0.55f),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        PlayAffordance(onPlay = onPlay)
    }
}

@Composable
private fun PlayAffordance(onPlay: () -> Unit) {
    FilledIconButton(
        onClick = onPlay,
        modifier = Modifier.size(36.dp),
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = InkText.copy(alpha = 0.14f),
            contentColor = InkText,
        ),
    ) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "Play",
            modifier = Modifier.size(20.dp),
        )
    }
}

@Composable
private fun AlbumThumb(
    artworkUri: Uri?,
    albumColor: Color,
    title: String,
    size: Dp = 48.dp,
) {
    val shape = RoundedCornerShape(if (size >= 56.dp) 16.dp else 14.dp)
    if (artworkUri != null) {
        AsyncImage(
            model = artworkUri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(size)
                .clip(shape),
        )
    } else {
        Box(
            modifier = Modifier
                .size(size)
                .clip(shape)
                .background(albumColor.copy(alpha = 0.45f)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = title.take(1).uppercase(),
                color = InkText,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
private fun ArtistAvatar(
    artworkUri: Uri?,
    artistColor: Color,
    name: String,
    size: Dp,
) {
    val shape = RoundedCornerShape(50)
    if (artworkUri != null) {
        AsyncImage(
            model = artworkUri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(size)
                .clip(shape),
        )
    } else {
        Box(
            modifier = Modifier
                .size(size)
                .clip(shape)
                .background(artistColor.copy(alpha = 0.45f)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = name.take(1).uppercase(),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

@Composable
private fun EmptyState(
    query: String,
    filter: LibraryFilter,
    section: LibrarySection,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = when {
                query.isNotBlank() -> "No matches for \"$query\"."
                section == LibrarySection.TRACKS && filter == LibraryFilter.FAVORITES ->
                    "No favorites yet. Tap the heart on a song to keep it here."
                section == LibrarySection.TRACKS && filter == LibraryFilter.DOWNLOADED ->
                    "No offline tracks yet. Download a song to listen without a connection."
                section == LibrarySection.ALBUMS -> "No albums yet. Connect a Subsonic server, then sync."
                section == LibrarySection.ARTISTS -> "No artists yet. Connect a Subsonic server, then sync."
                section == LibrarySection.PLAYLISTS -> "No playlists yet."
                else -> "No library yet. Connect a Subsonic server, then sync."
            },
            style = MaterialTheme.typography.bodyMedium,
            color = InkText.copy(alpha = 0.55f),
        )
    }
}
