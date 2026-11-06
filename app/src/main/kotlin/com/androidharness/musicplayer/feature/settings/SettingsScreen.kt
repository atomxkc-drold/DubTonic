package com.androidharness.musicplayer.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.androidharness.musicplayer.data.settings.AudioCodec
import com.androidharness.musicplayer.ui.glass.GlassSurface
import kotlin.math.roundToInt

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val settings = state.settings

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.surface,
                    )
                )
            )
            .statusBarsPadding(),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
                Text("Settings", style = MaterialTheme.typography.headlineSmall)
            }
        }

        item { SectionTitle("Server") }
        item {
            ServerCard(
                server = state.server,
                onConnect = viewModel::connect,
                onDisconnect = viewModel::disconnect,
                onDismiss = viewModel::dismissServerMessage,
            )
        }

        item { SectionTitle("Appearance") }
        item {
            GlassSurface(alpha = 0.06f, borderAlpha = 0.12f) {
                Column(Modifier.padding(16.dp)) {
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
                }
            }
        }

        item { SectionTitle("Buffering") }
        item {
            GlassSurface(alpha = 0.06f, borderAlpha = 0.12f) {
                Column(Modifier.padding(16.dp)) {
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
                    SwitchRow(
                        title = "Gapless playback",
                        checked = settings.playback.gaplessEnabled,
                        onCheckedChange = viewModel::setGapless,
                    )
                    Text(
                        "Buffer changes apply the next time the player starts.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        item { SectionTitle("Audio") }
        item {
            GlassSurface(alpha = 0.06f, borderAlpha = 0.12f) {
                Column(Modifier.padding(16.dp)) {
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
        }

        item { SectionTitle("Library and cache") }
        item {
            GlassSurface(alpha = 0.06f, borderAlpha = 0.12f) {
                Column(Modifier.padding(16.dp)) {
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
        }

        item { SectionTitle("Network") }
        item {
            GlassSurface(alpha = 0.06f, borderAlpha = 0.12f) {
                Column(Modifier.padding(16.dp)) {
                    SwitchRow(
                        title = "Allow streaming on cellular",
                        checked = settings.network.allowMeteredStreaming,
                        onCheckedChange = viewModel::setAllowMeteredStreaming,
                    )
                    SwitchRow(
                        title = "WiFi streams original",
                        checked = !settings.network.wifiTranscode.enabled,
                        onCheckedChange = viewModel::setWifiOriginal,
                    )
                    Text(
                        "Cellular codec",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 8.dp),
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf(
                            AudioCodec.ORIGINAL to "Original",
                            AudioCodec.OPUS to "Opus",
                            AudioCodec.MP3 to "MP3",
                            AudioCodec.AAC to "AAC",
                            AudioCodec.FLAC to "FLAC",
                        ).forEach { (codec, label) ->
                            FilterChip(
                                selected = settings.network.cellularTranscode.codec == codec,
                                onClick = { viewModel.setCellularCodec(codec) },
                                label = { Text(label) },
                            )
                        }
                    }
                    SliderRow(
                        title = "Cellular bitrate",
                        value = settings.network.cellularTranscode.maxBitrateKbps.toFloat(),
                        valueText = "${settings.network.cellularTranscode.maxBitrateKbps} kbps",
                        range = 64f..320f,
                        onChange = viewModel::setCellularBitrate,
                    )
                }
            }
        }

        item { Spacer(Modifier.height(32.dp)) }
    }
}

@Composable
private fun ServerCard(
    server: ServerUiState,
    onConnect: (String, String, String) -> Unit,
    onDisconnect: () -> Unit,
    onDismiss: () -> Unit,
) {
    var baseUrl by remember(server.baseUrl) { mutableStateOf(server.baseUrl) }
    var username by remember(server.username) { mutableStateOf(server.username) }
    var password by remember { mutableStateOf("") }

    GlassSurface(alpha = 0.06f, borderAlpha = 0.12f, shape = RoundedCornerShape(24.dp)) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = baseUrl,
                onValueChange = { baseUrl = it },
                label = { Text("Server URL") },
                placeholder = { Text("https://music.example.com") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = { onConnect(baseUrl, username, password) },
                    enabled = !server.busy && baseUrl.isNotBlank() && username.isNotBlank(),
                ) {
                    Text(if (server.configured) "Reconnect" else "Connect")
                }
                if (server.configured) {
                    OutlinedButton(onClick = onDisconnect) { Text("Disconnect") }
                }
                if (server.busy) {
                    CircularProgressIndicator(modifier = Modifier.height(20.dp))
                }
            }
            server.message?.let { message ->
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedButton(onClick = onDismiss) { Text("Dismiss") }
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 12.dp),
    )
}

@Composable
private fun SliderRow(
    title: String,
    value: Float,
    valueText: String,
    range: ClosedFloatingPointRange<Float>,
    steps: Int = 0,
    onChange: (Float) -> Unit,
) {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(title, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.weight(1f))
            Text(
                valueText,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Slider(
            value = value.coerceIn(range),
            onValueChange = onChange,
            valueRange = range,
            steps = steps,
        )
    }
}

@Composable
private fun SwitchRow(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(title, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Suppress("unused")
private fun Float.asPercent(): String = "${(this * 100).roundToInt()}%"
