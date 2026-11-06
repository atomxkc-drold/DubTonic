package com.androidharness.musicplayer.ui.theme

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.ui.graphics.Color
import com.androidharness.musicplayer.data.settings.UiSettings
import com.androidharness.musicplayer.ui.image.AlbumColors

/**
 * Every interaction uses a spring. Animation scale from settings multiplies stiffness
 * inversely, so a higher scale means slower motion without switching to tween specs.
 */
class SpringSpecs(ui: UiSettings) {
    private val scale = ui.animationScale.coerceIn(0.25f, 2f)
    private val stiffness = (Spring.StiffnessMedium / scale).coerceAtLeast(20f)

    fun <T> gentle() = spring<T>(
        dampingRatio = 0.82f,
        stiffness = stiffness,
    )

    fun <T> snappy() = spring<T>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = stiffness * 1.6f,
    )
}

fun saturate(color: Color, factor: Float): Color {
    val luminance = 0.2126f * color.red + 0.7152f * color.green + 0.0722f * color.blue
    return Color(
        red = (luminance + (color.red - luminance) * factor).coerceIn(0f, 1f),
        green = (luminance + (color.green - luminance) * factor).coerceIn(0f, 1f),
        blue = (luminance + (color.blue - luminance) * factor).coerceIn(0f, 1f),
        alpha = color.alpha,
    )
}

fun AlbumColors.withSaturation(factor: Float): AlbumColors = copy(
    primary = saturate(primary, factor),
    secondary = saturate(secondary, factor),
    background = saturate(background, factor),
)
