package com.androidharness.musicplayer.feature.player

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.math.sin

/**
 * Waveform-shaped scrubber.
 *
 * The bar envelope is derived from the track id, so every song keeps a stable silhouette
 * without needing the audio decoded first. It is decoration, not analysis: playback
 * position is what actually drives the played/unplayed split, and dragging previews the
 * target before committing on release.
 */
@Composable
fun WaveformScrubber(
    trackId: String,
    positionMs: Long,
    durationMs: Long,
    isPlaying: Boolean,
    accent: Color,
    onSeek: (Float) -> Unit,
    modifier: Modifier = Modifier,
    bars: Int = 84,
) {
    val peaks = remember(trackId, bars) { envelopeFor(trackId, bars) }
    val actual = if (durationMs > 0L) {
        (positionMs.toFloat() / durationMs.toFloat()).coerceIn(0f, 1f)
    } else {
        0f
    }
    var widthPx by remember { mutableFloatStateOf(1f) }
    var dragging by remember { mutableStateOf(false) }
    var dragFraction by remember { mutableFloatStateOf(0f) }
    val shown = if (dragging) dragFraction else actual

    Box(
        modifier
            .fillMaxWidth()
            .height(54.dp)
            .onSizeChanged { widthPx = it.width.toFloat().coerceAtLeast(1f) }
            .pointerInput(widthPx) {
                detectTapGestures { offset ->
                    onSeek((offset.x / widthPx).coerceIn(0f, 1f))
                }
            }
            .pointerInput(widthPx) {
                detectHorizontalDragGestures(
                    onDragStart = { offset ->
                        dragging = true
                        dragFraction = (offset.x / widthPx).coerceIn(0f, 1f)
                    },
                    onDragEnd = {
                        dragging = false
                        onSeek(dragFraction)
                    },
                    onDragCancel = { dragging = false },
                    onHorizontalDrag = { change, _ ->
                        dragFraction = (change.position.x / widthPx).coerceIn(0f, 1f)
                    },
                )
            },
    ) {
        Canvas(Modifier.fillMaxWidth().height(54.dp)) {
            val count = peaks.size
            val gap = size.width / (count * 2.6f)
            val barWidth = (size.width / count) - gap
            val centerY = size.height / 2f
            val maxBar = size.height * 0.86f

            peaks.forEachIndexed { index, peak ->
                val x = index * (barWidth + gap)
                // A gentle pulse while playing keeps the bars from looking dead.
                val lift = if (isPlaying) {
                    0.94f + 0.06f * sin((index.toFloat() / count) * 7f)
                } else {
                    1f
                }
                val h = (maxBar * peak * lift).coerceAtLeast(2f)
                val played = (index + 0.5f) / count <= shown
                drawRoundRect(
                    color = if (played) accent else Color.White.copy(alpha = 0.26f),
                    topLeft = Offset(x, centerY - h / 2f),
                    size = Size(barWidth.coerceAtLeast(1f), h),
                    cornerRadius = CornerRadius(barWidth / 2f, barWidth / 2f),
                )
            }
        }
    }
}

/** Deterministic, musical-looking envelope: fuller in the middle than at the edges. */
internal fun envelopeFor(seed: String, count: Int): FloatArray {
    if (count <= 0) return FloatArray(0)
    var state = seed.hashCode().toLong() * 2654435761L + 1L
    return FloatArray(count) { index ->
        state = state * 6364136223846793005L + 1442695040888963407L
        val bits = ((state ushr 33).toInt() and 0x7FFFFFFF)
        val random = bits / 0x7FFFFFFF.toFloat()
        val t = if (count == 1) 0.5f else index / (count - 1f)
        val envelope = 0.42f + 0.58f * sin(Math.PI * t).toFloat()
        (0.12f + 0.88f * abs(random) * envelope).coerceIn(0.08f, 1f)
    }
}
