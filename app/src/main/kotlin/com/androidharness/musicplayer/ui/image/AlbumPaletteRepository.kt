package com.androidharness.musicplayer.ui.image

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.LruCache
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.toBitmap
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class AlbumColors(
    val primary: Color = Color(0xFF2F6F63),
    val secondary: Color = Color(0xFF4A5A70),
    val background: Color = Color(0xFF0B0F0E),
    val onPrimary: Color = Color(0xFFF4F7F5),
)

@Singleton
class AlbumPaletteRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val images: AppImageLoader,
) {
    private val cache = LruCache<String, AlbumColors>(32)

    suspend fun colorsFor(uri: Uri?): AlbumColors = withContext(Dispatchers.Default) {
        if (uri == null) return@withContext AlbumColors()
        cache.get(uri.toString())?.let { return@withContext it }

        val result = runCatching {
            images.loader.execute(
                ImageRequest.Builder(context)
                    .data(uri)
                    .size(128)
                    .build()
            )
        }.getOrNull()

        val bitmap = (result as? SuccessResult)
            ?.image
            ?.let { image -> runCatching { image.toBitmap(64, 64) }.getOrNull() }
            ?: return@withContext AlbumColors()

        val colors = fromBitmap(bitmap)
        cache.put(uri.toString(), colors)
        colors
    }

    private fun fromBitmap(bitmap: Bitmap): AlbumColors {
        val palette = Palette.from(bitmap)
            .maximumColorCount(24)
            .clearFilters()
            .generate()

        val primary = palette.getVibrantColor(
            palette.getMutedColor(
                palette.getDominantColor(DEFAULT_PRIMARY)
            )
        )
        val secondary = palette.getMutedColor(DEFAULT_SECONDARY)
        val background = palette.getDarkMutedColor(DEFAULT_BACKGROUND)

        return AlbumColors(
            primary = Color(primary),
            secondary = Color(secondary),
            background = Color(background),
            onPrimary = if (primary.luminance() > 0.55f) Color(0xFF0B0F0E) else Color(0xFFF4F7F5),
        )
    }

    private fun Int.luminance(): Float {
        val r = android.graphics.Color.red(this) / 255f
        val g = android.graphics.Color.green(this) / 255f
        val b = android.graphics.Color.blue(this) / 255f
        return 0.2126f * r + 0.7152f * g + 0.0722f * b
    }

    private companion object {
        const val DEFAULT_PRIMARY = 0xFF2F6F63.toInt()
        const val DEFAULT_SECONDARY = 0xFF4A5A70.toInt()
        const val DEFAULT_BACKGROUND = 0xFF0B0F0E.toInt()
    }
}
