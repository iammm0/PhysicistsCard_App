package com.example.physicistscard.android.navigation

import PostDetailScreen
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.physicistscard.android.R
import com.example.physicistscard.android.auth.screens.LoginEmailScreen
import com.example.physicistscard.android.auth.screens.LoginNormalScreen
import com.example.physicistscard.android.auth.screens.LoginPhoneScreen
import com.example.physicistscard.android.auth.screens.RegisterEmailScreen
import com.example.physicistscard.android.auth.screens.RegisterPhoneScreen
import com.example.physicistscard.android.auth.screens.StartScreen
import com.example.physicistscard.android.collection.CollectionItem
import com.example.physicistscard.android.collection.CollectionMain
import com.example.physicistscard.android.collection.screens.CollectedWorksScreen
import com.example.physicistscard.android.collection.screens.CollectionDetailScreen
import com.example.physicistscard.android.collection.screens.LikedWorksScreen
import com.example.physicistscard.android.collection.screens.MyWorksScreen
import com.example.physicistscard.android.collection.screens.SubmitWorkScreen
import com.example.physicistscard.android.community.CommunityMain
import com.example.physicistscard.android.community.components.Post
import com.example.physicistscard.android.community.screens.CollectedPostsScreen
import com.example.physicistscard.android.community.screens.EditPostScreen
import com.example.physicistscard.android.community.screens.LikedPostsScreen
import com.example.physicistscard.android.community.screens.MyPostsScreen
import com.example.physicistscard.android.messages.Message
import com.example.physicistscard.android.messages.MessageMain
import com.example.physicistscard.android.messages.screens.MessageDetailScreen
import com.example.physicistscard.android.settings.SettingMain
import com.example.physicistscard.android.settings.components.ThemeSettingsScreen
import com.example.physicistscard.android.settings.screens.AboutPhysCardScreen
import com.example.physicistscard.android.settings.screens.AccountManagerScreen
import com.example.physicistscard.android.settings.screens.FeedbackScreen
import com.example.physicistscard.android.settings.screens.LanguageSettingsScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "start",
        enterTransition = {
            slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn()
        },
        exitTransition = {
            slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut()
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn()
        },
        popExitTransition = {
            slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut()
        }
    ) {
        // 认证系统相关界面
        composable("start") { StartScreen(navController) }
        composable("login") { LoginNormalScreen(navController) }
        composable("phone_register") { RegisterPhoneScreen(navController) }
        composable("email_register") { RegisterEmailScreen(navController) }
        composable("phone_login") { LoginPhoneScreen(navController) }
        composable("email_login") { LoginEmailScreen(navController) }

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
        composable("submit-work") {
            SubmitWorkScreen(navController)
        }

        composable("collection-detail/{workId}") { backStackEntry ->
            val workId = backStackEntry.arguments?.getString("workId")

            if (workId != null) {
                CollectionDetailScreen(workId = workId, navController)
            }
        }

        composable("liked-posts") { LikedPostsScreen(navController) }
        composable("collected-posts") { CollectedPostsScreen(navController) }
        composable("my-posts") { MyPostsScreen(navController) }

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

        // 消息列表的相关界面
        composable(BottomNavItem.Notification.route) {
            MessageMain(navController)
        }

        // 消息详情页面
        composable("messageDetail/{messageId}") { backStackEntry ->
            val messageId = backStackEntry.arguments?.getString("messageId")
            // 传递样例数据以测试消息详情
            val message = getMessageById(messageId)
            MessageDetailScreen(message = message,navController)
        }
        // 设置选项相关界面
        composable(BottomNavItem.Settings.route) {
            SettingMain(navController)
        }
        // 主题设置页面
        composable("setting-theme") {
            ThemeSettingsScreen(navController = navController)
        }

        // 语言设置页面
        composable("setting-language") {
            LanguageSettingsScreen(navController = navController)
        }

        // 反馈页面
        composable("setting-feedback") {
            FeedbackScreen(navController = navController)
        }

        // 关于 PhysCard 页面
        composable("setting-appmore") {
            AboutPhysCardScreen(navController = navController)
        }
        // 账户管理页面
        composable("setting-account") {
            AccountManagerScreen(navController = navController)
        }
    }
}

fun getPostById(postId: String?): Post {
    // 模拟从数据库或网络获取帖子数据
    val posts = listOf(
        Post(
            postId = "1",
            title = "物理学革命：从牛顿到爱因斯坦",
            description = "这是对物理学历史的深入探讨，从经典力学到相对论的演变过程。",
            imageUrl = R.drawable.newton,  // 替换为实际的图片资源
            likeCount = 120,
            commentCount = 45,
            favoriteCount = 80,
            shareCount = 10
        ),
        Post(
            postId = "2",
            title = "量子物理的基础",
            description = "量子物理学的基本概念和它如何改变我们的世界观。",
            imageUrl = R.drawable.bohr,  // 替换为实际的图片资源
            likeCount = 100,
            commentCount = 30,
            favoriteCount = 60,
            shareCount = 15
        )
    )
    return posts.find { it.postId == postId } ?: throw IllegalArgumentException("Post not found with id: $postId")
}


fun getMessageById(messageId: String?): Message {
    val messages = listOf(
        Message(id = "1", title = "新评论", content = "您有一条新的评论", timestamp = "10:30 AM"),
        Message(id = "2", title = "新点赞", content = "您的帖子被点赞", timestamp = "9:15 AM")
    )
    return messages.find { it.id == messageId } ?: messages[0] // 返回匹配的消息或默认消息
}