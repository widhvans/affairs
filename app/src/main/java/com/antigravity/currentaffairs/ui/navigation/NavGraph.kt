package com.antigravity.currentaffairs.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.antigravity.currentaffairs.ui.bookmarks.BookmarkScreen
import com.antigravity.currentaffairs.ui.detail.DetailScreen
import com.antigravity.currentaffairs.ui.home.HomeScreen
import com.antigravity.currentaffairs.ui.settings.SettingsScreen
import com.antigravity.currentaffairs.ui.splash.SplashScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        enterTransition = {
            fadeIn(animationSpec = tween(300)) + slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(300)
            )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(300)) + slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(300)
            )
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(300)) + slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(300)
            )
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(300)) + slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(300)
            )
        }
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onAffairClick = { affairId ->
                    navController.navigate(Screen.Detail.createRoute(affairId))
                },
                onBookmarksClick = {
                    navController.navigate(Screen.Bookmarks.route)
                },
                onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("affairId") { type = NavType.StringType })
        ) { backStackEntry ->
            val affairId = backStackEntry.arguments?.getString("affairId") ?: ""
            DetailScreen(
                affairId = affairId,
                onBackClick = { navController.popBackStack() },
                onRelatedClick = { relatedId ->
                    navController.navigate(Screen.Detail.createRoute(relatedId))
                }
            )
        }

        composable(Screen.Bookmarks.route) {
            BookmarkScreen(
                onBackClick = { navController.popBackStack() },
                onAffairClick = { affairId ->
                    navController.navigate(Screen.Detail.createRoute(affairId))
                }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
