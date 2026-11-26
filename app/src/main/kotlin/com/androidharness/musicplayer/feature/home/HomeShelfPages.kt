package com.androidharness.musicplayer.feature.home

/* HomeShelfPages.kt - Pill row with full text display
 * 
 * This file contains the pill row implementation that allows horizontal swiping
 * to reveal full pill titles (like "D" -> "Discover"). The key change is removing
 * maxLines = 1 from the Text component to allow text expansion.
 */

import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.data.settings.HomeShelf
import com.androidharness.musicplayer.data.settings.HomeShelfMode
import com.androidharness.musicplayer.ui.chrome.SubsonicBadge
import kotlinx.coroutines.flow.distinctUntilChanged

/** One row or tile on a Home shelf, whatever the display mode. */
internal data class ShelfItem(
    val id: String,
    val title: String,
    val subtitle: String? = null,
    val art: Uri? = null,
    val badge: String? = null,
    /** Track ids this item plays when the shelf header is pressed. */
    val queue: List<String> = emptyList(),
    val onClick: () -> Unit,
)

/** A track row for any shelf: title, artist under it, and the cover when there is one. */
internal fun Track.toShelfItem(art: Uri?, onClick: () -> Unit): ShelfItem = ShelfItem(
    id = id,
    title = title,
    subtitle = artist.ifBlank { album }.takeIf { it.isNotBlank() },
    art = art,
    queue = listOf(id),
    onClick = onClick,
)

private val ShelfPagerHeight = 396.dp

/**
 * The shelf section that replaced the mood launchpad: a row of pills the user can reorder by
 * long press, a pager that swipes under them, and one of three display modes per shelf.
 */
@Composable
internal fun HomeShelfPages(
    shelves: List<HomeShelf>,
    selected: HomeShelf,
    modes: Map<String, String>,
    accent: Color,
    ink: Color,
    muted: Color,
    content: (HomeShelf) -> List<ShelfItem>,
    onSelect: (HomeShelf) -> Unit,
    onReorder: (List<HomeShelf>) -> Unit,
    onModeChange: (HomeShelf, HomeShelfMode) -> Unit,
    onPlayAll: (HomeShelf, List<ShelfItem>) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (shelves.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { shelves.size })
    val selectedIndex = shelves.indexOf(selected).coerceAtLeast(0)

    // Pills drive the pager, swipes drive the pills: one state, two inputs.
    LaunchedEffect(selectedIndex) {
        if (pagerState.currentPage != selectedIndex) pagerState.scrollToPage(selectedIndex)
    }
    LaunchedEffect(pagerState, shelves) {
        snapshotFlow { pagerState.settledPage }
            .distinctUntilChanged()
            .collect { page -> shelves.getOrNull(page)?.let(onSelect) }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        ShelfPills(
            shelves = shelves,
            selected = selected,
            accent = accent,
            ink = ink,
            muted = muted,
            onSelect = onSelect,
            onReorder = onReorder,
        )

        Spacer(Modifier.height(12.dp))

        val current = shelves[selectedIndex]
        ShelfModeBar(
            mode = modes[current.name].toMode(),
            accent = accent,
            ink = ink,
            muted = muted,
            onModeChange = { onModeChange(current, it) },
        )

        Spacer(Modifier.height(8.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(ShelfPagerHeight),
            verticalAlignment = Alignment.Top,
        ) { page ->
            val shelf = shelves[page]
            val items = content(shelf)
            ShelfPage(
                shelf = shelf,
                items = items,
                mode = modes[shelf.name].toMode(),
                accent = accent,
                ink = ink,
                muted = muted,
                onPlayAll = { onPlayAll(shelf, items) },
            )
        }
    }
}

private fun String?.toMode(): HomeShelfMode =
    HomeShelfMode.entries.firstOrNull { it.name == this } ?: HomeShelfMode.GRID

/**
 * The pill row. A long press picks a pill up; dragging it half its own width swaps it with
 * the neighbour, so any order is reachable. Tap still selects.
 */
@Composable
private fun ShelfPills(
    shelves: List<HomeShelf>,
    selected: HomeShelf,
    accent: Color,
    ink: Color,
    muted: Color,
    onSelect: (HomeShelf) -> Unit,
    onReorder: (List<HomeShelf>) -> Unit,
) {
    var dragging by remember { mutableIntStateOf(-1) }
    var dragOffset by remember { mutableFloatStateOf(0f) }
    val widths = remember { mutableStateListOf<Int>() }
    val density = LocalDensity.current
    val minTouch = with(density) { 8.dp.toPx() }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        shelves.forEachIndexed { index, shelf ->
            val lifted = index == dragging
            val lift by animateFloatAsState(
                targetValue = if (lifted) 1.05f else 1f,
                label = "pillLift",
            )
            Box(
                modifier = Modifier
                    .onSizeChanged { size ->
                        if (index < widths.size) widths[index] = size.width else widths.add(size.width)
                    }
                    .graphicsLayer {
                        translationX = if (lifted) dragOffset else 0f
                        scaleX = lift
                        scaleY = lift
                    }
                    .pointerInput(index, shelves) {
                        detectDragGesturesAfterLongPress(
                            onDragStart = {
                                dragging = index
                                dragOffset = 0f
                            },
                            onDragEnd = {
                                dragging = -1
                                dragOffset = 0f
                            },
                            onDragCancel = {
                                dragging = -1
                                dragOffset = 0f
                            },
                            onDrag = { change, amount ->
                                change.consume()
                                dragOffset += amount.x
                                val threshold = widths.getOrElse(index) { 0 } * 0.5f + minTouch
                                val next = when {
                                    dragOffset > threshold && index < shelves.lastIndex -> index + 1
                                    dragOffset < -threshold && index > 0 -> index - 1
                                    else -> -1
                                }
                                if (next >= 0) {
                                    onReorder(shelves.toMutableList().also { it[index] = it[next]; it[next] = shelf })
                                    dragOffset = 0f
                                }
                            },
                        )
                    }
            ) {
                ShelfPill(
                    label = shelf.name,
                    count = null,
                    selected = shelf == selected,
                    lifted = lifted,
                    accent = accent,
                    ink = ink,
                    muted = muted,
                )
            }
        }
    }
}

