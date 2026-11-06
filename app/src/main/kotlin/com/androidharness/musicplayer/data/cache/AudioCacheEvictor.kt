package com.androidharness.musicplayer.data.cache

import android.content.Context
import android.net.Uri
import com.androidharness.musicplayer.data.local.dao.TrackDao
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class AudioCacheEvictor @Inject constructor(
    @ApplicationContext private val context: Context,
    private val trackDao: TrackDao,
    private val settings: SettingsRepository,
) {
    val downloadsDir: File
        get() = File(context.filesDir, "downloads")

    suspend fun evictNow() = withContext(Dispatchers.IO) {
        val directory = downloadsDir
        if (!directory.exists()) return@withContext

        val cache = settings.settings.value.cache
        val maxBytes = cache.audioCacheMb.toLong() * 1024L * 1024L
        val maxAgeMs = cache.maxAgeDays.toLong() * 24L * 60L * 60L * 1_000L
        val cutoff = System.currentTimeMillis() - maxAgeMs

        val files = directory.listFiles()?.sortedBy { it.lastModified() }.orEmpty()
        var total = files.sumOf { it.length() }

        for (file in files) {
            val expired = file.lastModified() < cutoff
            val overLimit = total > maxBytes
            if (!expired && !overLimit) continue

            trackDao.byLocalUri(Uri.fromFile(file).toString())?.let { entity ->
                trackDao.markNotDownloaded(entity.id)
            }
            total -= file.length()
            file.delete()
        }
    }
}
