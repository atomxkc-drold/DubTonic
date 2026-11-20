package com.androidharness.musicplayer.feature.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.androidharness.musicplayer.feature.home.HomeScreen
import com.androidharness.musicplayer.feature.library.LibraryScreen
import com.androidharness.musicplayer.feature.player.NowPlayingScreen
import com.androidharness.musicplayer.feature.player.PlayerViewModel
import com.androidharness.musicplayer.feature.settings.SettingsScreen
import com.androidharness.musicplayer.ui.theme.SpringSpecs

private const val HOME = "home"
private const val LIBRARY = "library"
private const val NOW_PLAYING = "now_playing"
private const val SETTINGS = "settings"

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(playerViewModel: PlayerViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val ui by playerViewModel.uiSettings.collectAsStateWithLifecycle()
    val motion = remember(ui) { SpringSpecs(ui) }

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = HOME,
            enterTransition = {
                fadeIn(animationSpec = motion.gentle<Float>()) +
                    scaleIn(initialScale = 0.96f, animationSpec = motion.gentle<Float>())
            },
            exitTransition = {
                fadeOut(animationSpec = motion.gentle<Float>()) +
                    scaleOut(
                        targetScale = 1.03f,
                        animationSpec = motion.gentle<Float>(),
                    )
            },
            popEnterTransition = {
                fadeIn(animationSpec = motion.gentle<Float>()) +
                    scaleIn(
                        initialScale = 1.03f,
                        animationSpec = motion.gentle<Float>(),
                    )
            },
            popExitTransition = {
                fadeOut(animationSpec = motion.gentle<Float>()) +
                    scaleOut(
                        targetScale = 0.97f,
                        animationSpec = motion.gentle<Float>(),
                    )
            },
        ) {
            composable(HOME) {
                HomeScreen(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable,
                    onOpenLibrary = { navController.navigate(LIBRARY) },
                    onOpenPlayer = { navController.navigate(NOW_PLAYING) },
                    onOpenSettings = { navController.navigate(SETTINGS) },
                )
            }
            composable(LIBRARY) {
                LibraryScreen(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable,
                    onBack = { navController.popBackStack() },
                    onOpenPlayer = { navController.navigate(NOW_PLAYING) },
                    onOpenSettings = { navController.navigate(SETTINGS) },
                )
            }
            composable(NOW_PLAYING) {
                NowPlayingScreen(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable,
                    onBack = { navController.popBackStack() },
                    onOpenSettings = { navController.navigate(SETTINGS) },
                )
            }
            composable(SETTINGS) {
                SettingsScreen(onBack = { navController.popBackStack() })
            }
        }
    }
}
