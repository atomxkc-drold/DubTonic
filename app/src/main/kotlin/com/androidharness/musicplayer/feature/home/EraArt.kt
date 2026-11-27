package com.androidharness.musicplayer.feature.home

import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.androidharness.musicplayer.data.settings.TimeMachineMotion
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random
import kotlinx.coroutines.delay

/**
 * Album art that dresses the Era Dial. A few covers per decade sit just outside the ring, so
 * the dial reads as a legend instead of a decorative circle. Geometry lives here as plain
 * data ([eraArtTiles]) so the placement can be checked without drawing.
 */
internal data class EraArtSource(
    val album: String,
    val year: Int?,
    val art: Uri?,
)

internal data class EraArtTile(
    val id: String,
    val startYear: Int,
    val art: Uri?,
    val album: String,
    /** Exact year, or null for the undated pile. */
    val year: Int?,
    /** Clockwise degrees from twelve o'clock, the same origin the ring arcs use. */
    val angleDegrees: Float,
)

/** Covers per decade. Small piles are shown whole; larger ones draw a random sample. */
private const val MAX_PER_ERA = 3

/** Below this the decade has too little to gain from shuffling, so it keeps its first covers. */
private const val SMALL_ERA = 5

/**
 * Picks up to [perEra] covers for every decade, including the undated pile. The spread follows
 * the arc, so a wide era fans its covers out while a narrow one keeps them as a tight stack.
 */
internal fun eraArtTiles(
    buckets: List<EraBucket>,
    pool: Map<String, List<EraArtSource>>,
    random: Random,
    perEra: Int = MAX_PER_ERA,
): List<EraArtTile> = buildList {
    buckets.forEach { bucket ->
        val candidates = pool[bucket.id].orEmpty()
        if (candidates.isEmpty()) return@forEach
        val picked = if (candidates.size < SMALL_ERA) {
            candidates.take(perEra)
        } else {
            candidates.shuffled(random).take(perEra)
        }
        val spread = (bucket.sweepDegrees * 0.55f).coerceAtMost(picked.size * 11f)
        picked.forEachIndexed { index, source ->
            val offset = if (picked.size == 1) {
                0f
            } else {
                (index / (picked.size - 1f) - 0.5f) * spread
            }
            add(
                EraArtTile(
                    id = bucket.id,
                    startYear = bucket.startYear,
                    art = source.art,
                    album = source.album,
                    year = source.year,
                    angleDegrees = bucket.startDegrees + bucket.sweepDegrees / 2f + offset,
                )
            )
        }
    }
}

/**
 * Cycles a spotlight through the covers while the dial turns: one tile lights up, its album and
 * exact year show under the ring, then it hands over to the next. Nothing moves under reduced
 * motion, so the spotlight stays off there.
 */
@Composable
internal fun rememberEraArtFeature(
    tiles: List<EraArtTile>,
    animate: Boolean,
    paused: Boolean = false,
    holdMillis: Long = 3_000,
    gapMillis: Long = 1_200,
): EraArtTile? {
    var featured by remember(tiles) { mutableStateOf<EraArtTile?>(null) }
    LaunchedEffect(tiles, animate, paused) {
        if (!animate || paused || tiles.isEmpty()) {
            featured = null
            return@LaunchedEffect
        }
        while (true) {
            delay(gapMillis)
            featured = tiles.random()
            delay(holdMillis)
            featured = null
        }
    }
    return featured
}

/** The tile closest to a touch angle, within [toleranceDegrees] of its own angle. */
internal fun nearestEraArtTile(
    tiles: List<EraArtTile>,
    degrees: Float,
    toleranceDegrees: Float = 16f,
): EraArtTile? = tiles
    .map { tile -> tile to angleDelta(tile.angleDegrees, degrees) }
    .filter { (_, delta) -> delta <= toleranceDegrees }
    .minByOrNull { (_, delta) -> delta }
    ?.first

private fun angleDelta(a: Float, b: Float): Float {
    val raw = ((a - b) % 360f + 360f) % 360f
    return if (raw > 180f) 360f - raw else raw
}

/**
 * The layer above the ring. Only ORBIT is built; the other modes are reserved until their
 * layers exist, and reduced motion keeps the covers in place without spinning them.
 */
