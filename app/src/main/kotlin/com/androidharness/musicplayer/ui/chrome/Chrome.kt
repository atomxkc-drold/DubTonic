package com.androidharness.musicplayer.ui.chrome

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.androidharness.musicplayer.R
import com.androidharness.musicplayer.feature.home.HomeBackdrop
import com.androidharness.musicplayer.feature.home.auroraFor
import com.androidharness.musicplayer.feature.home.dayMood
import java.time.LocalTime

/** Colour identity shared by Settings and Library so they sit with the home wall. */
data class ChromePalette(
    val ink: Color,
    val muted: Color,
    val base: Color,
    val accent: Color,
    val accentInk: Color,
    val dark: Boolean,
)

private val DarkChrome = ChromePalette(
    ink = Color(0xFFF3F5E7),
    muted = Color(0xFFAEB6A4),
    base = Color(0xFF080A10),
    accent = Color(0xFFC9E86B),
    accentInk = Color(0xFF10160A),
    dark = true,
)

private val LightChrome = ChromePalette(
    ink = Color(0xFF11150E),
    muted = Color(0xFF5C6457),
    base = Color(0xFFF4F5EA),
    accent = Color(0xFF2C39C8),
    accentInk = Color(0xFFF7F8F2),
    dark = false,
)

val LocalChrome = staticCompositionLocalOf { DarkChrome }

/** Brand blue and lime, lifted from the launcher icon. */
val BrandBlue: Color = Color(0xFF1A22CC)
val BrandLime: Color = Color(0xFFC9E86B)
val BrandYellow: Color = Color(0xFFFFC21A)
val PillInk: Color = Color(0xFF111111)

@Composable
fun ChromeTheme(content: @Composable () -> Unit) {
    val dark = isSystemInDarkTheme()
    CompositionLocalProvider(
        LocalChrome provides (if (dark) DarkChrome else LightChrome),
        content = content,
    )
}

/** Aurora wall used behind every screen that is not the home hero. */
@Composable
fun ChromeBackdrop(
    modifier: Modifier = Modifier,
    artwork: Uri? = null,
    primary: Color? = null,
    secondary: Color? = null,
    parallaxPx: Float = 0f,
    blurRadiusDp: Int = 44,
    reducedMotion: Boolean = false,
) {
    val chrome = LocalChrome.current
    val mood = remember { dayMood(LocalTime.now().hour) }
    val primarySeed = primary ?: if (chrome.dark) Color(0xFF2A32B0) else Color(0xFF7F89E8)
    val secondarySeed = secondary ?: if (chrome.dark) Color(0xFF7C8A3C) else Color(0xFFC9D89A)
    val spec = remember(primarySeed, secondarySeed, mood, reducedMotion) {
        auroraFor(primarySeed, secondarySeed, mood, reducedMotion)
    }
    HomeBackdrop(
        spec = spec,
        artwork = artwork,
        dark = chrome.dark,
        blurRadiusDp = blurRadiusDp,
        parallaxPx = parallaxPx,
        modifier = modifier,
    )
}

/** Mono section header with an index, matching the home shelves. */
@Composable
fun SectionLabel(
    index: String,
    title: String,
    hint: String? = null,
    accent: Color = LocalChrome.current.accent,
    muted: Color = LocalChrome.current.muted,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = index,
            color = accent,
            style = MaterialTheme.typography.labelSmall,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = title,
            color = muted,
            style = MaterialTheme.typography.labelSmall,
            fontFamily = FontFamily.Monospace,
            letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing,
        )
        Spacer(Modifier.weight(1f))
        if (hint != null) {
            Text(
                text = hint,
                color = muted.copy(alpha = 0.75f),
                style = MaterialTheme.typography.labelSmall,
                fontFamily = FontFamily.Monospace,
            )
        }
    }
}

/** Glass card used for settings groups, server form and library rows. */
@Composable
fun ChromePanel(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(24.dp),
    contentAlpha: Float = 0.08f,
    content: @Composable ColumnScope.() -> Unit,
) {
    val chrome = LocalChrome.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(chrome.ink.copy(alpha = contentAlpha))
            .border(1.dp, chrome.ink.copy(alpha = 0.13f), shape)
            .padding(16.dp),
        content = content,
    )
}

@Composable
fun ChromeTopBar(
    title: String,
    kicker: String,
    onBack: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    onSettings: (() -> Unit)? = null,
    username: String? = null,
    action: (@Composable () -> Unit)? = null,
) {
    val chrome = LocalChrome.current
    Row(
        modifier = modifier.fillMaxWidth().padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (onBack != null) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(chrome.ink.copy(alpha = 0.08f))
                    .border(1.dp, chrome.ink.copy(alpha = 0.14f), CircleShape)
                    .clickable(onClick = onBack),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = chrome.ink,
                    modifier = Modifier.size(20.dp),
                )
            }
            Spacer(Modifier.width(12.dp))
        }
        Column(Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                SubsonicMark(14.dp)
                Text(
                    text = kicker.uppercase(),
                    color = chrome.muted,
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false),
                )
                if (username != null) UsernamePill(username)
            }
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    brush = Brush.linearGradient(
                        listOf(chrome.ink, lerp(chrome.ink, chrome.accent, 0.7f)),
                    ),
                ),
            )
        }
        if (onSettings != null) {
            IconButton(onClick = onSettings) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = chrome.ink.copy(alpha = 0.75f),
                )
            }
        }
        action?.invoke()
    }
}

