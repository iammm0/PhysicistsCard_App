package com.example.physicistscard.android.messages.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.physicistscard.android.messages.Message
import com.example.physicistscard.android.messages.components.MessageItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageListPage(
    messages: List<Message>,  // 消息列表
    onMessageClick: (Message) -> Unit,  // 点击消息时触发的回调
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("消息列表") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ChevronLeft, contentDescription = "返回")
                    }
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                items(messages) { message ->
                    MessageItem(
                        message = message,
                        onClick = { onMessageClick(message) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    )
}
