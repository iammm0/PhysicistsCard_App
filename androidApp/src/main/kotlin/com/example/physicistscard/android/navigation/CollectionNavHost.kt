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
import com.example.physicistscard.android.data.exampleMyWorks
import com.example.physicistscard.android.error.ErrorScreen
import com.example.physicistscard.android.navigation.whole.BottomNavItem
import kotlinx.uuid.UUID

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
            val workIdString = backStackEntry.arguments?.getString("workId")
            val workId = try {
                workIdString?.let { UUID(it) }
            } catch (e: IllegalArgumentException) {
                null
            }
            if (workId != null) {
                CollectionDetailScreen(workId = workId, navController = navController)
            } else {
                ErrorScreen(
                    message = "无效的 Work ID：$workIdString",
                    navController = navController
                )
            }
        }
        // 征集系统相关页面
        composable(BottomNavItem.Collection.route) {
            CollectionMain(navController)
        }

        composable("liked-works") {
            LikedWorksScreen(navController, likedWorks = exampleMyWorks.filter { it.isLiked })
        }
        composable("collected-works") {
            CollectedWorksScreen(navController, collectedWorks = exampleMyWorks.filter { it.isFavorited })
        }
        composable("my-works") {
            MyWorksScreen(navController, allWorks = exampleMyWorks)
        }
    }
}