package com.antigravity.currentaffairs.ui.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Home : Screen("home")
    data object Detail : Screen("detail/{affairId}") {
        fun createRoute(affairId: String): String = "detail/$affairId"
    }
    data object Bookmarks : Screen("bookmarks")
    data object Settings : Screen("settings")
}
