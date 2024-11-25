package com.example.physicistscard.android.navigation.whole

import PostDetailScreen
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.physicistscard.android.auth.screens.LoginEmailScreen
import com.example.physicistscard.android.auth.screens.LoginNormalScreen
import com.example.physicistscard.android.auth.screens.LoginPhoneScreen
import com.example.physicistscard.android.auth.screens.RegisterEmailScreen
import com.example.physicistscard.android.auth.screens.RegisterPhoneScreen
import com.example.physicistscard.android.auth.screens.StartScreen
import com.example.physicistscard.android.collection.CollectionMain
import com.example.physicistscard.android.collection.screens.CollectedWorksScreen
import com.example.physicistscard.android.collection.screens.CollectionDetailScreen
import com.example.physicistscard.android.collection.screens.LikedWorksScreen
import com.example.physicistscard.android.collection.screens.MyWorksScreen
import com.example.physicistscard.android.collection.screens.SubmitWorkScreen
import com.example.physicistscard.android.community.CommunityMain
import com.example.physicistscard.android.community.screens.CollectedPostsScreen
import com.example.physicistscard.android.community.screens.EditPostScreen
import com.example.physicistscard.android.community.screens.LikedPostsScreen
import com.example.physicistscard.android.community.screens.MyPostsScreen
import com.example.physicistscard.android.error.ErrorScreen
import com.example.physicistscard.android.messages.Message
import com.example.physicistscard.android.messages.MessageMain
import com.example.physicistscard.android.messages.screens.MessageDetailScreen
import com.example.physicistscard.android.settings.SettingMain
import com.example.physicistscard.android.settings.components.ThemeSettingsScreen
import com.example.physicistscard.android.settings.screens.AboutPhysCardScreen
import com.example.physicistscard.android.settings.screens.AccountManagementScreen
import com.example.physicistscard.android.settings.screens.AccountManagerScreen
import com.example.physicistscard.android.settings.screens.BindEmailScreen
import com.example.physicistscard.android.settings.screens.BindPhoneScreen
import com.example.physicistscard.android.settings.screens.FeedbackScreen
import com.example.physicistscard.android.settings.screens.LanguageSettingsScreen
import com.example.physicistscard.android.settings.screens.LogoutScreen
import com.example.physicistscard.android.settings.screens.ResetPasswordScreen
import com.example.physicistscard.android.settings.screens.UserProfileScreen
import com.example.physicistscard.android.settings.screens.VerifyIdentityScreen
import com.example.physicistscard.transmissionModels.auth.user.Role
import com.example.physicistscard.transmissionModels.auth.user.User
import kotlinx.datetime.LocalDateTime
import kotlinx.uuid.UUID

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
        // 登录认证系统相关界面
        composable("start") {
            StartScreen(navController)
        }
        composable("login") {
            LoginNormalScreen(navController)
        }
        composable("phone_register") {
            RegisterPhoneScreen(navController)
        }
        composable("email_register") {
            RegisterEmailScreen(navController)
        }
        composable("phone_login") {
            LoginPhoneScreen(navController)
        }
        composable("email_login") {
            LoginEmailScreen(navController)
        }


        // 社区系统相关界面
        composable(BottomNavItem.Community.route) {
            CommunityMain(navController)
        }
        composable("community-edit-post") { EditPostScreen(navController) }

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

        composable("liked-posts") { LikedPostsScreen(navController) }
        composable("collected-posts") { CollectedPostsScreen(navController) }
        composable("my-posts") { MyPostsScreen(navController) }

        // 征集系统相关页面
        composable(BottomNavItem.Collection.route) {
            CollectionMain(navController)
        }
        composable("submit-work") {
            SubmitWorkScreen(navController)
        }
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
        composable("user-work-status") {
            MyWorksScreen(navController)
        }
        composable("liked-works") {
            LikedWorksScreen(navController)
        }
        composable("collected-works") {
            CollectedWorksScreen(navController)
        }
        composable("my-works") {
            MyWorksScreen(navController)
        }

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
        // 个人主页页面
        composable("setting-ourselves") {
            UserProfileScreen(navController = navController, user = exampleUser)
        }

        composable("account-management") {
            AccountManagementScreen(navController)
        }
        composable("account-bind-email") {
            BindEmailScreen()
        }
        composable("account-bind-phone") {
            BindPhoneScreen()
        }
        composable("account-verify-identity") {
            VerifyIdentityScreen()
        }
        composable("account-logout") {
            LogoutScreen(navController)
        }
        composable("account-reset-password") {
            ResetPasswordScreen()
        }
    }
}

val exampleUser = User(
    userId = "user123456",
    username = "我是明明啊",
    email = "example@physicistscard.com",
    phone = "+8613800000000",
    passwordHash = "e10adc3949ba59abbe56e057f20f883e", // 示例哈希值
    avatarUrl = "https://example.com/avatar.jpg",
    bio = "热衷于探索量子物理与人工智能的交叉领域。",
    registerDate = LocalDateTime.parse("2024-01-01T10:30:00"),
    isEmailVerified = true,
    isPhoneVerified = false,
    role = Role.USER // 普通用户角色
)


fun getMessageById(messageId: String?): Message {
    val messages = listOf(
        Message(id = "1", title = "新评论", content = "您有一条新的评论", timestamp = "10:30 AM"),
        Message(id = "2", title = "新点赞", content = "您的帖子被点赞", timestamp = "9:15 AM")
    )
    return messages.find { it.id == messageId } ?: messages[0] // 返回匹配的消息或默认消息
}