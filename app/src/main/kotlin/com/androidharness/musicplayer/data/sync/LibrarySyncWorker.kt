package com.androidharness.musicplayer.data.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.androidharness.musicplayer.core.result.AppError
import com.androidharness.musicplayer.core.result.AppResult
import com.androidharness.musicplayer.data.cache.AudioCacheEvictor
import com.androidharness.musicplayer.data.cache.SmartOfflineBuffer
import com.androidharness.musicplayer.domain.repository.MusicRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class LibrarySyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repository: MusicRepository,
    private val evictor: AudioCacheEvictor,
    private val smartOfflineBuffer: SmartOfflineBuffer,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        // Housekeeping first: it is local, cheap and must happen even when the sync fails.
        runCatching {
            evictor.reconcile()
            evictor.evictNow()
        }

        runCatching { smartOfflineBuffer.pinIfEnabled() }

        return when (val result = repository.syncLibrary()) {
            is AppResult.Success -> Result.success()
            is AppResult.Failure -> when (val error = result.error) {
                is AppError.Subsonic -> Result.failure()
                is AppError.NotConfigured -> Result.failure()
                is AppError.Http ->
                    if (error.code >= 500 && runAttemptCount < MAX_ATTEMPTS) {
                        Result.retry()
                    } else {
                        Result.failure()
                    }
                else ->
                    if (runAttemptCount < MAX_ATTEMPTS) Result.retry() else Result.failure()
            }
        }
    }

    private companion object {
        const val MAX_ATTEMPTS = 3
    }
}
