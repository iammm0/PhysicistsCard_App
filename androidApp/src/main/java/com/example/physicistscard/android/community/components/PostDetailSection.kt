package com.example.physicistscard.android.community.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostDetailSection(
    post: Post,
    navController: NavController
) {

    var commentText by remember { mutableStateOf(TextFieldValue("")) }
    val topBarVisible = remember { mutableStateOf(true) }
    val bottomBarVisible = remember { mutableStateOf(true) }

    // 控制滑动行为来动态显示/隐藏 TopBar 和 BottomBar
    val scrollState = rememberLazyListState()

    // 根据滑动状态控制 TopBar 和 BottomBar 的显示隐藏
    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.firstVisibleItemScrollOffset }.collect { offset ->
            if (offset > 0) {
                launch {
                    topBarVisible.value = false
                    bottomBarVisible.value = false
                }
            } else {
                launch {
                    topBarVisible.value = true
                    bottomBarVisible.value = true
                }
            }
        }
    }

    Scaffold(
        topBar = {
            if (topBarVisible.value) {
                TopAppBar(
                    title = { Text("推送详情") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ChevronLeft, contentDescription = "返回")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* 处理分享逻辑 */ }) {
                            Icon(Icons.Filled.Share, contentDescription = "分享")
                        }
                    }
                )
            }
        },

        bottomBar = {
            if (bottomBarVisible.value) {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    // 评论输入框
                    OutlinedTextField(
                        value = commentText,
                        onValueChange = { commentText = it },
                        placeholder = { Text("写下评论...") },
                        singleLine = true,
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Send
                        ),
                        keyboardActions = KeyboardActions(
                            onSend = {
                                // 处理发送评论的逻辑
                                println("发送的评论: ${commentText.text}")
                                commentText = TextFieldValue("") // 清空输入框
                            }
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )

                    // 点赞、收藏和评论按钮
                    IconButton(onClick = { /* 处理评论逻辑 */ }) {
                        Icon(Icons.Outlined.ArrowUpward, contentDescription = "评论", tint = MaterialTheme.colorScheme.onPrimary)
                    }

                    IconButton(onClick = { /* 处理点赞逻辑 */ }) {
                        Icon(Icons.Outlined.ThumbUp, contentDescription = "点赞", tint = MaterialTheme.colorScheme.onPrimary)
                    }

                    IconButton(onClick = { /* 处理收藏逻辑 */ }) {
                        Icon(Icons.Rounded.Star, contentDescription = "收藏", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        },

        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth().fillMaxHeight()
                    .padding(paddingValues)
            ) {
                item {
                    // 显示推送图片
                    Image(
                        painter = painterResource(id = post.imageUrl),
                        contentDescription = "Post Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // 显示推送标题
                    Text(
                        text = post.title,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // 显示推送描述
                    Text(
                        text = post.description,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    )
}

