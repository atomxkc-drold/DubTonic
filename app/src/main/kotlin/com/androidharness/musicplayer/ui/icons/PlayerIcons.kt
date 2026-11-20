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
