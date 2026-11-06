package com.androidharness.musicplayer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.androidharness.musicplayer.data.local.dao.AlbumDao
import com.androidharness.musicplayer.data.local.dao.ArtistDao
import com.androidharness.musicplayer.data.local.dao.PlaylistDao
import com.androidharness.musicplayer.data.local.dao.TrackDao
import com.androidharness.musicplayer.data.local.entity.AlbumEntity
import com.androidharness.musicplayer.data.local.entity.ArtistEntity
import com.androidharness.musicplayer.data.local.entity.PlaylistEntity
import com.androidharness.musicplayer.data.local.entity.PlaylistEntryEntity
import com.androidharness.musicplayer.data.local.entity.TrackEntity
import com.androidharness.musicplayer.data.local.entity.TrackFtsEntity

@Database(
    entities = [
        TrackEntity::class,
        TrackFtsEntity::class,
        AlbumEntity::class,
        ArtistEntity::class,
        PlaylistEntity::class,
        PlaylistEntryEntity::class,
    ],
    version = MusicDatabase.VERSION,
    exportSchema = true,
)
abstract class MusicDatabase : RoomDatabase() {

    abstract fun trackDao(): TrackDao

    abstract fun albumDao(): AlbumDao

    abstract fun artistDao(): ArtistDao

    abstract fun playlistDao(): PlaylistDao

    companion object {
        const val VERSION: Int = 1
        const val NAME: String = "harness_music.db"
    }
}
