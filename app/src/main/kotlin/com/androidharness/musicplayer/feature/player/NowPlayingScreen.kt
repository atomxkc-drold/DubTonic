package com.androidharness.musicplayer.feature.player

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.Player
import coil3.compose.AsyncImage
import com.androidharness.musicplayer.ui.chrome.SubsonicBadge
import com.androidharness.musicplayer.ui.icons.PlayerIcons
import kotlin.math.roundToInt

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
    val colors by viewModel.colors.collectAsStateWithLifecycle()
    val details by viewModel.details.collectAsStateWithLifecycle()
    val dark = isSystemInDarkTheme()
    val base = if (dark) Color(0xFF181C1D) else Color(0xFFF2F1EE)
    val tint = lerp(base, colors.primary, if (dark) 0.15f else 0.13f)
    val ink = if (dark) Color(0xFFF5F4EF) else Color(0xFF151719)
    val muted = if (dark) Color(0xFFAEB5B5) else Color(0xFF6F7579)
    val trackDetails = details?.takeIf { it.id == playback.trackId }

    Box(
        Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(tint, base, base)))
    ) {
        Column(
            modifier = Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding()
                .verticalScroll(rememberScrollState()).padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = ink)
                }
                Spacer(Modifier.weight(1f))
                Text("NOW PLAYING", color = muted, style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace)
                Spacer(Modifier.weight(1f))
                TextButton(onClick = onOpenSettings, colors = ButtonDefaults.textButtonColors(contentColor = ink)) {
                    Text("EQ", fontFamily = FontFamily.Monospace)
                }
            }
            Spacer(Modifier.height(16.dp))
            with(sharedTransitionScope) {
                Box(
                    modifier = Modifier.fillMaxWidth().widthIn(max = 480.dp).aspectRatio(1f)
                        .clip(RoundedCornerShape(6.dp)).background(ink.copy(alpha = 0.08f))
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = SHARED_ART_KEY),
                            animatedVisibilityScope = animatedVisibilityScope,
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    if (playback.artworkUri == null) {
                        Text("♪", color = muted, style = MaterialTheme.typography.displayLarge)
                    } else {
                        AsyncImage(model = playback.artworkUri, contentDescription = "Album artwork",
                            contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                    }
                    SubsonicBadge(
                        size = 30.dp,
                        scrim = Color.Black.copy(alpha = 0.38f),
                        modifier = Modifier.align(Alignment.BottomStart).padding(10.dp),
                    )
                }
            }
            Spacer(Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(playback.title.ifBlank { "Nothing playing" }, color = ink,
                        style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold,
                        maxLines = 2, overflow = TextOverflow.Ellipsis)
                    Spacer(Modifier.height(4.dp))
                    Text(playback.artist, color = muted, style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                IconButton(onClick = viewModel::toggleFavorite, enabled = trackDetails != null) {
                    Icon(if (trackDetails?.isFavorite == true) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = if (trackDetails?.isFavorite == true) "Remove favorite" else "Add favorite",
                        tint = if (trackDetails?.isFavorite == true) colors.primary else muted)
                }
            }
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                for (star in 1..5) {
                    val selected = star <= (trackDetails?.rating ?: 0)
                    TextButton(
                        onClick = { viewModel.setRating(if (trackDetails?.rating == star) 0 else star) },
                        enabled = trackDetails != null,
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp),
                        modifier = Modifier.size(44.dp).semantics { contentDescription = "Rate $star out of 5" },
                    ) {
                        Text(if (selected) "★" else "☆", color = if (selected) ink else muted,
                            style = MaterialTheme.typography.headlineSmall)
                    }
                }
            }
            val source = listOfNotNull(
                trackDetails?.format?.takeIf { it.isNotBlank() }?.uppercase(),
                trackDetails?.bitrateKbps?.let { "$it kbps" },
            ).joinToString(" · ")
            if (source.isNotBlank()) {
                Spacer(Modifier.height(6.dp))
                Box(Modifier.fillMaxWidth().height(1.dp).background(muted.copy(alpha = 0.5f)))
                Row(Modifier.fillMaxWidth().padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(source, color = ink, style = MaterialTheme.typography.labelMedium,
                        fontFamily = FontFamily.Monospace)
                    Text("SOURCE", color = muted, style = MaterialTheme.typography.labelSmall,
                        fontFamily = FontFamily.Monospace)
                }
                Box(Modifier.fillMaxWidth().height(1.dp).background(muted.copy(alpha = 0.5f)))
            }
            Spacer(Modifier.height(8.dp))
            var seeking by remember(playback.trackId) { mutableFloatStateOf(-1f) }
            Slider(
                value = if (seeking >= 0f) seeking else playback.progress,
                onValueChange = { seeking = it },
                onValueChangeFinished = { if (seeking >= 0f) viewModel.seekToFraction(seeking); seeking = -1f },
                enabled = playback.durationMs > 0,
                modifier = Modifier.fillMaxWidth().semantics { contentDescription = "Playback position" },
                colors = SliderDefaults.colors(thumbColor = ink, activeTrackColor = ink,
                    inactiveTrackColor = muted.copy(alpha = 0.4f)),
            )
            val shownPosition = if (seeking >= 0f) (playback.durationMs * seeking).toLong() else playback.positionMs
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(formatTime(shownPosition), color = muted, style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace)
                Text("-" + formatTime((playback.durationMs - shownPosition).coerceAtLeast(0)),
                    color = muted, style = MaterialTheme.typography.labelSmall, fontFamily = FontFamily.Monospace)
            }
            Spacer(Modifier.height(10.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = viewModel::toggleShuffle) {
                    Icon(PlayerIcons.Shuffle, contentDescription = if (playback.shuffleEnabled) "Turn shuffle off" else "Turn shuffle on",
                        tint = if (playback.shuffleEnabled) ink else muted)
                }
                IconButton(onClick = viewModel::previous) {
                    Icon(PlayerIcons.SkipPrevious, contentDescription = "Previous", tint = ink, modifier = Modifier.size(30.dp))
                }
                FilledIconButton(onClick = viewModel::playPause, modifier = Modifier.size(66.dp),
                    shape = CircleShape, colors = IconButtonDefaults.filledIconButtonColors(containerColor = ink, contentColor = base)) {
                    Icon(if (playback.isPlaying) PlayerIcons.Pause else Icons.Filled.PlayArrow,
                        contentDescription = if (playback.isPlaying) "Pause" else "Play", modifier = Modifier.size(32.dp))
                }
                IconButton(onClick = viewModel::next) {
                    Icon(PlayerIcons.SkipNext, contentDescription = "Next", tint = ink, modifier = Modifier.size(30.dp))
                }
                IconButton(onClick = viewModel::cycleRepeat) {
                    Icon(PlayerIcons.Repeat, contentDescription = when (playback.repeatMode) {
                        Player.REPEAT_MODE_ONE -> "Repeat one; change repeat mode"
                        Player.REPEAT_MODE_ALL -> "Repeat all; change repeat mode"
                        else -> "Repeat off; change repeat mode"
                    }, tint = if (playback.repeatMode == Player.REPEAT_MODE_OFF) muted else ink)
                }
            }
            Spacer(Modifier.height(12.dp))
            SystemVolumeControl(ink, muted)
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun SystemVolumeControl(ink: Color, muted: Color) {
    val context = LocalContext.current
    val audio = remember(context) { context.getSystemService(Context.AUDIO_SERVICE) as AudioManager }
    val maxVolume = remember(audio) { audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC).coerceAtLeast(1) }
    var volume by remember(audio) { mutableFloatStateOf(audio.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat()) }
    DisposableEffect(context, audio) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                volume = audio.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat()
            }
        }
        ContextCompat.registerReceiver(context, receiver, IntentFilter("android.media.VOLUME_CHANGED_ACTION"),
            ContextCompat.RECEIVER_EXPORTED)
        onDispose { context.unregisterReceiver(receiver) }
    }
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text("VOL", color = ink, style = MaterialTheme.typography.labelSmall, fontFamily = FontFamily.Monospace)
        Slider(value = volume.coerceIn(0f, maxVolume.toFloat()), onValueChange = {
            val level = it.roundToInt().coerceIn(0, maxVolume)
            volume = level.toFloat()
            audio.setStreamVolume(AudioManager.STREAM_MUSIC, level, 0)
        }, valueRange = 0f..maxVolume.toFloat(),
            modifier = Modifier.weight(1f).padding(horizontal = 12.dp).semantics { contentDescription = "Media volume" },
            colors = SliderDefaults.colors(thumbColor = ink, activeTrackColor = ink,
                inactiveTrackColor = muted.copy(alpha = 0.4f)))
        Text("${(volume / maxVolume * 100).roundToInt()}%", color = muted,
            style = MaterialTheme.typography.labelSmall, fontFamily = FontFamily.Monospace)
    }
}
