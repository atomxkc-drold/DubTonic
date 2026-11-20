package com.androidharness.musicplayer.ui.image

import android.content.Context
import android.net.Uri
import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.androidharness.musicplayer.core.di.NetworkModule
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okio.Path.Companion.toOkioPath

/**
 * Single Coil loader for the app.
 *
 * Cover art is fetched through the authenticated OkHttp client, so request URLs carry no
 * credentials: memory and disk cache keys stay stable when the server rotates the token,
 * and nothing secret can leak into a persisted URI.
 */
@Singleton
class AppImageLoader @Inject constructor(
    @ApplicationContext private val context: Context,
    settings: SettingsRepository,
    @Named(NetworkModule.IMAGE_CLIENT) private val imageClient: OkHttpClient,
) {
    private val cacheSettings = settings.settings.value.cache

    val loader: ImageLoader = ImageLoader.Builder(context)
        .components {
            add(
                OkHttpNetworkFetcherFactory(
                    callFactory = { imageClient },
                ),
            )
        }
        .memoryCache {
            MemoryCache.Builder()
                .maxSizePercent(context, cacheSettings.imageMemoryPercent / 100.0)
                .build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(File(context.cacheDir, "image_cache").toOkioPath())
                .maxSizeBytes(cacheSettings.imageDiskMb.toLong() * 1024L * 1024L)
                .build()
        }
        // Subsonic servers usually send no cache directives for artwork, so Coil keeps it.
        .crossfade(220)
        .build()

    fun preload(uri: Uri?) {
        if (uri == null) return
        loader.enqueue(
            ImageRequest.Builder(context)
                .data(uri)
                .size(512)
                .build()
        )
    }
}
