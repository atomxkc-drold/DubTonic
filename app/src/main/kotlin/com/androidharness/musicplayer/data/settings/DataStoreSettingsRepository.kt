package com.androidharness.musicplayer.data.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "app_settings",
)

private val SETTINGS_JSON = stringPreferencesKey("settings_json")

@Singleton
class DataStoreSettingsRepository @Inject constructor(
    @ApplicationContext context: Context,
) : SettingsRepository {

    private val dataStore = context.settingsDataStore
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override val settings: StateFlow<AppSettings> = dataStore.data
        .catch { throwable ->
            if (throwable is IOException) emit(emptyPreferences()) else throw throwable
        }
        .map { preferences -> SettingsEngine.decode(preferences[SETTINGS_JSON]) }
        .stateIn(
            scope = scope,
            started = SharingStarted.Eagerly,
            initialValue = AppSettings(),
        )

    override suspend fun update(transform: (AppSettings) -> AppSettings): AppSettings {
        var updated = AppSettings()
        dataStore.edit { preferences ->
            val current = SettingsEngine.decode(preferences[SETTINGS_JSON])
            updated = SettingsEngine.normalize(transform(current))
            preferences[SETTINGS_JSON] = SettingsEngine.encode(updated)
        }
        return updated
    }

    override suspend fun replace(settings: AppSettings): AppSettings =
        update { settings }

    override suspend fun reset(): AppSettings =
        update { AppSettings() }
}
