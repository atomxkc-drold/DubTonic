package com.androidharness.musicplayer.playback

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.media3.common.util.BitmapLoader
import androidx.media3.common.util.UnstableApi
import com.androidharness.musicplayer.ui.image.AppImageLoader
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.ListenableFutureTask
import java.io.IOException
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import kotlinx.coroutines.runBlocking

/**
 * Media3's default loader only understands local URIs and inline artwork bytes, so a
 * Subsonic cover-art URL was silently dropped and the notification came up blank.
 * This one resolves the same authenticated URL the rest of the UI already uses.
 */
@UnstableApi
class SubsonicBitmapLoader(
    private val images: AppImageLoader,
    private val executor: Executor,
) : BitmapLoader {

    override fun supportsMimeType(mimeType: String): Boolean = mimeType.startsWith("image/")

    override fun decodeBitmap(data: ByteArray): ListenableFuture<Bitmap> =
        submit {
            BitmapFactory.decodeByteArray(data, 0, data.size)
                ?: throw IOException("Could not decode artwork bytes")
        }

    override fun loadBitmap(uri: Uri): ListenableFuture<Bitmap> =
        submit {
            runBlocking { images.bitmapFor(uri) }
                ?: throw IOException("Could not load artwork for $uri")
        }

    private fun submit(task: () -> Bitmap): ListenableFuture<Bitmap> {
        val future = ListenableFutureTask.create(Callable { task() })
        executor.execute(future)
        return future
    }
}
