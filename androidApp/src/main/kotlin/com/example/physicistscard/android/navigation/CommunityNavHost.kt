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
import com.example.physicistscard.android.data.favoritedPosts
import com.example.physicistscard.android.data.likedPosts
import com.example.physicistscard.android.data.myPosts
import com.example.physicistscard.android.error.ErrorScreen
import com.example.physicistscard.android.navigation.whole.BottomNavItem
import kotlinx.uuid.UUID

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
            val postIdString = backStackEntry.arguments?.getString("postId")
            val postId = try {
                postIdString?.let { UUID(it) } // 将字符串转换为 UUID
            } catch (e: IllegalArgumentException) {
                null // 如果转换失败，处理为 null
            }

            if (postId != null) {
                PostDetailScreen(postId = postId, navController = navController)
            } else {
                // 处理错误情况，例如显示错误页面
                ErrorScreen(message = "Invalid Post ID")
            }
        }

        composable("liked-posts") {
            LikedPostsScreen(
                navController = navController,
                likedPosts = likedPosts
            )
        }
        composable("collected-posts") {
            CollectedPostsScreen(
                navController = navController,
                collectedPosts = favoritedPosts
            )
        }
        composable("my-posts") {
            MyPostsScreen(
                navController = navController,
                myPost = myPosts
            )
        }
    }
}