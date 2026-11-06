package com.androidharness.musicplayer.core.di

import android.content.Context
import androidx.room.Room
import com.androidharness.musicplayer.data.local.MusicDatabase
import com.androidharness.musicplayer.data.local.dao.AlbumDao
import com.androidharness.musicplayer.data.local.dao.ArtistDao
import com.androidharness.musicplayer.data.local.dao.PlaylistDao
import com.androidharness.musicplayer.data.local.dao.TrackDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): MusicDatabase = Room.databaseBuilder(
        context,
        MusicDatabase::class.java,
        MusicDatabase.NAME,
    )
        .fallbackToDestructiveMigration(dropAllTables = true)
        .build()

    @Provides
    fun provideTrackDao(database: MusicDatabase): TrackDao = database.trackDao()

    @Provides
    fun provideAlbumDao(database: MusicDatabase): AlbumDao = database.albumDao()

    @Provides
    fun provideArtistDao(database: MusicDatabase): ArtistDao = database.artistDao()

    @Provides
    fun providePlaylistDao(database: MusicDatabase): PlaylistDao = database.playlistDao()
}
