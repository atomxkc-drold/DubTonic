package com.androidharness.musicplayer.core.di

import com.androidharness.musicplayer.data.settings.DataStoreSettingsRepository
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import com.androidharness.musicplayer.playback.StreamPlaybackFallback
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule {

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(
        implementation: DataStoreSettingsRepository,
    ): SettingsRepository

    @Module
    @InstallIn(SingletonComponent::class)
    object StreamPlaybackFallbackProvider {
        @Provides
        @Singleton
        fun provideStreamPlaybackFallback(): StreamPlaybackFallback = StreamPlaybackFallback()
    }
}
