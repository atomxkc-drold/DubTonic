package com.androidharness.musicplayer.feature.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.androidharness.musicplayer.feature.library.LibraryScreen
import com.androidharness.musicplayer.feature.player.NowPlayingScreen
import com.androidharness.musicplayer.feature.settings.SettingsScreen

private const val LIBRARY = "library"
private const val NOW_PLAYING = "now_playing"
private const val SETTINGS = "settings"

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = LIBRARY,
            enterTransition = {
                fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMedium)) +
                    scaleIn(
                        initialScale = 0.96f,
                        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                    )
            },
            exitTransition = {
                fadeOut(animationSpec = spring(stiffness = Spring.StiffnessMedium)) +
                    scaleOut(
                        targetScale = 1.03f,
                        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                    )
            },
            popEnterTransition = {
                fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMedium)) +
                    scaleIn(
                        initialScale = 1.03f,
                        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                    )
            },
            popExitTransition = {
                fadeOut(animationSpec = spring(stiffness = Spring.StiffnessMedium)) +
                    scaleOut(
                        targetScale = 0.97f,
                        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                    )
            },
        ) {
            composable(LIBRARY) {
                LibraryScreen(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable,
                    onOpenPlayer = { navController.navigate(NOW_PLAYING) },
                    onOpenSettings = { navController.navigate(SETTINGS) },
                )
            }
            composable(NOW_PLAYING) {
                NowPlayingScreen(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable,
                    onBack = { navController.popBackStack() },
                )
            }
            composable(SETTINGS) {
                SettingsScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}
