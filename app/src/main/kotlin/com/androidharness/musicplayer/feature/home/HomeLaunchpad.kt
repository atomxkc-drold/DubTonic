package com.androidharness.musicplayer.feature.home

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.ui.chrome.SubsonicBadge

/** One square tile in the launchpad grid. */
data class LaunchTile(
    val id: String,
    val label: String,
    val artwork: Uri?,
    val accent: Color,
    val icon: ImageVector? = null,
    val onOpen: () -> Unit,
)

object Moods {
    const val ALL = "All"
    val chips = listOf("All", "Relax", "Sleep", "Romance", "Sad", "Focus", "Party")
}

private val moodKeywords = mapOf(
    "Relax" to listOf("chill", "ambient", "downtempo", "lounge", "acoustic", "lo-fi", "lofi"),
    "Sleep" to listOf("ambient", "drone", "classical", "piano", "sleep", "new age"),
    "Romance" to listOf("r&b", "rnb", "soul", "love", "ballad", "jazz", "slow"),
    "Sad" to listOf("sad", "emo", "blues", "acoustic", "slowcore", "melancholy"),
    "Focus" to listOf("instrumental", "classical", "electronic", "techno", "idm"),
    "Party" to listOf("dance", "house", "edm", "pop", "hip hop", "rap", "club"),
)

/**
 * Library narrowed to a mood, using genre tags. Falls back to everything when the filter
 * would leave too little behind, so a chip can never empty the page.
 */
fun tracksForMood(tracks: List<Track>, mood: String): List<Track> {
    if (mood == Moods.ALL) return tracks
    val keywords = moodKeywords[mood] ?: return tracks
    val matches = tracks.filter { track ->
        val genre = track.genre?.lowercase() ?: return@filter false
        keywords.any { keyword -> genre.contains(keyword) }
    }
    return if (matches.size >= 12) matches else tracks
}

/**
 * The first thing on Home: a mood chip row with the Subsonic mark at the top-left, and a
 * three-across grid of mixes. Paged twelve at a time, and collapsible to one row.
 */
@Composable
fun HomeLaunchpad(
    tiles: List<LaunchTile>,
    selectedMood: String,
    onMoodSelected: (String) -> Unit,
    onOpenSettings: () -> Unit,
    accent: Color,
    ink: Color,
    muted: Color,
    modifier: Modifier = Modifier,
) {
    var page by rememberSaveable { mutableIntStateOf(0) }
    var collapsed by rememberSaveable { mutableStateOf(false) }

    val perPage = 12
    val pageCount = ((tiles.size + perPage - 1) / perPage).coerceAtLeast(1)
    val safePage = page.coerceIn(0, pageCount - 1)
    val visible = if (collapsed) tiles.take(3) else tiles.drop(safePage * perPage).take(perPage)

    Column(modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(13.dp))
                    .background(ink.copy(alpha = 0.07f))
                    .border(1.dp, ink.copy(alpha = 0.14f), RoundedCornerShape(13.dp))
                    .clickable(onClick = onOpenSettings),
                contentAlignment = Alignment.Center,
            ) {
                SubsonicBadge(size = 30.dp, scrim = null)
            }
            Spacer(Modifier.width(10.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(Moods.chips) { mood ->
                    MoodChip(
                        label = mood,
                        selected = mood == selectedMood,
                        accent = accent,
                        ink = ink,
                        muted = muted,
                        onClick = { onMoodSelected(mood) },
                    )
                }
            }
        }

        Spacer(Modifier.height(14.dp))

        visible.chunked(3).forEach { row ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                row.forEach { tile ->
                    LaunchTileCard(tile = tile, modifier = Modifier.weight(1f))
                }
                repeat(3 - row.size) { Spacer(Modifier.weight(1f)) }
            }
            Spacer(Modifier.height(10.dp))
        }

        Row(
            Modifier.fillMaxWidth().padding(top = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(Modifier.weight(1f))
            if (!collapsed) {
                repeat(pageCount) { index ->
                    Box(
                        Modifier
                            .padding(horizontal = 3.dp)
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(
                                if (index == safePage) accent else ink.copy(alpha = 0.22f)
                            )
                            .clickable { page = index }
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            Box(
                Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .clickable { collapsed = !collapsed },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (collapsed) "Show all mixes" else "Collapse mixes",
                    tint = muted,
                    modifier = Modifier.rotate(if (collapsed) 180f else 0f).size(20.dp),
                )
            }
        }
    }
}

@Composable
private fun MoodChip(
    label: String,
    selected: Boolean,
    accent: Color,
    ink: Color,
    muted: Color,
    onClick: () -> Unit,
) {
    Text(
        text = label,
        color = if (selected) Color(0xFF0B0F0E) else ink.copy(alpha = 0.86f),
        fontSize = 13.sp,
        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(if (selected) accent else ink.copy(alpha = 0.07f))
            .border(
                1.dp,
                if (selected) Color.Transparent else ink.copy(alpha = 0.14f),
                RoundedCornerShape(50),
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 7.dp),
    )
}

@Composable
private fun LaunchTileCard(tile: LaunchTile, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(16.dp)
    Box(
        modifier
            .aspectRatio(1f)
            .clip(shape)
            .background(tile.accent.copy(alpha = 0.22f))
            .border(1.dp, Color.White.copy(alpha = 0.10f), shape)
            .clickable(onClick = tile.onOpen),
    ) {
        if (tile.artwork != null) {
            AsyncImage(
                model = tile.artwork,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            Box(
                Modifier.fillMaxSize().background(
                    Brush.linearGradient(
                        listOf(tile.accent.copy(alpha = 0.85f), tile.accent.copy(alpha = 0.35f))
                    )
                ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = tile.icon ?: Icons.Filled.ThumbUp,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.92f),
                    modifier = Modifier.size(40.dp),
                )
            }
        }

        Box(
            Modifier.fillMaxSize().background(
                Brush.verticalGradient(
                    0.45f to Color.Transparent,
                    1f to Color.Black.copy(alpha = 0.82f),
                )
            )
        )

        Text(
            text = tile.label,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 15.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 9.dp, vertical = 8.dp),
        )
    }
}
