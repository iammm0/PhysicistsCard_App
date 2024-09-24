package com.example.physicistscard.android.messages

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.physicistscard.android.messages.screens.MessageListPage

data class Message(
    val id: String,
    val title: String,
    val content: String,
    val timestamp: String
)

@Composable
fun MessageMain(
    navController: NavController
) {
    val messages = listOf(
        Message(id = "1", title = "新评论", content = "您有一条新的评论", timestamp = "10:30 AM"),
        Message(id = "2", title = "新点赞", content = "您的帖子被点赞", timestamp = "9:15 AM")
    )

    MessageListPage(
        messages = messages,
        onMessageClick = { message ->
            // 导航到消息详情页面，传递messageId
            navController.navigate("messageDetail/${message.id}")
        },
        navController
    )
}