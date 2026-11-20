package com.androidharness.musicplayer.ui.nowplaying.composables

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
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
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.sin

@Composable
fun AudioMatchedWaveform(
    progress: Float,
    durationMs: Long,
    positionMs: Long,
    isPlaying: Boolean,
    staticPeaks: List<Float>,
    liveBars: List<Float>,
    activeColor: Color,
    onSeekToFraction: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val haptics = LocalHapticFeedback.current
    var isScrubbing by remember { mutableStateOf(false) }
    var scrubFraction by remember { mutableFloatStateOf(progress) }

    val displayedProgress = if (isScrubbing) scrubFraction else progress
    val barCount = 76

    // Fallback envelope if track peaks haven't finished decoding yet
    val resolvedPeaks = remember(staticPeaks, barCount) {
        if (staticPeaks.size >= barCount) {
            staticPeaks.take(barCount)
        } else {
            List(barCount) { idx ->
                val x = idx.toFloat() / barCount
                (0.28f + 0.55f * kotlin.math.abs(sin(x * 18f) * sin(x * 5.5f + 0.7f)))
                    .coerceIn(0.14f, 0.96f)
            }
        }
    }

    val playheadScale by animateFloatAsState(
        targetValue = if (isScrubbing) 1.25f else 1.0f,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "playheadScale"
    )

    Column(modifier = modifier.fillMaxWidth()) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        val fraction = (offset.x / size.width).coerceIn(0f, 1f)
                        haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        onSeekToFraction(fraction)
                    }
                }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragStart = { offset ->
                            isScrubbing = true
                            scrubFraction = (offset.x / size.width).coerceIn(0f, 1f)
                            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                        },
                        onHorizontalDrag = { change, _ ->
                            change.consume()
                            scrubFraction = (change.position.x / size.width).coerceIn(0f, 1f)
                        },
                        onDragEnd = {
                            onSeekToFraction(scrubFraction)
                            isScrubbing = false
                        },
                        onDragCancel = {
                            isScrubbing = false
                        }
                    )
                }
        ) {
            val totalWidth = size.width
            val centerY = size.height / 2f
            val slotWidth = totalWidth / barCount
            val barWidth = (slotWidth * 0.52f).coerceAtLeast(2.dp.toPx())
            val playheadX = totalWidth * displayedProgress

            for (i in 0 until barCount) {
                val baseAmp = resolvedPeaks[i]
                val liveBoost = if (isPlaying && liveBars.isNotEmpty()) {
                    val liveVal = liveBars[i % liveBars.size]
                    // Blend structural track shape (65%) with live audio energy (35%)
                    (baseAmp * 0.65f + liveVal * 0.45f).coerceIn(0.12f, 1.0f)
                } else {
                    baseAmp
                }

                val barX = i * slotWidth + (slotWidth - barWidth) / 2f
                val isPlayed = barX <= playheadX
                val barHeight = (size.height * 0.86f * liveBoost).coerceAtLeast(4.dp.toPx())

                drawRoundRect(
                    color = if (isPlayed) {
                        activeColor.copy(alpha = 0.95f)
                    } else {
                        Color.White.copy(alpha = 0.24f)
                    },
                    topLeft = Offset(barX, centerY - barHeight / 2f),
                    size = Size(barWidth, barHeight),
                    cornerRadius = CornerRadius(barWidth / 2f, barWidth / 2f)
                )
            }

            // Crisp vertical playhead cursor matching the reference screenshot
            val cursorWidth = 3.5.dp.toPx() * playheadScale
            val cursorHeight = size.height * 0.76f * playheadScale
            drawRoundRect(
                color = Color(0xFFFFE6D5),
                topLeft = Offset(
                    (playheadX - cursorWidth / 2f).coerceIn(0f, totalWidth - cursorWidth),
                    centerY - cursorHeight / 2f
                ),
                size = Size(cursorWidth, cursorHeight),
                cornerRadius = CornerRadius(cursorWidth / 2f, cursorWidth / 2f)
            )
        }

        val currentMs = if (isScrubbing) (scrubFraction * durationMs).toLong() else positionMs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatDuration(currentMs),
                color = activeColor.copy(alpha = 0.9f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = formatDuration(durationMs),
                color = Color.White.copy(alpha = 0.55f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

private fun formatDuration(ms: Long): String {
    val totalSeconds = (ms / 1000L).coerceAtLeast(0L)
    val minutes = totalSeconds / 60L
    val seconds = totalSeconds % 60L
    return "%d:%02d".format(minutes, seconds)
}
