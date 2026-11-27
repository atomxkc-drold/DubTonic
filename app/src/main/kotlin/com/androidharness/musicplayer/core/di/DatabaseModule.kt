package com.androidharness.musicplayer.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.androidharness.musicplayer.data.local.MusicDatabase
import com.androidharness.musicplayer.data.local.dao.AlbumDao
import com.androidharness.musicplayer.data.local.dao.ArtistDao
import com.androidharness.musicplayer.data.local.dao.PlayStatsDao
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

    /** v2 adds per-track star rating; keeps downloads, favorites and playlists. */
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE tracks ADD COLUMN rating INTEGER NOT NULL DEFAULT 0")
        }
    }

    /** v3 indexes the download columns used by eviction and offline lookups. */
    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("CREATE INDEX IF NOT EXISTS index_tracks_localUri ON tracks (localUri)")
            db.execSQL(
                "CREATE INDEX IF NOT EXISTS index_tracks_isDownloaded ON tracks (isDownloaded)"
            )
        }
    }

    /** v4 adds the play history table that feeds History, Top 10 and Discover. */
    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(
                "CREATE TABLE IF NOT EXISTS `play_stats` (" +
                    "`trackId` TEXT NOT NULL, " +
                    "`playCount` INTEGER NOT NULL, " +
                    "`lastPlayedAtMs` INTEGER NOT NULL, " +
                    "PRIMARY KEY(`trackId`))"
            )
        }
    }

    /** v5 caches the server play count used by the User Plays Time Machine lens. */
    val MIGRATION_4_5 = object : Migration(4, 5) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE tracks ADD COLUMN serverPlayCount INTEGER NOT NULL DEFAULT 0")
        }
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): MusicDatabase = Room.databaseBuilder(
        context,
        MusicDatabase::class.java,
        MusicDatabase.NAME,
    )
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5)
        .build()

    @Provides
    fun provideTrackDao(database: MusicDatabase): TrackDao = database.trackDao()

    @Provides
    fun provideAlbumDao(database: MusicDatabase): AlbumDao = database.albumDao()

    @Provides
    fun provideArtistDao(database: MusicDatabase): ArtistDao = database.artistDao()

    @Provides
    fun providePlaylistDao(database: MusicDatabase): PlaylistDao = database.playlistDao()

    @Provides
    fun providePlayStatsDao(database: MusicDatabase): PlayStatsDao = database.playStatsDao()
}
