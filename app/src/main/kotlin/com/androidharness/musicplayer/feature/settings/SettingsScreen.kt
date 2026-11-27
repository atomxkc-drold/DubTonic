package com.androidharness.musicplayer.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.androidharness.musicplayer.ui.motion.motionBlurScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidharness.musicplayer.data.settings.AudioCodec
import com.androidharness.musicplayer.data.settings.CodecDisplayMode
import com.androidharness.musicplayer.data.settings.TimeMachineMotion
import com.androidharness.musicplayer.ui.chrome.BrandMark
import com.androidharness.musicplayer.ui.chrome.ChromeBackdrop
import com.androidharness.musicplayer.ui.chrome.ChromeChip
import com.androidharness.musicplayer.ui.chrome.ChromePanel
import com.androidharness.musicplayer.ui.chrome.ChromeTheme
import com.androidharness.musicplayer.ui.chrome.ChromeTopBar
import com.androidharness.musicplayer.ui.chrome.LocalChrome
import com.androidharness.musicplayer.ui.chrome.SectionLabel
import com.androidharness.musicplayer.ui.chrome.chromeFieldColors

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val settings = state.settings

    ChromeTheme {
        val chrome = LocalChrome.current
        Box(modifier = Modifier.fillMaxSize().background(chrome.base)) {
            ChromeBackdrop(reducedMotion = settings.ui.reducedMotion)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .motionBlurScroll(
                        enabled = settings.ui.scrollMotionBlur && !settings.ui.reducedMotion,
                        intensityPercent = settings.ui.motionBlurIntensity,
                    )
                    .statusBarsPadding(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                item {
                    ChromeTopBar(
                        title = "Settings",
                        kicker = "Subsonic",
                        onBack = onBack,
                        username = state.server.username.takeIf { it.isNotBlank() },
                    )
                }

                item {
                    ChromePanel(contentAlpha = 0.10f) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            BrandMark(size = 52.dp)
                            Column(Modifier.padding(start = 14.dp)) {
                                Text(
                                    text = "DUBTONIC",
                                    color = chrome.ink,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = MaterialTheme.typography.labelLarge.letterSpacing,
                                )
                                Text(
                                    text = "Subsonic player · the wall, the dial and the flow",
                                    color = chrome.muted,
                                    style = MaterialTheme.typography.bodySmall,
                                )
                            }
                        }
                    }
                }

                item { SectionLabel("01", "SERVER", if (state.server.configured) "CONNECTED" else "OFFLINE") }
                item {
                    ServerCard(
                        server = state.server,
                        onConnect = viewModel::connect,
                        onDisconnect = viewModel::disconnect,
                        onDismiss = viewModel::dismissServerMessage,
                    )
                }

                item { SectionLabel("02", "APPEARANCE", "LIVE") }
                item {
                    ChromePanel {
                        SliderRow(
                            title = "Blur radius",
                            value = settings.ui.blurRadiusDp.toFloat(),
                            valueText = "${settings.ui.blurRadiusDp} dp",
                            range = 0f..100f,
                            onChange = viewModel::setBlur,
                        )
                        SliderRow(
                            title = "Saturation",
                            value = settings.ui.saturation,
                            valueText = "%.2f×".format(settings.ui.saturation),
                            range = 0f..2f,
                            onChange = viewModel::setSaturation,
                        )
                        SliderRow(
                            title = "Glass opacity",
                            value = settings.ui.glassAlpha,
                            valueText = "%.2f".format(settings.ui.glassAlpha),
                            range = 0f..0.6f,
                            onChange = viewModel::setGlassAlpha,
                        )
                        SliderRow(
                            title = "Animation scale",
                            value = settings.ui.animationScale,
                            valueText = "%.2f×".format(settings.ui.animationScale),
                            range = 0.25f..2f,
                            onChange = viewModel::setAnimationScale,
                        )
                        SwitchRow(
                            title = "Reduce motion",
                            checked = settings.ui.reducedMotion,
                            onCheckedChange = viewModel::setReducedMotion,
                        )
                        SliderRow(
                            title = "Tint intensity",
                            value = settings.ui.tintIntensity,
                            valueText = "%.0f%%".format(settings.ui.tintIntensity * 100),
                            range = 0f..1f,
                            steps = 19,
                            onChange = viewModel::setTintIntensity,
                        )
                        SliderRow(
                            title = "Backdrop blur",
                            value = settings.ui.backdropBlurRadiusDp.toFloat(),
                            valueText = "${settings.ui.backdropBlurRadiusDp} dp",
                            range = 0f..100f,
                            onChange = { viewModel.setBackdropBlurRadiusDp(it.toInt()) },
                        )
                        SliderRow(
                            title = "Ring spin sensitivity",
                            value = settings.ui.ringSpinSensitivity,
                            valueText = "%.2f×".format(settings.ui.ringSpinSensitivity),
                            range = 0.25f..3f,
                            onChange = viewModel::setRingSpinSensitivity,
                        )
                        Text(
                            text = "TIME MACHINE ART",
                            color = chrome.muted,
                            style = MaterialTheme.typography.labelSmall,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(top = 10.dp, bottom = 6.dp),
                        )
                        Row(
                            modifier = Modifier.horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            listOf(
                                TimeMachineMotion.ORBIT to "Orbit",
                                TimeMachineMotion.OFF to "Off",
                            ).forEach { (mode, label) ->
                                ChromeChip(
                                    label = label,
                                    selected = settings.ui.timeMachineMotion == mode,
                                    onClick = { viewModel.setTimeMachineMotion(mode) },
                                )
                            }
                        }
                        SwitchRow(
                            title = "Scroll motion blur",
                            checked = settings.ui.scrollMotionBlur,
                            onCheckedChange = viewModel::setScrollMotionBlur,
                        )
                        SliderRow(
                            title = "Motion blur strength",
                            value = settings.ui.motionBlurIntensity.toFloat(),
                            valueText = "${settings.ui.motionBlurIntensity}%",
                            range = 0f..100f,
                            steps = 19,
                            onChange = viewModel::setMotionBlurIntensity,
                        )
                    }
                }

                item { SectionLabel("03", "PLAYBACK", "BUFFERING") }
                item {
                    ChromePanel {
                        SliderRow(
                            title = "WiFi buffer ceiling",
                            value = settings.playback.wifiBuffer.targetBufferMb.toFloat(),
                            valueText = "${settings.playback.wifiBuffer.targetBufferMb} MB",
                            range = 8f..512f,
                            onChange = viewModel::setWifiBufferMb,
                        )
                        SliderRow(
                            title = "WiFi minimum buffer",
                            value = settings.playback.wifiBuffer.minBufferMs / 1_000f,
                            valueText = "${settings.playback.wifiBuffer.minBufferMs / 1_000}s",
                            range = 5f..120f,
                            onChange = viewModel::setWifiMinBufferSeconds,
                        )
                        SliderRow(
                            title = "Cellular buffer ceiling",
                            value = settings.playback.cellularBuffer.targetBufferMb.toFloat(),
                            valueText = "${settings.playback.cellularBuffer.targetBufferMb} MB",
                            range = 8f..256f,
                            onChange = viewModel::setCellularBufferMb,
                        )
                        SliderRow(
                            title = "Crossfade",
                            value = settings.playback.crossfadeMs.toFloat(),
                            valueText = "${settings.playback.crossfadeMs} ms",
                            range = 0f..15_000f,
                            steps = 29,
                            onChange = viewModel::setCrossfadeMs,
                        )
                        // Gapless playback is always on in ExoPlayer; no toggle needed.
                        SwitchRow(
                            title = "Skip silence",
                            checked = settings.playback.skipSilence,
                            onCheckedChange = viewModel::setSkipSilence,
                        )
                        SwitchRow(
                            title = "Audio compatibility mode",
                            checked = settings.playback.audioCompatibilityMode,
                            onCheckedChange = viewModel::setAudioCompatibilityMode,
                        )
                        SwitchRow(
                            title = "Pause when headphones unplug",
                            checked = settings.playback.pauseOnHeadphonesDisconnect,
                            onCheckedChange = viewModel::setPauseOnHeadphonesDisconnect,
                        )
                        SwitchRow(
                            title = "Restore last queue on launch",
                            checked = settings.playback.resumeQueueOnStart,
                            onCheckedChange = viewModel::setResumeQueueOnStart,
                        )
                        Text(
                            text = "Buffer changes apply the next time the player starts.",
                            style = MaterialTheme.typography.bodySmall,
                            color = chrome.muted,
                            modifier = Modifier.padding(top = 6.dp),
                        )
                    }
                }

                item { SectionLabel("04", "AUDIO", "DSP") }
                item {
                    ChromePanel {
                        SwitchRow(
                            title = "ReplayGain",
                            checked = settings.playback.replayGain.enabled,
                            onCheckedChange = viewModel::setReplayGainEnabled,
                        )
                        SwitchRow(
                            title = "Parametric EQ",
                            checked = settings.playback.equalizer.enabled,
                            onCheckedChange = viewModel::setEqualizerEnabled,
                        )
                        SliderRow(
                            title = "EQ preamp",
                            value = settings.playback.equalizer.preampDb,
                            valueText = "%+.1f dB".format(settings.playback.equalizer.preampDb),
                            range = -12f..12f,
                            onChange = viewModel::setEqualizerPreamp,
                        )
                    }
                }

                item { SectionLabel("05", "LIBRARY AND CACHE", "${settings.library.syncIntervalMinutes} MIN") }
                item {
                    ChromePanel {
                        SliderRow(
                            title = "Sync interval",
                            value = settings.library.syncIntervalMinutes.toFloat(),
                            valueText = "${settings.library.syncIntervalMinutes} min",
                            range = 15f..1_440f,
                            onChange = viewModel::setSyncIntervalMinutes,
                        )
                        SwitchRow(
                            title = "Sync on WiFi only",
                            checked = settings.library.syncOnWifiOnly,
                            onCheckedChange = viewModel::setSyncOnWifiOnly,
                        )
                        SwitchRow(
                            title = "Privacy & Telemetry Kill-Switch",
                            checked = settings.ui.privacyKillSwitch,
                            onCheckedChange = viewModel::setPrivacyKillSwitch,
                        )
                        SwitchRow(
                            title = "Smart Offline Buffer",
                            checked = settings.ui.smartOfflineBuffer,
                            onCheckedChange = viewModel::setSmartOfflineBuffer,
                        )
                        SliderRow(
                            title = "Image cache",
                            value = settings.cache.imageDiskMb.toFloat(),
                            valueText = "${settings.cache.imageDiskMb} MB",
                            range = 64f..2_048f,
                            onChange = viewModel::setImageCacheMb,
                        )
                        SliderRow(
                            title = "Audio cache",
                            value = settings.cache.audioCacheMb.toFloat(),
                            valueText = "${settings.cache.audioCacheMb} MB",
                            range = 128f..32_768f,
                            onChange = viewModel::setAudioCacheMb,
                        )
                        SliderRow(
                            title = "Cache age",
                            value = settings.cache.maxAgeDays.toFloat(),
                            valueText = "${settings.cache.maxAgeDays} days",
                            range = 1f..365f,
                            onChange = viewModel::setCacheMaxAgeDays,
                        )
                    }
                }

                item { SectionLabel("06", "NETWORK", "STREAMING") }
                item {
                    ChromePanel {
                        SwitchRow(
                            title = "Allow streaming on cellular",
                            checked = settings.network.allowMeteredStreaming,
                            onCheckedChange = viewModel::setAllowMeteredStreaming,
                        )
                        SwitchRow(
                            title = "Prefer downloaded copies",
                            checked = settings.network.preferLocalCopy,
                            onCheckedChange = viewModel::setPreferLocalCopy,
                        )
                        SwitchRow(
                            title = "WiFi streams original",
                            checked = !settings.network.wifiTranscode.enabled,
                            onCheckedChange = viewModel::setWifiOriginal,
                        )
                        Text(
                            text = "CELLULAR CODEC",
                            color = chrome.muted,
                            style = MaterialTheme.typography.labelSmall,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(top = 10.dp, bottom = 6.dp),
                        )
                        Row(
                            modifier = Modifier.horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            listOf(
                                AudioCodec.ORIGINAL to "Original",
                                AudioCodec.OPUS to "Opus",
                                AudioCodec.MP3 to "MP3",
                                AudioCodec.AAC to "AAC",
                                AudioCodec.FLAC to "FLAC",
                            ).forEach { (codec, label) ->
                                ChromeChip(
                                    label = label,
                                    selected = settings.network.cellularTranscode.codec == codec,
                                    onClick = { viewModel.setCellularCodec(codec) },
                                )
                            }
                        }
                        val isOriginal = settings.network.cellularTranscode.codec == AudioCodec.ORIGINAL
                        SliderRow(
                            title = "Cellular bitrate",
                            value = settings.network.cellularTranscode.maxBitrateKbps.toFloat(),
                            valueText = if (isOriginal) "Lossless / Source" else "${settings.network.cellularTranscode.maxBitrateKbps} kbps",
                            range = 64f..320f,
                            onChange = viewModel::setCellularBitrate,
                            enabled = !isOriginal,
                        )
                    }
                }

                item { SectionLabel("07", "CODEC & QUALITY BADGE", "DISPLAY") }
                item {
                    ChromePanel {
                        Text(
                            text = "BADGE DISPLAY STYLE",
                            color = chrome.muted,
                            style = MaterialTheme.typography.labelSmall,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(bottom = 6.dp),
                        )
                        Row(
                            modifier = Modifier.horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            listOf(
                                CodecDisplayMode.RAW_CONTAINER to "Raw container/bitrate",
                                CodecDisplayMode.SIMPLIFIED to "LOSSLESS / LOSSY",
                                CodecDisplayMode.HIGHLIGHTED to "Highlight transcoded",
                            ).forEach { (mode, label) ->
                                ChromeChip(
                                    label = label,
                                    selected = settings.ui.codecDisplayMode == mode,
                                    onClick = { viewModel.setCodecDisplayMode(mode) },
                                )
                            }
                        }
                    }
                }

                item {
                    Text(
                        text = "Changes save as you move. Nothing here leaves the phone.",
                        color = chrome.muted,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 12.dp, start = 4.dp),
                    )
                }

                item { Spacer(Modifier.height(32.dp)) }
            }
        }
    }
}

