package com.androidharness.musicplayer.core.di

import com.androidharness.musicplayer.data.repository.SubsonicMusicRepository
import com.androidharness.musicplayer.domain.repository.MusicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MusicRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMusicRepository(
        implementation: SubsonicMusicRepository,
    ): MusicRepository
}