@Composable
internal fun EraArtLayer(
    mode: TimeMachineMotion,
    tiles: List<EraArtTile>,
    spin: State<Float>,
    featured: EraArtTile?,
    selectedStartYear: String?,
    accent: Color,
    ink: Color,
    onTileTap: (EraArtTile) -> Unit,
    modifier: Modifier = Modifier,
    radius: Dp = 127.dp,
) {
    if (mode != TimeMachineMotion.ORBIT || tiles.isEmpty()) return
    EraArtOrbit(
        tiles = tiles,
        spin = spin,
        featured = featured,
        selectedStartYear = selectedStartYear,
        accent = accent,
        ink = ink,
        onTileTap = onTileTap,
        radius = radius,
        modifier = modifier,
    )
}

/**
 * Rides the dial: the parent layer carries the spin, every tile counter-rotates so covers stay
 * upright. Both angles are read inside graphicsLayer, which runs in the draw phase, so a frame
 * never recomposes this tree.
 */
@Composable
private fun EraArtOrbit(
    tiles: List<EraArtTile>,
    spin: State<Float>,
    featured: EraArtTile?,
    selectedStartYear: String?,
    accent: Color,
    ink: Color,
    onTileTap: (EraArtTile) -> Unit,
    radius: Dp,
    modifier: Modifier = Modifier,
) {
    val radiusPx = with(LocalDensity.current) { radius.toPx() }

    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer { rotationZ = spin.value },
        contentAlignment = Alignment.Center,
    ) {
        tiles.forEach { tile ->
            val selected = tile.id == selectedStartYear
            val spotlighted = tile === featured
            val radians = (tile.angleDegrees - 90f) * PI / 180.0
            val x = (cos(radians) * radiusPx).toFloat()
            val y = (sin(radians) * radiusPx).toFloat()

            val scale by animateFloatAsState(
                targetValue = when {
                    spotlighted -> 1.3f
                    selected -> 1.06f
                    else -> 1f
                },
                animationSpec = tween(durationMillis = 260),
                label = "eraArtScale",
            )
            val alpha by animateFloatAsState(
                targetValue = if (spotlighted || selected) 1f else 0.74f,
                animationSpec = tween(durationMillis = 260),
                label = "eraArtAlpha",
            )

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onTileTap(tile) }
                    .graphicsLayer {
                        translationX = x
                        translationY = y
                        rotationZ = -spin.value
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
                    .clip(CircleShape)
                    .background(ink.copy(alpha = 0.22f))
                    .border(
                        width = if (spotlighted) 2.dp else 1.dp,
                        color = when {
                            spotlighted -> accent
                            selected -> accent.copy(alpha = 0.9f)
                            else -> Color.White.copy(alpha = 0.22f)
                        },
                        shape = CircleShape,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    model = tile.art,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

/**
 * The caption under the ring: the spotlighted cover's album and exact year, in the same
 * monospace and glass language as the rest of Home. The strip keeps its height so the dial
 * never jumps when the caption comes and goes.
 */
@Composable
internal fun EraArtSpotlight(
    tile: EraArtTile?,
    accent: Color,
    ink: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp),
        contentAlignment = Alignment.Center,
    ) {
        AnimatedContent(
            targetState = tile,
            transitionSpec = { fadeIn(tween(durationMillis = 220)) togetherWith fadeOut(tween(durationMillis = 160)) },
            label = "eraSpotlight",
        ) { current ->
            if (current == null) {
                Box(Modifier.height(0.dp))
            } else {
                val shape = RoundedCornerShape(12.dp)
                Row(
                    modifier = Modifier
                        .clip(shape)
                        .background(ink.copy(alpha = 0.10f))
                        .border(1.dp, ink.copy(alpha = 0.16f), shape)
                        .padding(start = 6.dp, end = 12.dp, top = 5.dp, bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(9.dp),
                ) {
                    AsyncImage(
                        model = current.art,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(26.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(ink.copy(alpha = 0.12f)),
                    )
                    Text(
                        text = current.album.uppercase(),
                        color = ink,
                        style = MaterialTheme.typography.labelMedium,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.widthIn(max = 168.dp),
                    )
                    Text(
                        text = current.year?.toString() ?: "NO YEAR",
                        color = accent,
                        style = MaterialTheme.typography.labelSmall,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}
