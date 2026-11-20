package com.androidharness.musicplayer.feature.home

import android.net.Uri
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Full-bleed ambient wall for the home screen: the playing artwork blurred to
 * near-abstraction, three slowly drifting colour clouds sampled from the album palette,
 * a rotating sheen and a vignette. Every layer is a gradient, so it stays cheap on devices
 * where [blur] is a no-op (below API 31).
 */
@Composable
fun HomeBackdrop(
    spec: AuroraSpec,
    artwork: Uri?,
    dark: Boolean,
    blurRadiusDp: Int,
    parallaxPx: Float,
    modifier: Modifier = Modifier,
) {
    val base = if (dark) lerp(spec.shade, Color.Black, 0.30f) else lerp(spec.shade, Color.White, 0.88f)
    val baseTop = if (dark) lerp(spec.shade, spec.blobs[0], 0.22f) else lerp(base, spec.blobs[0], 0.35f)
    val cloudAlpha = if (dark) 0.55f else 0.42f
    val drift = spec.driftMillis > 0

    Box(
        modifier = modifier
            .fillMaxSize()
            .clipToBounds()
            .background(Brush.verticalGradient(listOf(baseTop, base, lerp(base, Color.Black, 0.25f)))),
    ) {
        if (artwork != null) {
            AsyncImage(
                model = artwork,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        scaleX = 1.4f
                        scaleY = 1.4f
                        alpha = if (dark) 0.34f else 0.20f
                        translationY = parallaxPx * 0.12f
                    }
                    .blur((blurRadiusDp * 1.6f).dp),
            )
        }

        DriftingCloud(
            color = spec.blobs[0],
            size = 0.95f,
            anchorX = -0.10f,
            anchorY = -0.06f,
            alpha = cloudAlpha,
            phase = 0f,
            driftMillis = spec.driftMillis,
            parallaxPx = parallaxPx,
        )
        DriftingCloud(
            color = spec.blobs[1],
            size = 0.85f,
            anchorX = 0.55f,
            anchorY = 0.18f,
            alpha = cloudAlpha * 0.9f,
            phase = 2.1f,
            driftMillis = spec.driftMillis,
            parallaxPx = parallaxPx,
        )
        DriftingCloud(
            color = spec.blobs[2],
            size = 1.1f,
            anchorX = 0.20f,
            anchorY = 0.55f,
            alpha = cloudAlpha * 0.75f,
            phase = 4.3f,
            driftMillis = spec.driftMillis,
            parallaxPx = parallaxPx,
        )

        if (drift) {
            val transition = rememberInfiniteTransition(label = "sheen")
            val sweep by transition.animateFloat(
                initialValue = 0f,
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 52_000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart,
                ),
                label = "sheenRotation",
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { rotationZ = sweep }
                    .background(
                        Brush.sweepGradient(
                            listOf(
                                Color.Transparent,
                                spec.glow.copy(alpha = 0.14f),
                                Color.Transparent,
                                Color.Transparent,
                            )
                        )
                    ),
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color.Transparent,
                            lerp(base, Color.Black, if (dark) 0.55f else 0.10f),
                        )
                    )
                )
        )
    }
}

@Composable
private fun DriftingCloud(
    color: Color,
    size: Float,
    anchorX: Float,
    anchorY: Float,
    alpha: Float,
    phase: Float,
    driftMillis: Int,
    parallaxPx: Float,
) {
    val progress: Float by if (driftMillis > 0) {
        rememberInfiniteTransition(label = "cloud").animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = driftMillis, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "cloudDrift",
        )
    } else {
        remember { mutableFloatStateOf(0.5f) }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val angle = (progress * 2f * PI + phase).toFloat()
        val parallax = with(LocalDensity.current) { (parallaxPx * 0.16f).toDp() }
        val x = maxWidth * (anchorX + cos(angle) * 0.055f)
        val y = maxHeight * (anchorY + sin(angle) * 0.035f) + parallax
        Box(
            modifier = Modifier
                .offset(x = x, y = y)
                .size(maxWidth * size)
                .background(
                    Brush.radialGradient(
                        listOf(color.copy(alpha = alpha), color.copy(alpha = 0f)),
                    )
                ),
        )
    }
}


/** Slow breathing halo used behind the hero artwork. */
@Composable
fun BreathingHalo(color: Color, animate: Boolean, modifier: Modifier = Modifier) {
    val scale: Float by if (animate) {
        rememberInfiniteTransition(label = "halo").animateFloat(
            initialValue = 0.94f,
            targetValue = 1.06f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 6_400, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "haloScale",
        )
    } else {
        remember { mutableFloatStateOf(1f) }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .background(
                Brush.radialGradient(
                    listOf(
                        color.copy(alpha = 0.34f),
                        color.copy(alpha = 0.10f),
                        Color.Transparent,
                    )
                )
            )
    )
}

/** Small live indicator: a pulsing dot used next to the time-of-day kicker. */
@Composable
fun PulseDot(active: Boolean, color: Color, modifier: Modifier = Modifier) {
    val alpha: Float by if (active) {
        rememberInfiniteTransition(label = "pulse").animateFloat(
            initialValue = 0.35f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1_600, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "pulseAlpha",
        )
    } else {
        remember { mutableFloatStateOf(0.5f) }
    }
    Box(
        modifier = modifier
            .size(7.dp)
            .background(color.copy(alpha = alpha), CircleShape)
    )
}
