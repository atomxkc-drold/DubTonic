package com.androidharness.musicplayer.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.luminance

/**
 * Contrast-safe ink for text drawn on a translucent or artwork-tinted pill.
 *
 * The pill's effective rendered colour is the tint composited over the page base, so the
 * decision is made against what the eye actually sees rather than the raw tint. When the
 * result is too close to the candidate ink, the ink is pushed to the opposite end of the
 * scale so a label never washes out over artwork or a blurred backdrop.
 */
fun pillInk(
    tint: Color,
    base: Color,
    darkInk: Color = Color(0xFFF4F7F5),
    lightInk: Color = Color(0xFF0B0F0E),
    minContrast: Float = 0.42f,
): Color {
    val rendered = tint.compositeOver(base)
    val renderedLuma = rendered.luminance()
    val preferred = if (renderedLuma > 0.5f) lightInk else darkInk
    val fallback = if (renderedLuma > 0.5f) darkInk else lightInk
    val gap = kotlin.math.abs(preferred.luminance() - renderedLuma)
    return if (gap >= minContrast) preferred else fallback
}

/** Same decision, but for a pill whose own background is already opaque. */
fun inkOn(background: Color, darkInk: Color = Color(0xFFF4F7F5), lightInk: Color = Color(0xFF0B0F0E)): Color =
    if (background.luminance() > 0.5f) lightInk else darkInk

/**
 * Clamps a dynamic artwork colour so it stays legible as a pill tint: very dark colours are
 * lifted and very light ones are pulled down, keeping the hue but bounding the luminance.
 */
fun clampTint(color: Color, minLuma: Float = 0.10f, maxLuma: Float = 0.72f): Color {
    val luma = color.luminance()
    if (luma in minLuma..maxLuma) return color
    val target = luma.coerceIn(minLuma, maxLuma)
    val shift = target - luma
    return Color(
        red = (color.red + shift).coerceIn(0f, 1f),
        green = (color.green + shift).coerceIn(0f, 1f),
        blue = (color.blue + shift).coerceIn(0f, 1f),
        alpha = color.alpha,
    )
}
