package com.example.physicistscard.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.physicistscard.android.settings.SettingMain
import com.example.physicistscard.android.settings.components.ThemeSettingsScreen
import com.example.physicistscard.android.settings.screens.AboutPhysCardScreen
import com.example.physicistscard.android.settings.screens.AccountManagerScreen
import com.example.physicistscard.android.settings.screens.FeedbackScreen
import com.example.physicistscard.android.settings.screens.LanguageSettingsScreen

@Composable
fun SettingsNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Settings.route
    ) {
        composable("settings-main") { SettingMain(navController) }
        composable("setting-theme") { ThemeSettingsScreen(navController = navController) }
        composable("setting-language") { LanguageSettingsScreen(navController = navController) }
        composable("setting-feedback") { FeedbackScreen(navController = navController) }
        composable("setting-appmore") { AboutPhysCardScreen(navController = navController) }
        composable("setting-account") { AccountManagerScreen(navController = navController) }
    }
}