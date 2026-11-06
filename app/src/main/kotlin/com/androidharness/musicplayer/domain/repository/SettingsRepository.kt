package com.androidharness.musicplayer.domain.repository

import com.androidharness.musicplayer.data.settings.AppSettings
import kotlinx.coroutines.flow.StateFlow

interface SettingsRepository {
    val settings: StateFlow<AppSettings>

    suspend fun update(transform: (AppSettings) -> AppSettings): AppSettings

    suspend fun replace(settings: AppSettings): AppSettings

    suspend fun reset(): AppSettings
}