@Composable
fun chromeFieldColors(): TextFieldColors {
    val chrome = LocalChrome.current
    return OutlinedTextFieldDefaults.colors(
        focusedContainerColor = chrome.ink.copy(alpha = 0.07f),
        unfocusedContainerColor = chrome.ink.copy(alpha = 0.05f),
        focusedBorderColor = chrome.accent.copy(alpha = 0.65f),
        unfocusedBorderColor = chrome.ink.copy(alpha = 0.12f),
        focusedTextColor = chrome.ink,
        unfocusedTextColor = chrome.ink,
        cursorColor = chrome.accent,
        focusedLabelColor = chrome.accent,
        unfocusedLabelColor = chrome.muted,
        focusedPlaceholderColor = chrome.muted.copy(alpha = 0.7f),
        unfocusedPlaceholderColor = chrome.muted.copy(alpha = 0.7f),
    )
}

@Composable
fun ChromeChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val chrome = LocalChrome.current
    val shape = RoundedCornerShape(14.dp)
    Box(
        modifier = modifier
            .height(44.dp)
            .clip(shape)
            .background(if (selected) chrome.accent else chrome.ink.copy(alpha = 0.07f))
            .border(
                BorderStroke(1.dp, if (selected) Color.Transparent else chrome.ink.copy(alpha = 0.14f)),
                shape,
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label,
            color = if (selected) chrome.accentInk else chrome.ink,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
        )
    }
}

/** Launcher field colours: amber radial, matching ic_launcher_background. */
private val IconFieldLight = Color(0xFFFFD470)
private val IconFieldMid = Color(0xFFFBAF2B)
private val IconFieldDeep = Color(0xFFE88A00)

/**
 * Small brand tile: amber field, azure beamed note, ink waveform.
 * Layer for layer the same mark as the launcher icon, so the header and Settings
 * crest never drift from what sits on the home screen.
 */
@Composable
fun BrandMark(size: Dp = 44.dp, modifier: Modifier = Modifier) {
    val field = with(LocalDensity.current) {
        val extent = size.toPx()
        Brush.radialGradient(
            colors = listOf(IconFieldLight, IconFieldMid, IconFieldDeep),
            center = Offset(extent * 0.5f, extent * 0.32f),
            radius = extent * 0.82f,
        )
    }
    Box(
        modifier = modifier
            .size(size)
            .clip(RoundedCornerShape(size * 0.28f))
            .background(field),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

/** The Subsonic submarine, drawn from the official mark. */
@Composable
fun SubsonicMark(size: Dp, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.ic_subsonic),
        contentDescription = "Subsonic",
        modifier = modifier.size(size),
    )
}

/**
 * Subsonic mark in a soft bubble so it stays legible on artwork, on glass and on the
 * light theme. Pass a darker scrim when it sits on top of a cover.
 */
@Composable
fun SubsonicBadge(
    size: Dp = 34.dp,
    modifier: Modifier = Modifier,
    scrim: Color? = null,
    markScale: Float = 0.78f,
) {
    val chrome = LocalChrome.current
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(scrim ?: chrome.ink.copy(alpha = if (chrome.dark) 0.10f else 0.07f))
            .border(1.dp, chrome.ink.copy(alpha = 0.12f), CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        SubsonicMark(size * markScale)
    }
}

/** Yellow pill with black text, used for the Subsonic user name. */
@Composable
fun UsernamePill(
    name: String,
    modifier: Modifier = Modifier,
    maxWidth: Dp = 108.dp,
) {
    Box(
        modifier = modifier
            .widthIn(max = maxWidth)
            .clip(RoundedCornerShape(50))
            .background(BrandYellow)
            .padding(horizontal = 8.dp, vertical = 3.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = name.uppercase(),
            color = PillInk,
            style = MaterialTheme.typography.labelSmall,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/** Initial-letter stand-in for a profile picture. */
@Composable
fun IdentityAvatar(
    name: String,
    modifier: Modifier = Modifier,
    size: Dp = 38.dp,
) {
    val chrome = LocalChrome.current
    val hue = ((name.hashCode() % 360) + 360) % 360
    val start = Color.hsl(hue.toFloat(), 0.58f, if (chrome.dark) 0.58f else 0.46f)
    val end = Color.hsl(((hue + 42) % 360).toFloat(), 0.62f, if (chrome.dark) 0.40f else 0.32f)
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(Brush.linearGradient(listOf(start, end)))
            .border(1.dp, Color.White.copy(alpha = 0.28f), CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = name.trim().take(1).uppercase().ifEmpty { "?" },
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}

/**
 * Full brand cluster: optional avatar, the Subsonic mark, the DubTonic wordmark and
 * "Subsonic" with the signed-in user in a yellow pill.
 */
@Composable
fun SubsonicBrandHeader(
    username: String?,
    modifier: Modifier = Modifier,
    onSettings: (() -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    val chrome = LocalChrome.current
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (!username.isNullOrBlank()) {
            IdentityAvatar(name = username)
            Spacer(Modifier.width(8.dp))
        }
        SubsonicBadge(size = 38.dp)
        Spacer(Modifier.width(10.dp))
        Column(Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                BrandMark(size = 20.dp)
                Spacer(Modifier.width(7.dp))
                Text(
                    text = "DubTonic",
                    color = chrome.ink,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(Modifier.height(3.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = "Subsonic",
                    color = chrome.muted,
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                    letterSpacing = MaterialTheme.typography.labelSmall.letterSpacing,
                )
                UsernamePill(
                    name = username?.takeIf { it.isNotBlank() } ?: "LOCAL",
                    modifier = Modifier.weight(1f, fill = false),
                )
            }
        }
        if (onSettings != null) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(chrome.ink.copy(alpha = 0.08f))
                    .border(1.dp, chrome.ink.copy(alpha = 0.14f), CircleShape)
                    .clickable(onClick = onSettings),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = chrome.ink,
                    modifier = Modifier.size(20.dp),
                )
            }
        }
        trailing?.invoke()
    }
}
