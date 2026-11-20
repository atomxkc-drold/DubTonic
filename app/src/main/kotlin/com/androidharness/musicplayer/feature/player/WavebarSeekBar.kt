package com.androidharness.musicplayer.feature.player

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlin.math.sin
import kotlin.random.Random

/**
 * Waveform seek bar. Bars are a deterministic, track-specific shape (real waveform
 * extraction is a separate concern); tapping or dragging scrubs playback.
 */
@Composable
fun WavebarSeekBar(
    trackId: String,
    progress: Float,
    positionMs: Long,
    durationMs: Long,
    accent: Color,
    onSeek: (Float) -> Unit,
    modifier: Modifier = Modifier,
) {
    val amplitudes = remember(trackId) { waveformAmplitudes(trackId, BAR_COUNT) }
    var dragging by remember(trackId) { mutableFloatStateOf(-1f) }
    val fraction = if (dragging >= 0f) dragging else progress.coerceIn(0f, 1f)

    Column(modifier = modifier.fillMaxWidth()) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .pointerInput(trackId) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            dragging = (offset.x / size.width).coerceIn(0f, 1f)
                        },
                        onDrag = { change, _ ->
                            change.consume()
                            dragging = (change.position.x / size.width).coerceIn(0f, 1f)
                        },
                        onDragEnd = {
                            if (dragging >= 0f) onSeek(dragging)
                            dragging = -1f
                        },
                        onDragCancel = { dragging = -1f },
                    )
                },
        ) {
            val barWidth = size.width / BAR_COUNT
            val gap = barWidth * 0.28f
            val maxHeight = size.height
            val playedX = fraction * size.width

            amplitudes.forEachIndexed { index, amplitude ->
                val x = index * barWidth
                val height = (amplitude * maxHeight).coerceAtLeast(3f)
                val color = if (x <= playedX) accent else Color.White.copy(alpha = 0.26f)

                drawRoundRect(
                    color = color,
                    topLeft = Offset(x + gap / 2f, (maxHeight - height) / 2f),
                    size = Size(barWidth - gap, height),
                    cornerRadius = CornerRadius((barWidth - gap) / 2f),
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = formatTime(positionMs),
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.6f),
            )
            Text(
                text = "-" + formatTime((durationMs - positionMs).coerceAtLeast(0)),
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.6f),
            )
        }
    }
}

private const val BAR_COUNT = 52

private fun waveformAmplitudes(seed: String, count: Int): List<Float> {
    val random = Random(seed.hashCode().toLong())
    val phase = (seed.hashCode() % 7).toFloat()
    return List(count) { index ->
        val wave = 0.5f + 0.5f * sin(index * 0.55f + phase)
        val noise = random.nextFloat()
        (0.22f + wave * 0.45f + noise * 0.35f).coerceIn(0.12f, 1f)
    }
}

internal fun formatTime(milliseconds: Long): String {
    val totalSeconds = (milliseconds.coerceAtLeast(0) / 1_000).toInt()
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%d:%02d".format(minutes, seconds)
}
