package com.example.physicistscard.android.collection.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.rounded.CommentBank
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.physicistscard.android.R
import com.example.physicistscard.android.collection.CollectionItem
import com.example.physicistscard.android.collection.components.CollectionInteractionButtons
import com.example.physicistscard.android.commonComponents.comment.Comment
import com.example.physicistscard.android.commonComponents.comment.CommentsSection
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CollectionDetailScreen(
    workId: String,
    navController: NavController
) {
    // 获取该 itemId 对应的作品详细信息
    val collectionItem = getWorkById(workId)

    val topBarVisible = remember { mutableStateOf(true) }
    val bottomBarVisible = remember { mutableStateOf(true) }
    var commentText by remember { mutableStateOf(TextFieldValue("")) }

    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
        confirmValueChange = { true }
    )

    // 控制滑动行为来动态显示/隐藏 TopBar 和 BottomBar
    val scrollState = rememberLazyListState()

    // 评论列表的数据和状态
    val comments = remember { mutableStateOf(getInitialComments()) }

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
            TopAppBar(
                title = { Text("作品详情") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ChevronLeft, contentDescription = "返回")
                    }
                },
                actions = {
                    CollectionInteractionButtons(navController = navController)
                }
            )
        },

        bottomBar = {
            // 正常状态的 BottomBar
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.surface,
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                // 评论输入框位于底部弹窗底部
                Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp), verticalAlignment = Alignment.CenterVertically) {
                    // 评论按钮，点击后弹出评论区
                    IconButton(onClick = {
                        scope.launch {
                            bottomSheetState.show()
                        }
                    }) {
                        Icon(Icons.Rounded.CommentBank, contentDescription = "评论", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                    Spacer(modifier = Modifier.width(2.dp))

                    OutlinedTextField(
                        value = commentText,
                        onValueChange = { commentText = it },
                        placeholder = { Text(text = "写下评论...", fontSize = 12.sp) },
                        singleLine = true,
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.weight(1f).height(56.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Send
                        ),
                        keyboardActions = KeyboardActions(
                            onSend = {
                                // 处理发送评论的逻辑
                                println("发送的评论: \${commentText.text}")
                                commentText = TextFieldValue("") // 清空输入框
                            }
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            // 处理发送评论的逻辑
                            println("发送的评论: \${commentText.text}")
                            commentText = TextFieldValue("") // 清空输入框
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        ),
                    ) {
                        Text(text = "发送", color = MaterialTheme.colorScheme.onSecondary)
                    }
                }
            }
        },

        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(2.dp)
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 16.dp) // 增加底部间距，防止被底部栏遮挡
            ) {
                // 作品详细信息展示
                item {
                    Image(
                        painter = painterResource(id = collectionItem.imageUrl),
                        contentDescription = "Collection Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    Text(
                        text = "作品标题: ${collectionItem.title}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    Text(
                        text = collectionItem.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    )

    // 评论区的底部弹窗
    if (bottomSheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    bottomSheetState.hide()
                }
            },
            sheetState = bottomSheetState,
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                CommentsSection(
                    comments = comments.value,
                    onReply = { commentId, content ->
                        comments.value = comments.value.map { comment ->
                            if (comment.id == commentId) {
                                comment.copy(
                                    replies = comment.replies + Comment(
                                        id = comment.replies.size + 1,
                                        userName = "当前用户", // 替换为实际的用户信息
                                        content = content,
                                        timestamp = "刚刚"
                                    )
                                )
                            } else {
                                comment
                            }
                        }
                    }
                )
            }
        }
    }
}

fun getWorkById(workId: String?): CollectionItem {
    // 模拟从数据库或网络获取作品数据
    val works = listOf(
        CollectionItem(
            id = "1",
            title = "Albert Enstein",
            description = "爱因斯坦卡牌。",
            imageUrl = R.drawable.enstein,
            likes = 150,
            commentsCount = 20
        ),
        CollectionItem(
            id = "2",
            title = "Issac Newton",
            description = "牛顿卡牌。",
            imageUrl = R.drawable.newton,
            likes = 200,
            commentsCount = 50
        ),
        CollectionItem(
            id = "3",
            title = "Bohr",
            description = "玻尔卡牌。",
            imageUrl = R.drawable.bohr,
            likes = 200,
            commentsCount = 50
        ),
        CollectionItem(
            id = "5",
            title = "Fourier",
            description = "菲涅尔卡牌。",
            imageUrl = R.drawable.fourier,
            likes = 200,
            commentsCount = 50
        ),
        CollectionItem(
            id = "6",
            title = "Curie",
            description = "居里卡牌。",
            imageUrl = R.drawable.curie,
            likes = 200,
            commentsCount = 50
        ),
        // 添加更多的作品数据
    )
    return works.find { it.id == workId } ?: throw IllegalArgumentException("Work not found with id: $workId")
}

// 获取初始评论列表数据
fun getInitialComments(): List<Comment> {
    return listOf(
        Comment(1, "User1", "这是第一个评论", "2024-11-02", replies = listOf(
            Comment(2, "User2", "这是一个回复", "2024-11-02")
        )),
        Comment(3, "User3", "这是第二个评论", "2024-11-02")
    )
}