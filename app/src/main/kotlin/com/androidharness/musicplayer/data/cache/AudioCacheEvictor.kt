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

/**
 * Keeps the offline download folder bounded and consistent with the database.
 *
 * Eviction is LRU by file modification time, which [touch] bumps whenever a download is
 * played. [reconcile] removes partial files left by interrupted downloads and clears DB
 * rows whose file disappeared, so the UI never offers an unplayable "downloaded" track.
 */
@Singleton
class AudioCacheEvictor @Inject constructor(
    @ApplicationContext private val context: Context,
    private val trackDao: TrackDao,
    private val settings: SettingsRepository,
) {
    val downloadsDir: File
        get() = File(context.filesDir, "downloads")

    /** Marks a download as recently used so LRU keeps it. */
    suspend fun touch(localUri: String) = withContext(Dispatchers.IO) {
        runCatching {
            localUri.let(Uri::parse).path?.let(::File)?.setLastModified(System.currentTimeMillis())
        }
        Unit
    }

    /**
     * Startup/periodic reconciliation: deletes abandoned `.part` files and drops download
     * flags whose file is gone. Cheap enough to run on every sync.
     */
    suspend fun reconcile() = withContext(Dispatchers.IO) {
        val directory = downloadsDir
        if (!directory.exists()) return@withContext

        directory.listFiles { file -> file.name.endsWith(".part") }?.forEach { it.delete() }

        for (entity in trackDao.byLocalUriIsNotNull()) {
            val path = entity.localUri?.let(Uri::parse)?.path ?: continue
            if (!File(path).exists()) trackDao.markNotDownloaded(entity.id)
        }
    }

    suspend fun evictNow() = withContext(Dispatchers.IO) {
        val directory = downloadsDir
        if (!directory.exists()) return@withContext

        val cache = settings.settings.value.cache
        val maxBytes = cache.audioCacheMb.toLong() * 1024L * 1024L
        val maxAgeMs = cache.maxAgeDays.toLong() * 24L * 60L * 60L * 1_000L
        val cutoff = System.currentTimeMillis() - maxAgeMs

        // Oldest access first: LRU order is the eviction order.
        val files = directory.listFiles()
            ?.filter { !it.name.endsWith(".part") }
            ?.sortedBy { it.lastModified() }
            .orEmpty()
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
