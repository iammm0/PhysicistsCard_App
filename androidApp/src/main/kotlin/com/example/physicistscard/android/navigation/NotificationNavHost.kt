package com.example.physicistscard.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.physicistscard.android.messages.MessageMain
import com.example.physicistscard.android.messages.screens.MessageDetailScreen

@Composable
fun NotificationNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Notification.route
    ) {
        composable("notification-main") { MessageMain(navController) }
        composable("messageDetail/{messageId}") { backStackEntry ->
            val messageId = backStackEntry.arguments?.getString("messageId")
            MessageDetailScreen(message = getMessageById(messageId), navController = navController)
        }
    }
}