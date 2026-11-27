package com.androidharness.musicplayer

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.content.ContextCompat
import com.androidharness.musicplayer.domain.repository.SettingsRepository
import com.androidharness.musicplayer.feature.navigation.AppNavHost
import com.androidharness.musicplayer.feature.player.PlaybackConnection
import com.androidharness.musicplayer.ui.chrome.ChromeTheme
import com.androidharness.musicplayer.ui.theme.HarnessMusicTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var playbackConnection: PlaybackConnection

    /** Read at the root so the tint control reaches every glass surface. */
    @Inject
    lateinit var settingsRepository: SettingsRepository

    private val notificationPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { /* Denial only affects the notification; playback still works. */ }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        playbackConnection.connect()
        requestNotificationPermissionIfNeeded()

        setContent {
            val settings by settingsRepository.settings.collectAsState()
            HarnessMusicTheme {
                ChromeTheme(tintIntensity = settings.ui.tintIntensity) {
                    AppNavHost()
                }
            }
        }
    }

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return
        val granted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS,
        ) == PackageManager.PERMISSION_GRANTED
        if (!granted) notificationPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
    }
}
