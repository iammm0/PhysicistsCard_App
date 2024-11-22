package com.example.physicistscard.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.physicistscard.android.collection.CollectionMain
import com.example.physicistscard.android.collection.screens.CollectedWorksScreen
import com.example.physicistscard.android.collection.screens.CollectionDetailScreen
import com.example.physicistscard.android.collection.screens.LikedWorksScreen
import com.example.physicistscard.android.collection.screens.MyWorksScreen
import com.example.physicistscard.android.collection.screens.SubmitWorkScreen

@Composable
fun CollectionNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Collection.route
    ) {
        composable("submit-work") {
            SubmitWorkScreen(navController)
        }
        composable("collection-main") { CollectionMain(navController) }
        composable("collection-detail/{workId}") { backStackEntry ->
            val workId = backStackEntry.arguments?.getString("workId")
            if (workId != null) {
                CollectionDetailScreen(workId = workId, navController = navController)
            }
        }
        composable("user-work-status") {
            MyWorksScreen(navController)
        }
        // 征集系统相关页面
        composable(BottomNavItem.Collection.route) {
            CollectionMain(navController)
        }

        composable("liked-works") { LikedWorksScreen(navController) }
        composable("collected-works") { CollectedWorksScreen(navController) }
        composable("my-works") { MyWorksScreen(navController) }
    }
}