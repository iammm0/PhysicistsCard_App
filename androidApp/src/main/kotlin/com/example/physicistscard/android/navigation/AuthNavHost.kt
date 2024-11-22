package com.example.physicistscard.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.physicistscard.android.auth.screens.LoginEmailScreen
import com.example.physicistscard.android.auth.screens.LoginNormalScreen
import com.example.physicistscard.android.auth.screens.LoginPhoneScreen
import com.example.physicistscard.android.auth.screens.RegisterEmailScreen
import com.example.physicistscard.android.auth.screens.RegisterPhoneScreen
import com.example.physicistscard.android.auth.screens.StartScreen

@Composable
fun AuthNavHost(navController: NavController) {
    val authNavController = rememberNavController()
    NavHost(
        navController = authNavController,
        startDestination = "start"
    ) {
        composable("start") { StartScreen(navController = authNavController) }
        composable("login") { LoginNormalScreen(navController = authNavController) }
        composable("phone_register") { RegisterPhoneScreen(navController = authNavController) }
        composable("email_register") { RegisterEmailScreen(navController = authNavController) }
        composable("phone_login") { LoginPhoneScreen(navController = authNavController) }
        composable("email_login") { LoginEmailScreen(navController = authNavController) }
    }
}