@Composable
private fun ShelfPill(
    label: String,
    count: String?,
    selected: Boolean,
    lifted: Boolean,
    accent: Color,
    ink: Color,
    muted: Color,
) {
    val shape = RoundedCornerShape(14.dp)
    Row(
        modifier = Modifier
            .height(44.dp)
            .clip(shape)
            .background(if (selected) accent else ink.copy(alpha = 0.07f))
            .border(
                width = 1.dp,
                color = when {
                    selected || lifted -> Color.Transparent
                    else -> ink.copy(alpha = 0.14f)
                },
                shape = shape,
            )
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(
            text = label,
            color = if (selected) Color(0xFF0B0F0E) else ink,
            style = MaterialTheme.typography.labelLarge,
            fontFamily = FontFamily.Monospace,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
        )
        if (count != null) {
            Text(
                text = count,
                color = if (selected) Color(0xFF0B0F0E).copy(alpha = 0.6f) else muted,
                style = MaterialTheme.typography.labelSmall,
                fontFamily = FontFamily.Monospace,
            )
        }
    }
}

/** Per-shelf display switch: tile grid, vertical cards, or the compact library row. */
@Composable
private fun ShelfModeBar(
    mode: HomeShelfMode,
    accent: Color,
    ink: Color,
    muted: Color,
    onModeChange: (HomeShelfMode) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Text(
            text = "VIEW",
            color = muted,
            style = MaterialTheme.typography.labelSmall,
            fontFamily = FontFamily.Monospace,
        )
        Spacer(Modifier.weight(1f))
        HomeShelfMode.entries.forEach { option ->
            val selected = option == mode
            val shape = RoundedCornerShape(10.dp)
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .clip(shape)
                    .background(if (selected) accent else ink.copy(alpha = 0.06f))
                    .border(
                        1.dp,
                        if (selected) Color.Transparent else ink.copy(alpha = 0.12f),
                        shape,
                    )
                    .clickable { onModeChange(option) }
                    .padding(horizontal = 10.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = option.label,
                    color = if (selected) Color(0xFF0B0F0E) else muted,
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                )
            }
        }
    }
}

private val HomeShelfMode.label: String
    get() = when (this) {
        HomeShelfMode.GRID -> "TILES"
        HomeShelfMode.CARDS -> "CARDS"
        HomeShelfMode.COMPACT -> "LIST"
    }

@Composable
private fun ShelfPage(
    shelf: HomeShelf,
    items: List<ShelfItem>,
    mode: HomeShelfMode,
    accent: Color,
    ink: Color,
    muted: Color,
    onPlayAll: () -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        ShelfPageHeader(
            shelf = shelf,
            count = items.size,
            accent = accent,
            ink = ink,
            muted = muted,
            onPlayAll = onPlayAll,
        )
        Spacer(Modifier.height(10.dp))
        if (items.isEmpty()) {
            ShelfEmpty(shelf = shelf, ink = ink, muted = muted)
        } else {
            when (mode) {
                HomeShelfMode.GRID -> ShelfGrid(items = items, ink = ink, muted = muted)
                HomeShelfMode.CARDS -> ShelfCards(items = items, ink = ink, muted = muted)
                HomeShelfMode.COMPACT -> ShelfCompact(items = items, ink = ink, muted = muted)
            }
        }
    }
}

