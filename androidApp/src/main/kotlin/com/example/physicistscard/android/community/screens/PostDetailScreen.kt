import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.rounded.CommentBank
import androidx.compose.material.icons.rounded.Star
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.physicistscard.android.R
import com.example.physicistscard.android.commonComponents.comment.Comment
import com.example.physicistscard.android.commonComponents.comment.CommentsSection
import com.example.physicistscard.android.community.components.Post
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(
    postId: String?,
    navController: NavController
) {
    val post = com.example.physicistscard.android.navigation.whole.getPostById(postId)

    var commentText by remember { mutableStateOf(TextFieldValue("")) }
    val comments = remember { mutableStateOf(getInitialComments()) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
        confirmValueChange = { true }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("推送详情") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ChevronLeft, contentDescription = "返回")
                    }
                },
                actions = {
                    // 点赞、收藏按钮
                    IconButton(onClick = { /* 处理点赞逻辑 */ }) {
                        Icon(Icons.Outlined.ThumbUp, contentDescription = "点赞", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                    IconButton(onClick = { /* 处理收藏逻辑 */ }) {
                        Icon(Icons.Rounded.Star, contentDescription = "收藏", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                    IconButton(onClick = { /* 处理分享逻辑 */ }) {
                        Icon(Icons.Filled.Share, contentDescription = "分享")
                    }
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
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
                    Spacer(modifier = Modifier.width(10.dp))
                    OutlinedTextField(
                        // modifier = Modifier.height(56.dp),
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

fun getInitialComments(): List<Comment> {
    return listOf(
        Comment(1, "赵明俊", "马上就要到收获的季节了！", "2024-11-02", replies = listOf(
            Comment(2, "爱因斯坦", "牢底，还得再练", "2024-11-02")
        )),
        Comment(3, "李培梁", "大家好我叫李玲珑", "2024-11-02", replies = listOf(
            Comment(4, "蒋鸿杰", "可爱香草", "2024-11-02"),
            Comment(5, "赵明俊", "香草捏", "2024-11-02")

        )),
        Comment(6, "张子洋", "派克不K头玩什么呀！又不是不分钱！", "2024-11-03"),
        Comment(7, "蒋鸿杰", "可爱香草...", "2024-11-02"),
        Comment(8, "李培梁", "大家好我叫李玲珑", "2024-11-02"),
        Comment(9, "李培梁", "大家好我叫李玲珑", "2024-11-02"),
        Comment(10, "李培梁", "大家好我叫李玲珑", "2024-11-02"),
    )
}