@Composable
private fun ServerCard(
    server: ServerUiState,
    onConnect: (String, String, String) -> Unit,
    onDisconnect: () -> Unit,
    onDismiss: () -> Unit,
) {
    val chrome = LocalChrome.current
    var baseUrl by remember(server.baseUrl) { mutableStateOf(server.baseUrl) }
    var username by remember(server.username) { mutableStateOf(server.username) }
    var password by remember { mutableStateOf("") }

    ChromePanel(shape = RoundedCornerShape(24.dp)) {
        OutlinedTextField(
            value = baseUrl,
            onValueChange = { baseUrl = it },
            label = { Text("Server URL") },
            placeholder = { Text("https://music.example.com") },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = chromeFieldColors(),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = chromeFieldColors(),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            visualTransformation = PasswordVisualTransformation(),
            colors = chromeFieldColors(),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(12.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                onClick = {
                    onConnect(baseUrl, username, password)
                    password = ""
                },
                enabled = !server.busy && baseUrl.isNotBlank() && username.isNotBlank(),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = chrome.accent,
                    contentColor = chrome.accentInk,
                ),
            ) {
                Text(if (server.configured) "Reconnect" else "Connect")
            }
            if (server.configured) {
                OutlinedButton(
                    onClick = onDisconnect,
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = chrome.ink),
                ) {
                    Text("Disconnect")
                }
            }
            if (server.busy) {
                CircularProgressIndicator(
                    modifier = Modifier.height(20.dp),
                    color = chrome.accent,
                    strokeWidth = 2.dp,
                )
            }
        }
        server.message?.let { message ->
            Spacer(Modifier.height(10.dp))
            Text(
                text = message,
                style = MaterialTheme.typography.bodySmall,
                color = chrome.accent,
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedButton(
                onClick = onDismiss,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = chrome.ink),
            ) {
                Text("Dismiss")
            }
        }
    }
}

