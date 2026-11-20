package com.androidharness.musicplayer.core.di

import android.content.Context
import androidx.media3.common.util.UnstableApi
import androidx.media3.database.StandaloneDatabaseProvider
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.ResolvingDataSource
import androidx.media3.datasource.cache.CacheDataSource
import androidx.media3.datasource.cache.LeastRecentlyUsedCacheEvictor
import androidx.media3.datasource.cache.SimpleCache
import com.androidharness.musicplayer.playback.StreamUrlResolver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

/**
 * ExoPlayer read-through cache for streamed audio.
 *
 * Bytes fetched from the Subsonic server are written to disk so rebuffering, seek-back
 * and repeat playback do not hit the server again. A persistent download index
 * ([StandaloneDatabaseProvider]) avoids a full directory scan on every cold start, and
 * the LRU evictor caps the cache at [MAX_BYTES].
 *
 * Only HTTP(S) goes through the cache: downloaded tracks are played from `file://` URIs,
 * and [DefaultDataSource] is what teaches the player to read those instead of handing the
 * path to an HTTP connection.
 */
@OptIn(UnstableApi::class)
@Module
@InstallIn(SingletonComponent::class)
object PlaybackCacheModule {

    private const val MAX_BYTES = 512L * 1024L * 1024L

    @Provides
    @Singleton
    fun provideCacheDatabaseProvider(
        @ApplicationContext context: Context,
    ): StandaloneDatabaseProvider = StandaloneDatabaseProvider(context)

    @Provides
    @Singleton
    fun provideMediaCache(
        @ApplicationContext context: Context,
        databaseProvider: StandaloneDatabaseProvider,
    ): SimpleCache = SimpleCache(
        File(context.filesDir, "media_cache"),
        LeastRecentlyUsedCacheEvictor(MAX_BYTES),
        databaseProvider,
    )

    @Provides
    @Singleton
    fun provideCacheDataSourceFactory(
        cache: SimpleCache,
    ): CacheDataSource.Factory {
        val upstream = DefaultHttpDataSource.Factory()
            .setUserAgent("HarnessMusic")
            .setConnectTimeoutMs(15_000)
            .setReadTimeoutMs(30_000)
            .setAllowCrossProtocolRedirects(true)

        return CacheDataSource.Factory()
            .setCache(cache)
            .setUpstreamDataSourceFactory(upstream)
            // Blocking on a cache miss caches spans as they stream; IGNORE_CACHE_ON_ERROR
            // keeps playback working when the cache write fails.
            .setFlags(
                CacheDataSource.FLAG_BLOCK_ON_CACHE or
                    CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR
            )
    }

    /**
     * Data source for every queue item: `dubtonic://stream/...` URIs are rewritten into the
     * Subsonic request for the current network profile, HTTP(S) goes through the read-through
     * cache, and local downloads keep playing through [DefaultDataSource]'s file sources.
     */
    @Provides
    @Singleton
    fun providePlaybackDataSourceFactory(
        @ApplicationContext context: Context,
        cacheDataSourceFactory: CacheDataSource.Factory,
        streamUrlResolver: StreamUrlResolver,
    ): DataSource.Factory = ResolvingDataSource.Factory(
        DefaultDataSource.Factory(context, cacheDataSourceFactory),
        streamUrlResolver,
    )
}
