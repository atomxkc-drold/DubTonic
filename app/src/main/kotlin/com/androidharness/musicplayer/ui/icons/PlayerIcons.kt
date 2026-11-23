package com.androidharness.musicplayer.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.unit.dp

/**
 * Small set of player glyphs kept locally. material-icons-core does not ship the
 * transport controls, and pulling in the extended icon pack would add megabytes to
 * the on-device build for six paths.
 */
object PlayerIcons {

    val Pause: ImageVector by lazy {
        icon("Pause", "M6 19h4V5H6v14zm8-14v14h4V5h-4z")
    }

    val SkipNext: ImageVector by lazy {
        icon("SkipNext", "M6 18l8.5-6L6 6v12zM16 6v12h2V6h-2z")
    }

    val SkipPrevious: ImageVector by lazy {
        icon("SkipPrevious", "M6 6h2v12H6zm3.5 6l8.5 6V6z")
    }

    val Shuffle: ImageVector by lazy {
        icon(
            "Shuffle",
            "M10.59 9.17L5.41 4 4 5.41l5.17 5.17 1.42-1.41zM14.5 4l2.04 2.04" +
                "L4 18.59 5.41 20 17.96 7.46 20 9.5V4h-5.5zm.33 9.41l-1.41 1.41" +
                " 3.13 3.13L14.5 20H20v-5.5l-2.04 2.04-3.13-3.13z"
        )
    }

    val Repeat: ImageVector by lazy {
        icon(
            "Repeat",
            "M7 7h10v3l4-4-4-4v3H5v6h2V7zm10 10H7v-3l-4 4 4 4v-3h12v-6h-2v4z"
        )
    }

    val Download: ImageVector by lazy {
        icon("Download", "M19 9h-4V3H9v6H5l7 7 7-7zM5 18v2h14v-2H5z")
    }

    val Check: ImageVector by lazy {
        icon("Check", "M9 16.17L4.83 12l-1.42 1.41L9 19 21 7l-1.41-1.41z")
    }

    val Smartphone: ImageVector by lazy {
        icon(
            "Smartphone",
            "M17 1H7c-1.1 0-2 .9-2 2v18c0 1.1.9 2 2 2h10c1.1 0 2-.9 2-2V3c0-1.1-.9-2-2-2z" +
                "m0 18H7V5h10v14z"
        )
    }

    val Queue: ImageVector by lazy {
        icon(
            "Queue",
            "M15 6H3v2h12V6zm0 4H3v2h12v-2zM3 16h8v-2H3v2zM17 6v8.18c-.31-.11-.65-.18-1-.18" +
                "-1.66 0-3 1.34-3 3s1.34 3 3 3 3-1.34 3-3V8h3V6h-5z"
        )
    }

    val Lyrics: ImageVector by lazy {
        icon(
            "Lyrics",
            "M20 4v12H5.17L4 17.17V4h16m0-2H4c-1.1 0-2 .9-2 2v18l4-4h14c1.1 0 2-.9 2-2V4" +
                "c0-1.1-.9-2-2-2z"
        )
    }

    val Sparkle: ImageVector by lazy {
        icon(
            "Sparkle",
            "M19 9l1.25-2.75L23 5l-2.75-1.25L19 1l-1.25 2.75L15 5l2.75 1.25L19 9zm-7.5.5L9 4" +
                " 6.5 9.5 1 12l5.5 2.5L9 20l2.5-5.5L17 12l-5.5-2.5zM19 15l-1.25 2.75L15 19" +
                "l2.75 1.25L19 23l1.25-2.75L23 19l-2.75-1.25L19 15z"
        )
    }

    val Equalizer: ImageVector by lazy {
        icon(
            "Equalizer",
            "M3 17v2h6v-2H3zM3 5v2h10V5H3zm10 16v-2h8v-2h-8v-2h-2v6h2zM7 9v2H3v2h4v2h2V9H7z" +
                "m14 4v-2H11v2h10zm-6-4h2V7h4V5h-4V3h-2v6z"
        )
    }

    val Spectrum: ImageVector by lazy {
        icon(
            "Spectrum",
            "M4 10h2v8H4v-8zm4-6h2v14H8V4zm4 3h2v11h-2V7zm4-4h2v18h-2V3zm4 8h2v10h-2V11z"
        )
    }

    private fun icon(name: String, pathData: String): ImageVector =
        ImageVector.Builder(
            name = name,
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f,
        ).addPath(
            pathData = addPathNodes(pathData),
            fill = SolidColor(Color.Black),
        ).build()
}