@Composable
private fun ShelfPageHeader(
    shelf: HomeShelf,
    count: Int,
    accent: Color,
    ink: Color,
    muted: Color,
    onPlayAll: () -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text(
                text = shelf.title,
                color = ink,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = shelf.blurb,
                color = muted,
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Text(
            text = "$count",
            color = muted,
            style = MaterialTheme.typography.labelSmall,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(end = 10.dp),
        )
        if (count > 0) {
            val shape = RoundedCornerShape(12.dp)
            Box(
                modifier = Modifier
                    .height(36.dp)
                    .clip(shape)
                    .background(accent)
                    .clickable(onClick = onPlayAll)
                    .padding(horizontal = 12.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "PLAY",
                    color = Color(0xFF0B0F0E),
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

internal val HomeShelf.title: String
    get() = when (this) {
        HomeShelf.HISTORY -> "Recently played"
        HomeShelf.FAVORITES -> "Liked music"
        HomeShelf.TOP10 -> "Top 10"
        HomeShelf.PLAYLISTS -> "Playlists"
        HomeShelf.DISCOVER -> "Discover"
    }

internal val HomeShelf.blurb: String
    get() = when (this) {
        HomeShelf.HISTORY -> "Newest first, straight from your plays"
        HomeShelf.FAVORITES -> "Everything you starred on the server"
        HomeShelf.TOP10 -> "The songs you start most"
        HomeShelf.PLAYLISTS -> "Yours, plus mixes built from this library"
        HomeShelf.DISCOVER -> "Never played yet"
    }

@Composable
private fun ShelfEmpty(shelf: HomeShelf, ink: Color, muted: Color) {
    val shape = RoundedCornerShape(20.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape)
            .background(ink.copy(alpha = 0.05f))
            .border(1.dp, ink.copy(alpha = 0.10f), shape)
            .padding(18.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = shelf.emptyLine,
            color = muted,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

private val HomeShelf.emptyLine: String
    get() = when (this) {
        HomeShelf.HISTORY -> "Nothing played yet. Start a song and it lands here."
        HomeShelf.FAVORITES -> "No liked songs yet. Star a track in the player."
        HomeShelf.TOP10 -> "No plays recorded yet."
        HomeShelf.PLAYLISTS -> "No playlists on the server yet."
        HomeShelf.DISCOVER -> "Everything in the library has been played."
    }

/** Tile mode: the screenshot's square covers with the label over a scrim. */
@Composable
private fun ShelfGrid(items: List<ShelfItem>, ink: Color, muted: Color) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 8.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(items, key = { it.id }) { item ->
            Column(Modifier.clickable(onClick = item.onClick)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(ink.copy(alpha = 0.08f)),
                ) {
                    if (item.art != null) {
                        AsyncImage(
                            model = item.art,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    0.45f to Color.Transparent,
                                    1f to Color.Black.copy(alpha = 0.82f),
                                )
                            )
                    )
                    if (item.badge != null) {
                        SubsonicBadge(
                            size = 22.dp,
                            scrim = Color.Black.copy(alpha = 0.42f),
                            modifier = Modifier.align(Alignment.TopEnd).padding(6.dp),
                        )
                    }
                    Text(
                        text = item.title,
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(horizontal = 8.dp, vertical = 7.dp),
                    )
                }
                if (item.subtitle != null) {
                    Text(
                        text = item.subtitle,
                        color = muted,
                        style = MaterialTheme.typography.labelSmall,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 3.dp, start = 2.dp),
                    )
                }
            }
        }
    }
}

/** Card mode: one full-width row per item, big art, title and meta. */
@Composable
private fun ShelfCards(items: List<ShelfItem>, ink: Color, muted: Color) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(bottom = 8.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        itemsIndexed(items, key = { _, item -> item.id }) { _, item ->
            val shape = RoundedCornerShape(18.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape)
                    .background(ink.copy(alpha = 0.06f))
                    .border(1.dp, ink.copy(alpha = 0.12f), shape)
                    .clickable(onClick = item.onClick)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(62.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(ink.copy(alpha = 0.08f)),
                ) {
                    if (item.art != null) {
                        AsyncImage(
                            model = item.art,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
                Column(Modifier.weight(1f)) {
                    Text(
                        text = item.title,
                        color = ink,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    if (item.subtitle != null) {
                        Text(
                            text = item.subtitle,
                            color = muted,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
                if (item.badge != null) {
                    Text(
                        text = item.badge,
                        color = muted,
                        style = MaterialTheme.typography.labelSmall,
                        fontFamily = FontFamily.Monospace,
                    )
                }
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(ink.copy(alpha = 0.06f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "▶",
                        color = muted,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }
        }
    }
}

/** List mode: the Library track row, condensed. */
@Composable
private fun ShelfCompact(items: List<ShelfItem>, ink: Color, muted: Color) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(bottom = 8.dp),
        modifier = Modifier.fillMaxSize(),
    ) {
        itemsIndexed(items, key = { _, item -> item.id }) { _, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable(onClick = item.onClick)
                    .padding(horizontal = 4.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(ink.copy(alpha = 0.08f)),
                ) {
                    if (item.art != null) {
                        AsyncImage(
                            model = item.art,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
                Column(Modifier.weight(1f)) {
                    Text(
                        text = item.title,
                        color = ink,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    if (item.subtitle != null) {
                        Text(
                            text = item.subtitle,
                            color = muted,
                            style = MaterialTheme.typography.labelSmall,
                            fontFamily = FontFamily.Monospace,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
                if (item.badge != null) {
                    Text(
                        text = item.badge,
                        color = muted,
                        style = MaterialTheme.typography.labelSmall,
                        fontFamily = FontFamily.Monospace,
                    )
                }
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(ink.copy(alpha = 0.06f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "▶",
                        color = muted,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }
        }
    }
}
