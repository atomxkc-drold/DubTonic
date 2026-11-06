package com.androidharness.musicplayer.ui.glass

import android.os.Build
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.androidharness.musicplayer.ui.image.AlbumColors

/**
 * Album art blurred behind a palette gradient. Modifier.blur maps to RenderEffect on
 * API 31+; older versions get a smaller radius and the translucent scrim still carries
 * the same layout.
 */
@Composable
fun BlurTwistBackground(
    artworkUri: Uri?,
    colors: AlbumColors,
    blurRadiusDp: Int,
    saturation: Float,
    modifier: Modifier = Modifier,
) {
    val tinted = colors.withSaturationLocal(saturation)
    Box(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(tinted.background, Color.Black.copy(alpha = 0.92f))
                    )
                )
        )
        if (artworkUri != null) {
            AsyncImage(
                model = artworkUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(
                        radius = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            blurRadiusDp.dp
                        } else {
                            minOf(blurRadiusDp, 20).dp
                        }
                    ),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        listOf(
                            tinted.primary.copy(alpha = 0.32f),
                            tinted.background.copy(alpha = 0.72f),
                            Color.Black.copy(alpha = 0.9f),
                        )
                    )
                )
        )
    }
}

@Composable
fun GlassSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(28.dp),
    alpha: Float = 0.18f,
    borderAlpha: Float = 0.28f,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(Color.White.copy(alpha = alpha))
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    listOf(
                        Color.White.copy(alpha = borderAlpha),
                        Color.White.copy(alpha = borderAlpha * 0.25f),
                    )
                ),
                shape = shape,
            ),
        content = content,
    )
}

private fun AlbumColors.withSaturationLocal(factor: Float): AlbumColors {
    fun adjust(color: Color): Color {
        val luma = 0.2126f * color.red + 0.7152f * color.green + 0.0722f * color.blue
        return Color(
            red = (luma + (color.red - luma) * factor).coerceIn(0f, 1f),
            green = (luma + (color.green - luma) * factor).coerceIn(0f, 1f),
            blue = (luma + (color.blue - luma) * factor).coerceIn(0f, 1f),
            alpha = color.alpha,
        )
    }
    return copy(primary = adjust(primary), secondary = adjust(secondary), background = adjust(background))
}

@Suppress("unused")
private val UnusedRadius: Dp = 0.dp