@Composable
private fun SliderRow(
    title: String,
    value: Float,
    valueText: String,
    range: ClosedFloatingPointRange<Float>,
    steps: Int = 0,
    onChange: (Float) -> Unit,
    enabled: Boolean = true,
) {
    val chrome = LocalChrome.current
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(title, color = chrome.ink, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.weight(1f))
            Text(
                valueText,
                color = chrome.accent,
                style = MaterialTheme.typography.labelSmall,
                fontFamily = FontFamily.Monospace,
            )
        }
        Slider(
            value = value.coerceIn(range),
            onValueChange = onChange,
            valueRange = range,
            steps = steps,
            enabled = enabled,
            colors = SliderDefaults.colors(
                thumbColor = chrome.accent,
                activeTrackColor = chrome.accent,
                inactiveTrackColor = chrome.ink.copy(alpha = 0.16f),
                activeTickColor = chrome.accentInk.copy(alpha = 0.4f),
                inactiveTickColor = chrome.ink.copy(alpha = 0.25f),
            ),
        )
    }
}

@Composable
private fun SwitchRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    val chrome = LocalChrome.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            title,
            color = chrome.ink,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f),
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = chrome.accentInk,
                checkedTrackColor = chrome.accent,
                checkedBorderColor = chrome.accent,
                uncheckedThumbColor = chrome.muted,
                uncheckedTrackColor = chrome.ink.copy(alpha = 0.12f),
                uncheckedBorderColor = chrome.ink.copy(alpha = 0.22f),
            ),
        )
    }
}
