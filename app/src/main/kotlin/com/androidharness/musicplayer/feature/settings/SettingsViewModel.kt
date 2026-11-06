package com.androidharness.musicplayer.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidharness.musicplayer.data.remote.SubsonicApi
import com.androidharness.musicplayer.data.remote.SubsonicCredentialsStore
import com.androidharness.musicplayer.data.remote.SubsonicUrls
import com.androidharness.musicplayer.data.remote.validated
import com.androidharness.musicplayer.data.settings.AppSettings
import com.androidharness.musicplayer.data.settings.AudioCodec
import com.androidharness.musicplayer.data.settings.TranscodeProfile
import com.androidharness.musicplayer.data.sync.SyncScheduler
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ServerUiState(
    val baseUrl: String = "",
    val username: String = "",
    val configured: Boolean = false,
    val busy: Boolean = false,
    val message: String? = null,
)

data class SettingsUiState(
    val settings: AppSettings = AppSettings(),
    val server: ServerUiState = ServerUiState(),
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository,
    private val credentials: SubsonicCredentialsStore,
    private val api: SubsonicApi,
    private val urls: SubsonicUrls,
    private val syncScheduler: SyncScheduler,
) : ViewModel() {

    private val server = MutableStateFlow(
        credentials.current().let { session ->
            ServerUiState(
                baseUrl = session?.baseUrl.orEmpty(),
                username = session?.username.orEmpty(),
                configured = session != null,
            )
        }
    )

    val state: StateFlow<SettingsUiState> = combine(repository.settings, server) { settings, server ->
        SettingsUiState(settings = settings, server = server)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SettingsUiState(),
    )

    fun connect(baseUrl: String, username: String, password: String) {
        if (server.value.busy) return
        viewModelScope.launch {
            server.update { it.copy(busy = true, message = null) }

            val saved = runCatching {
                credentials.save(baseUrl, username, password)
            }.getOrNull()

            if (saved == null) {
                server.update {
                    it.copy(busy = false, configured = false, message = "Enter a valid server URL")
                }
                return@launch
            }

            val result = runCatching {
                api.ping(urls.rest("ping")).validated()
            }

            result.onSuccess {
                server.value = ServerUiState(
                    baseUrl = saved.baseUrl,
                    username = saved.username,
                    configured = true,
                    busy = false,
                    message = "Connected",
                )
            }.onFailure { error ->
                credentials.clear()
                server.value = ServerUiState(
                    baseUrl = saved.baseUrl,
                    username = saved.username,
                    configured = false,
                    busy = false,
                    message = error.message ?: "Login failed",
                )
            }
        }
    }

    fun disconnect() {
        credentials.clear()
        server.value = ServerUiState(message = "Disconnected")
    }

    fun dismissServerMessage() {
        server.update { it.copy(message = null) }
    }

    fun setBlur(value: Float) = update {
        it.copy(ui = it.ui.copy(blurRadiusDp = value.roundToInt()))
    }

    fun setSaturation(value: Float) = update {
        it.copy(ui = it.ui.copy(saturation = value))
    }

    fun setAnimationScale(value: Float) = update {
        it.copy(ui = it.ui.copy(animationScale = value))
    }

    fun setGlassAlpha(value: Float) = update {
        it.copy(ui = it.ui.copy(glassAlpha = value))
    }

    fun setWifiBufferMb(value: Float) = update {
        it.copy(playback = it.playback.copy(
            wifiBuffer = it.playback.wifiBuffer.copy(targetBufferMb = value.roundToInt())
        ))
    }

    fun setCellularBufferMb(value: Float) = update {
        it.copy(playback = it.playback.copy(
            cellularBuffer = it.playback.cellularBuffer.copy(targetBufferMb = value.roundToInt())
        ))
    }

    fun setWifiMinBufferSeconds(value: Float) = update {
        it.copy(playback = it.playback.copy(
            wifiBuffer = it.playback.wifiBuffer.copy(minBufferMs = (value * 1_000).toLong())
        ))
    }

    fun setCrossfadeMs(value: Float) = update {
        it.copy(playback = it.playback.copy(crossfadeMs = value.roundToInt()))
    }

    fun setGapless(enabled: Boolean) = update {
        it.copy(playback = it.playback.copy(gaplessEnabled = enabled))
    }

    fun setReplayGainEnabled(enabled: Boolean) = update {
        it.copy(playback = it.playback.copy(replayGain = it.playback.replayGain.copy(enabled = enabled)))
    }

    fun setEqualizerEnabled(enabled: Boolean) = update {
        it.copy(playback = it.playback.copy(equalizer = it.playback.equalizer.copy(enabled = enabled)))
    }

    fun setEqualizerPreamp(value: Float) = update {
        it.copy(playback = it.playback.copy(equalizer = it.playback.equalizer.copy(preampDb = value)))
    }

    fun setSyncIntervalMinutes(value: Float) {
        viewModelScope.launch {
            repository.update {
                it.copy(library = it.library.copy(syncIntervalMinutes = value.roundToInt()))
            }
            syncScheduler.schedule()
        }
    }

    fun setSyncOnWifiOnly(enabled: Boolean) {
        viewModelScope.launch {
            repository.update { it.copy(library = it.library.copy(syncOnWifiOnly = enabled)) }
            syncScheduler.schedule()
        }
    }

    fun setImageCacheMb(value: Float) = update {
        it.copy(cache = it.cache.copy(imageDiskMb = value.roundToInt()))
    }

    fun setAudioCacheMb(value: Float) = update {
        it.copy(cache = it.cache.copy(audioCacheMb = value.roundToInt()))
    }

    fun setCacheMaxAgeDays(value: Float) = update {
        it.copy(cache = it.cache.copy(maxAgeDays = value.roundToInt()))
    }

    fun setAllowMeteredStreaming(enabled: Boolean) = update {
        it.copy(network = it.network.copy(allowMeteredStreaming = enabled))
    }

    fun setCellularCodec(codec: AudioCodec) = update {
        it.copy(network = it.network.copy(
            cellularTranscode = it.network.cellularTranscode.copy(
                enabled = codec != AudioCodec.ORIGINAL,
                codec = codec,
                maxBitrateKbps = if (codec == AudioCodec.ORIGINAL) {
                    0
                } else {
                    it.network.cellularTranscode.maxBitrateKbps.coerceAtLeast(96)
                },
            )
        ))
    }

    fun setCellularBitrate(value: Float) = update {
        it.copy(network = it.network.copy(
            cellularTranscode = it.network.cellularTranscode.copy(maxBitrateKbps = value.roundToInt())
        ))
    }

    fun setWifiOriginal(enabled: Boolean) = update {
        it.copy(network = it.network.copy(
            wifiTranscode = if (enabled) {
                TranscodeProfile()
            } else {
                TranscodeProfile(enabled = true, codec = AudioCodec.FLAC, maxBitrateKbps = 1_411)
            }
        ))
    }

    private fun update(transform: (AppSettings) -> AppSettings) {
        viewModelScope.launch {
            repository.update(transform)
        }
    }
}
