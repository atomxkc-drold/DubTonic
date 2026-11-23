package com.androidharness.musicplayer.ui.motion

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlinx.coroutines.delay

/**
 * Directional motion blur for scrolling surfaces.
 *
 * A scroll gesture reports its per-frame delta through nested scroll. Each delta raises the
 * blur radius; an idle ticker decays it back to zero, so content smears while the finger is
 * moving and snaps back to crisp when scrolling stops.
 *
 * Placement matters: this modifier must sit *before* `verticalScroll`/`LazyColumn` in the
 * chain, because nested-scroll dispatch travels outward to ancestors. The radius is read
 * inside `graphicsLayer`, so a moving scroll invalidates the layer only and never
 * recomposes the screen.
 *
 * On API < 31 there is no RenderEffect, so the blur is a no-op rather than a costly
 * software fallback.
 */
@Composable
fun Modifier.motionBlurScroll(
    enabled: Boolean,
    intensityPercent: Int,
): Modifier {
    if (!enabled || intensityPercent <= 0 || Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
        return this
    }

    val density = LocalDensity.current
    val maxRadiusPx = remember(density) { with(density) { MotionBlur.MAX_RADIUS.dp.toPx() } }
    val strength = remember(intensityPercent, maxRadiusPx) {
        // Per-frame scroll delta (px) to blur radius (px).
        (intensityPercent / 100f) * MotionBlur.DELTA_TO_RADIUS * maxRadiusPx / MotionBlur.MAX_RADIUS
    }

    var radius by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(MotionBlur.TICK_MS)
            val decayed = radius * MotionBlur.DECAY
            radius = if (decayed < 0.08f) 0f else decayed
        }
    }

    val connection = remember(strength, maxRadiusPx) {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = abs(available.y)
                if (delta > 0f) {
                    val target = (delta * strength).coerceIn(0f, maxRadiusPx)
                    if (target > radius) radius = target
                }
                return Offset.Zero
            }
        }
    }

    return this
        .nestedScroll(connection)
        .graphicsLayer {
            renderEffect = if (radius > 0.1f) {
                BlurEffect(radius, radius, TileMode.Decal)
            } else {
                null
            }
        }
}

object MotionBlur {
    /** Strongest blur the dial can reach, in dp. */
    const val MAX_RADIUS = 12f

    /** How far a scroll delta travels, relative to the maximum radius. */
    const val DELTA_TO_RADIUS = 1.4f

    /** Fraction of the radius kept per tick; the rest is the smear fading out. */
    const val DECAY = 0.82f

    const val TICK_MS = 16L
}
