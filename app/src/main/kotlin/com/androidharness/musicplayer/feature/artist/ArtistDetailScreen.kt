package com.androidharness.musicplayer.feature.artist

import android.net.Uri
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.lifecycle.viewModelScope
import com.androidharness.musicplayer.core.model.Album
import com.androidharness.musicplayer.core.model.Artist
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.data.remote.SubsonicApi
import com.androidharness.musicplayer.data.remote.SubsonicUrls
import com.androidharness.musicplayer.data.remote.toAlbum
import com.androidharness.musicplayer.data.remote.toArtist
import com.androidharness.musicplayer.data.remote.validated
import com.androidharness.musicplayer.data.remote.SubsonicCredentialsStore
import com.androidharness.musicplayer.domain.repository.MusicRepository
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import com.androidharness.musicplayer.feature.player.PlaybackConnection
import com.androidharness.musicplayer.feature.player.PlaybackUiState
import com.androidharness.musicplayer.feature.player.PlayerViewModel
import com.androidharness.musicplayer.ui.chrome.ChromeBackdrop
import com.androidharness.musicplayer.ui.chrome.ChromeTopBar
import com.androidharness.musicplayer.ui.chrome.LocalChrome
import com.androidharness.musicplayer.ui.chrome.chromeFieldColors
import com.androidharness.musicplayer.ui.icons.PlayerIcons
import com.androidharness.musicplayer.ui.theme.SpringSpecs
import com.androidharness.musicplayer.ui.glass.GlassSurface
import coil3.compose.AsyncImage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.core.net.toUri

