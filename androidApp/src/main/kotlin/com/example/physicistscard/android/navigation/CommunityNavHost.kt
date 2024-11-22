package com.example.physicistscard.android.navigation

import PostDetailScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.physicistscard.android.community.CommunityMain
import com.example.physicistscard.android.community.screens.CollectedPostsScreen
import com.example.physicistscard.android.community.screens.EditPostScreen
import com.example.physicistscard.android.community.screens.LikedPostsScreen
import com.example.physicistscard.android.community.screens.MyPostsScreen

@Composable
fun CommunityNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Community.route
    ) {
        // 社区系统相关界面
        composable(BottomNavItem.Community.route) {
            CommunityMain(navController)
        }

        composable("community-edit-post") { EditPostScreen(navController) }

        // 推送详情页面，接收postId参数
        composable("postDetail/{postId}") { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")

            // 显示推送详情
            PostDetailScreen(postId = postId, navController = navController)
        }

        composable("liked-posts") { LikedPostsScreen(navController) }
        composable("collected-posts") { CollectedPostsScreen(navController) }
        composable("my-posts") { MyPostsScreen(navController) }
    }
}