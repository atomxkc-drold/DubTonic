package com.androidharness.musicplayer.data.cache

import com.androidharness.musicplayer.core.model.NetworkType
import com.androidharness.musicplayer.core.model.Track
import com.androidharness.musicplayer.data.connectivity.ConnectivityMonitor
import com.androidharness.musicplayer.domain.repository.MusicRepository
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.first

/**
 * Smart Offline Buffer: keeps a small set of tracks pinned on disk so playback survives a
 * cellular drop or a dead zone.
 *
 * It pins favorites first, then whatever was played most recently, and only on an unmetered
 * network. Nothing is pinned unless the user turned the option on, and already-downloaded
 * tracks are skipped so the buffer never re-fetches what is local.
 */
@Singleton
class SmartOfflineBuffer @Inject constructor(
    private val repository: MusicRepository,
    private val settings: SettingsRepository,
    private val connectivity: ConnectivityMonitor,
) {

    suspend fun pinIfEnabled() {
        if (!settings.settings.value.ui.smartOfflineBuffer) return
        if (connectivity.current() == NetworkType.CELLULAR) return

        val favorites = repository.observeTracks().first().filter { it.isFavorite }
        val recent = repository.observeRecentlyPlayed(PIN_LIMIT).first()
        val candidates: List<Track> = (favorites + recent)
            .distinctBy { it.id }
            .filterNot { it.isDownloaded }
            .take(PIN_LIMIT)

        candidates.forEach { track -> repository.downloadTrack(track.id) }
    }

    companion object {
        /** Upper bound on pinned tracks, so the buffer cannot fill the device. */
        const val PIN_LIMIT: Int = 25
    }
}