@OptIn(ExperimentalSharedTransitionApi::class)
@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val repository: MusicRepository,
    private val credentials: SubsonicCredentialsStore,
    private val api: SubsonicApi,
    private val urls: SubsonicUrls,
    private val connection: PlaybackConnection,
    private val paletteRepository: com.androidharness.musicplayer.ui.image.AlbumPaletteRepository,
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    var artistId: String = ""
    var navController: androidx.navigation.NavController? = null

    private val _artist = MutableStateFlow<Artist?>(null)
    val artist: StateFlow<Artist?> = _artist.asStateFlow()

    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> = _albums.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadArtist(id: String) {
        artistId = id
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val artistDetail = api.getArtist(
                    urls.rest("getArtist"),
                    id
                ).validated().artist ?: run {
                    _error.value = "Artist not found"
                    _isLoading.value = false
                    return@launch
                }

                _artist.value = Artist(
                    id = artistDetail.id,
                    name = artistDetail.name,
                    albumCount = artistDetail.albumCount ?: artistDetail.album.size,
                    coverArtId = artistDetail.coverArt,
                )
                val albumList = artistDetail.album?.distinctBy { it.id } ?: emptyList()
                _albums.value = albumList.map { it.toAlbum() }
                _isLoading.value = false
            } catch (e: Exception) {
                _error.value = e.message
                _isLoading.value = false
            }
        }
    }

    fun playAlbum(album: Album) {
        viewModelScope.launch {
            val tracks = com.androidharness.musicplayer.feature.library.albumOrder(
                repository.observeTracks().first().filter { it.albumId == album.id }
            )
            if (tracks.isNotEmpty()) {
                connection.play(tracks.map { it.toMediaItem(urls) }, 0)
            }
        }
    }

    fun playAll() {
        viewModelScope.launch {
            val tracks = com.androidharness.musicplayer.feature.library.sortTracks(
                repository.observeTracks().first().filter { it.artistId == artistId },
                com.androidharness.musicplayer.feature.library.TrackSort.ARTIST,
            )
            if (tracks.isNotEmpty()) {
                connection.playShuffled(tracks.map { it.toMediaItem(urls) })
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ArtistDetailScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onBack: () -> Unit,
    viewModel: ArtistDetailViewModel = hiltViewModel(),
    playerViewModel: PlayerViewModel = hiltViewModel(),
) {
    val artist by viewModel.artist.collectAsStateWithLifecycle()
    val albums by viewModel.albums.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    val playback by playerViewModel.playback.collectAsStateWithLifecycle()
    val colors by playerViewModel.colors.collectAsStateWithLifecycle()
    val ui by playerViewModel.uiSettings.collectAsStateWithLifecycle()
    val motion = remember(ui) { SpringSpecs(ui) }

    val artistId = remember { viewModel.artistId }
    val navController = remember { viewModel.navController }

    // Load artist on first composition
    androidx.compose.runtime.LaunchedEffect(Unit) {
        viewModel.loadArtist(artistId)
    }

    val ink = LocalChrome.current.ink
    val accent = LocalChrome.current.accent
    val base = LocalChrome.current.base
    val PillBase = ink.copy(alpha = 0.08f)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(base)
    ) {
        ChromeBackdrop(
            artwork = null,
            primary = colors.primary.takeIf { playback.hasQueue },
            secondary = colors.secondary.takeIf { playback.hasQueue },
            blurRadiusDp = ui.blurRadiusDp,
            reducedMotion = ui.reducedMotion,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            ChromeTopBar(
                title = artist?.name ?: "Artist",
                kicker = "Artist",
                onBack = onBack,
                action = {
                    if (artist != null) {
                        IconButton(onClick = { /* TODO: More options */ }, modifier = Modifier.size(44.dp)) {
                            Icon(Icons.Filled.MoreVert, contentDescription = "More options", tint = LocalChrome.current.ink, modifier = Modifier.size(20.dp))
                        }
                    }
                },
            )

            if (isLoading) {
                androidx.compose.foundation.layout.Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    androidx.compose.material3.CircularProgressIndicator(
                        color = accent,
                        modifier = Modifier.size(48.dp)
                    )
                }
            } else if (error != null) {
                androidx.compose.foundation.layout.Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = error!!, color = ink, style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                artist?.let { artist ->
                    ArtistDetailContent(
                        artist = artist,
                        albums = albums,
                        motion = motion,
                        onPlayAll = { viewModel.playAll() },
                        onPlayAlbum = { album -> viewModel.playAlbum(album) },
                        onOpenAlbum = { album ->
                            navController?.navigate("album_detail/$album.id")
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun ArtistDetailContent(
    artist: Artist,
    albums: List<Album>,
    motion: SpringSpecs,
    onPlayAll: () -> Unit,
    onPlayAlbum: (Album) -> Unit,
    onOpenAlbum: (Album) -> Unit,
) {
    val ink = LocalChrome.current.ink
    val accent = LocalChrome.current.accent
    val base = LocalChrome.current.base
    val PillBase = ink.copy(alpha = 0.08f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Hero header with circular artist image
        ArtistHeroHeader(
            artist = artist,
            onPlayAll = onPlayAll,
        )

        // Albums section
        if (albums.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ALBUMS",
                        color = ink.copy(alpha = 0.6f),
                        style = MaterialTheme.typography.labelSmall,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    )
                }

                androidx.compose.foundation.lazy.LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(albums, key = { it.id }) { album ->
                        AlbumPill(
                            album = album,
                            motion = motion,
                            onPlay = { onPlayAlbum(album) },
                            onClick = { onOpenAlbum(album) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ArtistHeroHeader(
    artist: Artist,
    onPlayAll: () -> Unit,
) {
    val ink = LocalChrome.current.ink
    val accent = LocalChrome.current.accent
    val base = LocalChrome.current.base
    val PillBase = ink.copy(alpha = 0.08f)

    val artworkUri = null

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Circular artist image
        Box(
            modifier = Modifier.size(180.dp).clip(androidx.compose.foundation.shape.CircleShape),
            contentAlignment = Alignment.Center
        ) {
            if (artworkUri != null) {
                coil3.compose.AsyncImage(
                    model = artworkUri,
                    contentDescription = null,
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                    modifier = Modifier.size(180.dp).clip(androidx.compose.foundation.shape.CircleShape),
                )
            } else {
                Box(
                    modifier = Modifier.size(180.dp).clip(androidx.compose.foundation.shape.CircleShape).background(PillBase),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = artist.name.take(1).uppercase(),
                        color = ink,
                        style = MaterialTheme.typography.displayLarge,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    )
                }
            }
        }

        // Artist name
        Text(
            text = artist.name,
            color = ink,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            maxLines = 2,
            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
        )

        // Play all button
        androidx.compose.material3.Button(
            onClick = onPlayAll,
            modifier = Modifier.padding(horizontal = 24.dp),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = accent,
                contentColor = Color.Black,
            ),
            shape = RoundedCornerShape(50)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.PlayArrow, contentDescription = null, tint = Color.Black, modifier = Modifier.size(20.dp))
                Text(text = "Play All", color = Color.Black, style = MaterialTheme.typography.labelLarge, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun AlbumPill(
    album: Album,
    motion: SpringSpecs,
    onPlay: () -> Unit,
    onClick: () -> Unit,
) {
    val ink = LocalChrome.current.ink
    val accent = LocalChrome.current.accent
    val base = LocalChrome.current.base
    val PillBase = ink.copy(alpha = 0.08f)

    val artworkUri = null

    var albumColor by remember(album.id) { mutableStateOf(Color(0xFF4A5A70)) }

    androidx.compose.runtime.LaunchedEffect(artworkUri) {
        albumColor = Color(0xFF4A5A70)
    }

    val pillColor by androidx.compose.animation.animateColorAsState(
        targetValue = albumColor.copy(alpha = 0.22f),
        animationSpec = motion.slow(),
        label = "albumPill",
    )
    val borderColor by androidx.compose.animation.animateColorAsState(
        targetValue = albumColor.copy(alpha = 0.3f),
        animationSpec = motion.slow(),
        label = "albumPillBorder",
    )

    val shape = RoundedCornerShape(30.dp)
    val meta = listOfNotNull(
        album.year?.toString(),
        "${album.songCount} songs",
    ).joinToString(" · ")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(pillColor)
            .border(1.dp, borderColor, shape)
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Album thumbnail
        Box(
            modifier = Modifier.size(56.dp).clip(RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (artworkUri != null) {
                coil3.compose.AsyncImage(
                    model = artworkUri,
                    contentDescription = null,
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                    modifier = Modifier.size(56.dp).clip(RoundedCornerShape(16.dp)),
                )
            } else {
                Box(
                    modifier = Modifier.size(56.dp).clip(RoundedCornerShape(16.dp)).background(albumColor.copy(alpha = 0.45f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = album.name.take(1).uppercase(),
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = album.name,
                color = ink,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
            )
            Text(
                text = meta,
                color = ink.copy(alpha = 0.55f),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
            )
        }

        // Play affordance
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(androidx.compose.foundation.shape.CircleShape)
                .background(
                    if (true) {
                        Color.White.copy(alpha = 0.18f)
                    } else {
                        Color.White.copy(alpha = 0.05f)
                    }
                )
                .border(
                    1.dp,
                    if (true) {
                        Color.White.copy(alpha = 0.34f)
                    } else {
                        Color.White.copy(alpha = 0.10f)
                    },
                    androidx.compose.foundation.shape.CircleShape,
                )
                .clickable(onClick = onPlay),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(24.dp),
            )
        }
    }
}

private fun Track.toMediaItem(urls: com.androidharness.musicplayer.data.remote.SubsonicUrls): MediaItem =
    MediaItem.Builder()
        .setMediaId(id)
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setTitle(title)
                .setArtist(artist)
                .setAlbumTitle(album)
                .setArtworkUri(coverArtId?.let { urls.coverArtUrl(it, size = 512).toUri() })
                .build()
        )
        .build()
