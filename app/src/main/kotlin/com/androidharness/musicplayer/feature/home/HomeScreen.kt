package com.androidharness.musicplayer.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidharness.musicplayer.feature.player.MiniPlayer
import com.androidharness.musicplayer.feature.player.PlayerViewModel
import com.androidharness.musicplayer.ui.chrome.BrandMark
import com.androidharness.musicplayer.ui.chrome.SectionLabel
import com.androidharness.musicplayer.ui.chrome.SubsonicBrandHeader
import com.androidharness.musicplayer.ui.icons.PlayerIcons
import com.androidharness.musicplayer.ui.theme.SpringSpecs

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onOpenLibrary: () -> Unit,
    onOpenPlayer: () -> Unit,
    onOpenSettings: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    playerViewModel: PlayerViewModel = hiltViewModel(),
) {
    val tracks by viewModel.tracks.collectAsStateWithLifecycle()
    val playback by viewModel.playback.collectAsStateWithLifecycle()
    val colors by viewModel.colors.collectAsStateWithLifecycle()
    val ui by viewModel.ui.collectAsStateWithLifecycle()
    val clock by viewModel.clock.collectAsStateWithLifecycle()
    val session by viewModel.session.collectAsStateWithLifecycle()

    val dark = isSystemInDarkTheme()
    val mood = remember(clock.hour) { dayMood(clock.hour) }
    // While a track plays the kicker reports on its artist instead of the clock; a new
    // song usually lands on a different fact, so the top line keeps moving.
    val artistLine = remember(playback.artist, playback.trackId, tracks) {
        artistKicker(playback.artist, playback.trackId, tracks)
    }
    val aurora = remember(colors, mood, ui.reducedMotion) {
        auroraFor(colors.primary, colors.secondary, mood, ui.reducedMotion)
    }
    val ink = if (dark) Color(0xFFF4F2EB) else Color(0xFF101613)
    val muted = if (dark) Color(0xFFB5BBB4) else Color(0xFF59625C)
    val base = if (dark) Color(0xFF0B0F0E) else Color(0xFFF3F2EE)
    val accent = if (dark) aurora.glow else lerp(aurora.glow, Color(0xFF0E2A24), 0.55f)
    val motion = remember(ui) { SpringSpecs(ui) }
    val animate = !ui.reducedMotion
    val scroll = rememberScrollState()

    val artwork = remember(tracks) { selectHomeArtwork(tracks).firstOrNull() }
    val buckets = remember(tracks) { eraBuckets(tracks, includeUndated = true) }
    val stacks = remember(tracks) { buildVibeStacks(tracks, limit = 6, minSize = 3) }
    val route = remember(tracks) { buildSonicFlow(tracks, maxTracks = 14) }
    var eraStart by rememberSaveable(tracks) { mutableStateOf<Int?>(null) }
    val selectedEra = buckets.firstOrNull { it.startYear == eraStart } ?: buckets.maxByOrNull { it.count }

    val accentFor: (Int) -> Color = remember(aurora, dark) {
        { index ->
            val ramp = listOf(aurora.blobs[0], aurora.blobs[1], aurora.glow, aurora.blobs[2])
            val tint = ramp[index % ramp.size]
            if (dark) tint else lerp(tint, Color(0xFF0E2A24), 0.35f)
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(base)) {
        HomeBackdrop(
            spec = aurora,
            artwork = playback.artworkUri ?: viewModel.artUri(artwork?.coverArtId),
            dark = dark,
            blurRadiusDp = ui.blurRadiusDp,
            parallaxPx = scroll.value.toFloat(),
        )

        Scaffold(
            containerColor = Color.Transparent,
            // The column below already handles the status bar; letting Scaffold add its own
            // top inset too left a status-bar-sized hole above the brand header.
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
                    .verticalScroll(scroll)
                    .padding(horizontal = 20.dp),
            ) {
                Spacer(Modifier.height(6.dp))

                SubsonicBrandHeader(
                    username = session?.username,
                    onSettings = onOpenSettings,
                )

                Spacer(Modifier.height(24.dp))

                val kickerLine = artistLine?.line ?: moodKicker(mood, clock.hour, clock.minute)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    PulseDot(active = animate, color = accent)
                    Spacer(Modifier.size(8.dp))
                    Box(Modifier.weight(1f)) {
                        if (animate) {
                            Crossfade(
                                targetState = kickerLine,
                                animationSpec = tween(durationMillis = 320),
                                label = "kicker",
                            ) { line -> KickerText(line, muted) }
                        } else {
                            KickerText(kickerLine, muted)
                        }
                    }
                    if (artistLine == null) {
                        Spacer(Modifier.size(8.dp))
                        Text(
                            text = "· ${tracks.size} TRACKS",
                            color = muted.copy(alpha = 0.8f),
                            style = MaterialTheme.typography.labelSmall,
                            fontFamily = FontFamily.Monospace,
                        )
                    }
                }

                Spacer(Modifier.height(10.dp))

                Text(
                    text = mood.headline,
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.Bold,
                        brush = Brush.linearGradient(
                            listOf(ink, lerp(ink, accent, 0.72f), ink.copy(alpha = 0.85f)),
                        ),
                    ),
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = if (tracks.isEmpty()) {
                        "Connect your server and sync the library. Then this wall starts breathing."
                    } else {
                        mood.blurb
                    },
                    color = muted,
                    style = MaterialTheme.typography.bodyLarge,
                )

                Spacer(Modifier.height(18.dp))

                if (playback.hasQueue) {
                    NowPlayingStrip(
                        playback = playback,
                        accent = accent,
                        ink = ink,
                        muted = muted,
                        animate = animate,
                        onClick = onOpenPlayer,
                    )
                    Spacer(Modifier.height(16.dp))
                }

                CollectionCard(
                    trackCount = tracks.size,
                    mixCount = stacks.size,
                    accent = accent,
                    ink = ink,
                    muted = muted,
                    onOpenLibrary = onOpenLibrary,
                )

                AlbumFan(
                    tracks = selectHomeArtwork(tracks),
                    artworkFor = { viewModel.artworkFor(it) },
                    ink = ink,
                    accent = accent,
                    animate = animate,
                )

                Spacer(Modifier.height(22.dp))

                var sonicFlowOpen by rememberSaveable { mutableStateOf(true) }
                CollapsibleSection(
                    title = "SONIC FLOW",
                    hint = "${tracks.size} TRACKS",
                    expanded = sonicFlowOpen,
                    onToggle = { sonicFlowOpen = !sonicFlowOpen },
                    accent = accent,
                    ink = ink,
                    muted = muted,
                ) {
                    SonicFlowCard(
                        route = route,
                        trackCount = tracks.size,
                        totalDurationMs = tracks.sumOf { it.durationMs },
                        accent = accent,
                        glow = aurora.blobs[1],
                        ink = ink,
                        muted = muted,
                        animate = animate,
                        onClick = { viewModel.playFlow(playback.trackId) },
                    )
                }

                Spacer(Modifier.height(12.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(
                        onClick = viewModel::shuffleAll,
                        enabled = tracks.isNotEmpty() && playback.isConnected,
                        modifier = Modifier.weight(1f).height(54.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ink,
                            contentColor = base,
                        ),
                    ) {
                        Icon(
                            PlayerIcons.Shuffle,
                            contentDescription = null,
                            modifier = Modifier.size(19.dp),
                        )
                        Spacer(Modifier.size(9.dp))
                        Text("Shuffle all", fontWeight = FontWeight.SemiBold)
                    }
                    OutlinedButton(
                        onClick = onOpenLibrary,
                        modifier = Modifier.height(54.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = ink),
                    ) {
                        Text("Library")
                    }
                }

                if (tracks.isEmpty()) {
                    Spacer(Modifier.height(12.dp))
                    EmptyHint(ink = ink, muted = muted, onOpenSettings = onOpenSettings)
                }

                if (buckets.isNotEmpty()) {
                    Spacer(Modifier.height(30.dp))

                    var timeMachineOpen by rememberSaveable { mutableStateOf(false) }
                    CollapsibleSection(
                        index = "01",
                        title = "TIME MACHINE",
                        hint = "${buckets.size} ERAS",
                        expanded = timeMachineOpen,
                        onToggle = { timeMachineOpen = !timeMachineOpen },
                        accent = accent,
                        ink = ink,
                        muted = muted,
                    ) {
                        EraDial(
                            buckets = buckets,
                            selected = selectedEra,
                            accent = accent,
                            ink = ink,
                            muted = muted,
                            animate = animate,
                            onSelect = { eraStart = it.startYear },
                            onPlay = viewModel::playEra,
                        )
                    }
                }

                if (stacks.isNotEmpty()) {
                    Spacer(Modifier.height(34.dp))
                    SectionLabel(
                        index = "02",
                        title = "RADIO FROM YOUR SHELF",
                        hint = "${stacks.size} MIXES",
                        accent = accent,
                        muted = muted,
                    )
                    Spacer(Modifier.height(14.dp))
                    VibeStackRail(
                        stacks = stacks,
                        artworkFor = viewModel::artUri,
                        accentFor = accentFor,
                        ink = ink,
                        muted = muted,
                        motion = motion,
                        onPlay = viewModel::playStack,
                    )
                }

                Spacer(Modifier.height(30.dp))
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun KickerText(text: String, color: Color, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.labelSmall,
        fontFamily = FontFamily.Monospace,
        letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}

@Composable
private fun EmptyHint(ink: Color, muted: Color, onOpenSettings: () -> Unit) {
    val shape = RoundedCornerShape(22.dp)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(ink.copy(alpha = 0.07f))
            .border(1.dp, ink.copy(alpha = 0.12f), shape)
            .clickable(onClick = onOpenSettings)
            .padding(18.dp),
    ) {
        Text(
            text = "NO LIBRARY YET",
            color = muted,
            style = MaterialTheme.typography.labelSmall,
            fontFamily = FontFamily.Monospace,
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = "Add your Subsonic server in settings to fill the dial, the mixes and the flow.",
            color = ink,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "OPEN SETTINGS",
            color = muted,
            style = MaterialTheme.typography.labelSmall,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun CollectionCard(
    trackCount: Int,
    mixCount: Int,
    accent: Color,
    ink: Color,
    muted: Color,
    onOpenLibrary: () -> Unit,
) {
    val shape = RoundedCornerShape(22.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(ink.copy(alpha = 0.06f))
            .border(1.dp, ink.copy(alpha = 0.12f), shape)
            .clickable(onClick = onOpenLibrary)
            .padding(horizontal = 18.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(accent.copy(alpha = 0.18f)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                PlayerIcons.Queue,
                contentDescription = null,
                tint = accent,
                modifier = Modifier.size(20.dp),
            )
        }
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = "YOUR COLLECTION",
                color = muted,
                style = MaterialTheme.typography.labelSmall,
                fontFamily = FontFamily.Monospace,
            )
            Spacer(Modifier.height(3.dp))
            Text(
                text = "$trackCount songs · $mixCount mixes",
                color = ink,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Text(
            text = "BROWSE →",
            color = ink,
            style = MaterialTheme.typography.labelSmall,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
        )
    }
}

/**
 * Section header that folds its content away. The chevron turns as it opens, and the
 * summary hint stays visible while collapsed so the page still says what is inside.
 */
@Composable
private fun CollapsibleSection(
    title: String,
    hint: String,
    expanded: Boolean,
    onToggle: () -> Unit,
    accent: Color,
    ink: Color,
    muted: Color,
    index: String? = null,
    content: @Composable () -> Unit,
) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .clickable(onClick = onToggle)
                .padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (index != null) {
                Text(
                    text = index,
                    color = accent,
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.width(10.dp))
            }
            Text(
                text = title,
                color = ink,
                style = MaterialTheme.typography.labelSmall,
                fontFamily = FontFamily.Monospace,
                letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = hint,
                color = muted,
                style = MaterialTheme.typography.labelSmall,
                fontFamily = FontFamily.Monospace,
            )
            Spacer(Modifier.width(6.dp))
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = if (expanded) "Collapse $title" else "Expand $title",
                tint = muted,
                modifier = Modifier.rotate(if (expanded) 180f else 0f).size(20.dp),
            )
        }
        AnimatedVisibility(visible = expanded) {
            Column(Modifier.fillMaxWidth()) {
                Spacer(Modifier.height(12.dp))
                content()
            }
        }
    }
}
