package com.androidharness.musicplayer.feature.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.graphicsLayer
import kotlin.math.atan2
import kotlin.math.hypot

/**
 * The Era Dial: the whole library bent into a ring of decades. Tap anywhere on the
 * ring to pick that decade, then start it. The ring keeps a slow decorative spin so the
 * section never looks static, without stealing drag gestures from the page scroll.
 */
@Composable
fun EraDial(
    buckets: List<EraBucket>,
    selected: EraBucket?,
    accent: Color,
    ink: Color,
    muted: Color,
    animate: Boolean,
    onSelect: (EraBucket) -> Unit,
    onPlay: (EraBucket) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (buckets.isEmpty()) return

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(238.dp)
                .semantics {
                    contentDescription = "Era dial. Selected ${selected?.label ?: "nothing"}."
                },
            contentAlignment = Alignment.Center,
        ) {
            EraRing(
                buckets = buckets,
                selected = selected,
                accent = accent,
                ink = ink,
                animate = animate,
                onSelect = onSelect,
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = selected?.count?.toString() ?: "0",
                    color = ink,
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = (selected?.label ?: "—").uppercase(),
                    color = accent,
                    style = MaterialTheme.typography.labelLarge,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = if (selected?.isUndated == true) "NO DATE" else "TRACKS",
                    color = muted,
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 2.dp),
        ) {
            items(buckets, key = { it.startYear }) { bucket ->
                EraChip(
                    bucket = bucket,
                    selected = bucket.startYear == selected?.startYear,
                    accent = accent,
                    ink = ink,
                    muted = muted,
                    onClick = { onSelect(bucket) },
                )
            }
        }

        Spacer(Modifier.height(14.dp))

        val current = selected ?: return@Column
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(ink.copy(alpha = 0.10f))
                .border(
                    BorderStroke(1.dp, ink.copy(alpha = 0.16f)),
                    RoundedCornerShape(18.dp),
                )
                .clickable { onPlay(current) }
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .background(accent, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = null,
                    tint = Color(0xFF0B0F0E),
                    modifier = Modifier.size(20.dp),
                )
            }
            Column(Modifier.weight(1f)) {
                Text(
                    text = if (current.isUndated) "Play the undated pile" else "Play the ${current.label}",
                    color = ink,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "Shuffled inside the decade",
                    color = muted,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Text(
                text = "PLAY",
                color = accent,
                style = MaterialTheme.typography.labelSmall,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
private fun EraRing(
    buckets: List<EraBucket>,
    selected: EraBucket?,
    accent: Color,
    ink: Color,
    animate: Boolean,
    onSelect: (EraBucket) -> Unit,
) {
    val selectedSweep by animateFloatAsState(
        targetValue = selected?.sweepDegrees ?: 0f,
        animationSpec = tween(durationMillis = if (animate) 520 else 0),
        label = "eraSweep",
    )

    val spin: Float by if (animate) {
        rememberInfiniteTransition(label = "dial").animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 90_000, easing = LinearEasing),
            ),
            label = "dialSpin",
        )
    } else {
        androidx.compose.runtime.remember { androidx.compose.runtime.mutableFloatStateOf(0f) }
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(buckets) {
                detectTapGestures { position ->
                    val center = Offset(size.width / 2f, size.height / 2f)
                    val distance = hypot(position.x - center.x, position.y - center.y)
                    val outer = minOf(size.width, size.height) / 2f
                    if (distance < outer * 0.34f || distance > outer) return@detectTapGestures
                    val degrees = Math.toDegrees(
                        atan2(position.y - center.y, position.x - center.x).toDouble()
                    ).toFloat()
                    // The ring starts at twelve o'clock, so shift the raw touch angle.
                    eraAtAngle(buckets, degrees + 90f)?.let(onSelect)
                }
            },
    ) {
        val outer = size.minDimension / 2f
        val ringRadius = outer - 14.dp.toPx()
        val topLeft = Offset(center.x - ringRadius, center.y - ringRadius)
        val ringSize = androidx.compose.ui.geometry.Size(ringRadius * 2f, ringRadius * 2f)

        drawCircle(
            color = ink.copy(alpha = 0.06f),
            radius = ringRadius,
            center = center,
            style = Stroke(width = 26.dp.toPx()),
        )

        buckets.forEach { bucket ->
            val isSelected = bucket.startYear == selected?.startYear
            val gap = if (bucket.sweepDegrees > 6f) 2.4f else 0.8f
            val sweep = (bucket.sweepDegrees - gap).coerceAtLeast(0.6f)
            val width = if (isSelected) 24.dp.toPx() else 13.dp.toPx()
            val baseAlpha = 0.20f + bucket.share * 0.55f

            if (isSelected) {
                drawArc(
                    color = accent.copy(alpha = 0.22f),
                    startAngle = -90f + bucket.startDegrees,
                    sweepAngle = sweep,
                    useCenter = false,
                    topLeft = topLeft,
                    size = ringSize,
                    style = Stroke(width = width + 16.dp.toPx(), cap = StrokeCap.Round),
                )
            }

            drawArc(
                color = if (isSelected) accent else ink.copy(alpha = baseAlpha),
                startAngle = -90f + bucket.startDegrees,
                sweepAngle = sweep,
                useCenter = false,
                topLeft = topLeft,
                size = ringSize,
                style = Stroke(
                    width = width,
                    cap = if (sweep > 8f) StrokeCap.Round else StrokeCap.Butt,
                ),
            )
        }

        if (selected != null && selectedSweep > 0f) {
            drawArc(
                color = Color.White.copy(alpha = 0.18f),
                startAngle = -90f + selected.startDegrees,
                sweepAngle = (selectedSweep - 2.4f).coerceAtLeast(0.6f),
                useCenter = false,
                topLeft = topLeft,
                size = ringSize,
                style = Stroke(width = 1.4.dp.toPx()),
            )
        }

        // Rotating dashed halo, decorative only.
        val dashes = 44
        for (index in 0 until dashes) {
            val angle = Math.toRadians((index * 360f / dashes + spin).toDouble())
            val inner = ringRadius + 9.dp.toPx()
            val outerRadius = ringRadius + 13.dp.toPx()
            drawLine(
                color = ink.copy(alpha = if (index % 3 == 0) 0.30f else 0.12f),
                start = Offset(
                    center.x + (inner * kotlin.math.cos(angle)).toFloat(),
                    center.y + (inner * kotlin.math.sin(angle)).toFloat(),
                ),
                end = Offset(
                    center.x + (outerRadius * kotlin.math.cos(angle)).toFloat(),
                    center.y + (outerRadius * kotlin.math.sin(angle)).toFloat(),
                ),
                strokeWidth = 1.5.dp.toPx(),
                cap = StrokeCap.Round,
            )
        }
    }
}

@Composable
private fun EraChip(
    bucket: EraBucket,
    selected: Boolean,
    accent: Color,
    ink: Color,
    muted: Color,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(14.dp)
    Box(
        modifier = Modifier
            .height(44.dp)
            .clip(shape)
            .background(if (selected) accent else ink.copy(alpha = 0.08f))
            .border(
                BorderStroke(1.dp, if (selected) Color.Transparent else ink.copy(alpha = 0.14f)),
                shape,
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = if (bucket.isUndated) "??" else bucket.label.removeSuffix("s"),
                color = if (selected) Color(0xFF0B0F0E) else ink,
                style = MaterialTheme.typography.labelLarge,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = bucket.count.toString(),
                color = if (selected) Color(0xFF0B0F0E).copy(alpha = 0.6f) else muted,
                style = MaterialTheme.typography.labelSmall,
                fontFamily = FontFamily.Monospace,
            )
        }
    }
}

/** Kept for callers that scale the ring decoration themselves. */
@Composable
fun DialGlow(color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer { alpha = 0.5f }
            .background(
                Brush.radialGradient(
                    listOf(color.copy(alpha = 0.22f), Color.Transparent),
                )
            )
    )
}

