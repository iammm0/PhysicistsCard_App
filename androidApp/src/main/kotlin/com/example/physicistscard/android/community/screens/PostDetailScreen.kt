import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.physicistscard.android.R
import com.example.physicistscard.android.collection.components.formatInstantToReadable
import com.example.physicistscard.android.commonComponents.tag.TagsDisplay
import com.example.physicistscard.android.community.components.CommunityCommentsSection
import com.example.physicistscard.android.data.exampleUserComments
import com.example.physicistscard.transmissionModels.community.content.ImageContent
import com.example.physicistscard.transmissionModels.community.content.TextContent
import com.example.physicistscard.transmissionModels.community.content.VideoContent
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.uuid.UUID
import kotlinx.uuid.generateUUID
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(
    postId: UUID,
    navController: NavController,
) {
    val postItem = getPostById(postId)

    var commentText by remember { mutableStateOf("") }
    val comments = remember { mutableStateOf(getPostComments(postId)) }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

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
                    IconButton(onClick = { /* 点赞逻辑 */ }) {
                        Icon(Icons.Outlined.ThumbUp, contentDescription = "点赞")
                    }
                    IconButton(onClick = { /* 收藏逻辑 */ }) {
                        Icon(Icons.Rounded.Star, contentDescription = "收藏")
                    }
                    IconButton(onClick = { /* 分享逻辑 */ }) {
                        Icon(Icons.Filled.Share, contentDescription = "分享")
                    }
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                // 推送信息卡片
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = postItem.userId,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Text(
                                text = "发布时间: ${formatInstantToReadable(postItem.createdAt)}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                // 显示标题和标签
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = postItem.title,
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            TagsDisplay(tags = postItem.tags)
                        }
                    }
                }

                // 显示内容
                items(postItem.contents.sortedBy { it.order }) { content ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        when (content) {
                            is TextContent -> RenderTextContent(content)
                            is ImageContent -> RenderImageContent(content)
                            is VideoContent -> RenderVideoContent(content)
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.surface
            ) {
                // 评论按钮，点击后弹出评论区
                IconButton(onClick = {
                    scope.launch {
                        bottomSheetState.show()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Rounded.CommentBank,
                        contentDescription = "评论",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp) // 调整图标大小
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                OutlinedTextField(
                    value = commentText,
                    onValueChange = { commentText = it },
                    placeholder = { Text("写下评论...") },
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = {
                        scope.launch {
                            // 添加新评论逻辑
                            if (commentText.isNotEmpty()) {
                                comments.value += UserComment(
                                    commentId = UUID.generateUUID(Random).toString(),
                                    userId = "当前用户",
                                    postId = postId,
                                    content = commentText,
                                    createdAt = Clock.System.now().minus(10, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
                                    parentId = null
                                )
                                commentText = ""
                            }
                        }
                    })
                )
                Button(
                    onClick = {
                        scope.launch {
                            if (commentText.isNotEmpty()) {
                                comments.value += UserComment(
                                    commentId = UUID.generateUUID(Random).toString(),
                                    userId = "当前用户",
                                    postId = postId,
                                    content = commentText,
                                    createdAt = Clock.System.now().minus(10, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
                                    parentId = null
                                )
                                commentText = ""
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("发送", color = MaterialTheme.colorScheme.onSecondary)
                }
            }
        }
    )

    // 评论区的底部弹窗
    if (bottomSheetState.isVisible) {
        ModalBottomSheet(
            onDismissRequest = { scope.launch { bottomSheetState.hide() } },
            sheetState = bottomSheetState
        ) {
            CommunityCommentsSection(
                comments = comments.value,
                onReply = { parentId, replyContent ->
                    comments.value += UserComment(
                        commentId = UUID.generateUUID(Random).toString(),
                        userId = "当前用户",
                        postId = postId,
                        content = replyContent,
                        createdAt = Clock.System.now().minus(10, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
                        parentId = parentId
                    )
                }
            )
        }
    }
}

fun getPostComments(postId: UUID): List<UserComment> {
    return exampleUserComments.filter { it.postId == postId }
}

fun getPostById(postId: UUID): Post {
    val posts = listOf(
        Post(
            postId = UUID("68af0708-40c5-4b6f-b78a-91d98d9561bf"),
            userId = "user001",
            title = "量子计算的前沿进展",
            description = "量子计算作为未来计算的核心技术之一，正在深刻改变科学和工程的面貌。",
            contents = listOf(
                TextContent(
                    contentId = 1,
                    order = 1,
                    text = "量子计算是当前计算机科学领域的热点研究之一..."
                ),
                ImageContent(
                    contentId = 2,
                    order = 2,
                    imageUrl = "https://example.com/images/quantum_computing.png"
                )
            ),
            createdAt = Clock.System.now().minus(10, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().minus(3, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            categories = listOf("科学技术", "量子计算"),
            tags = listOf("量子计算", "科技前沿", "计算机科学")
        ),
        Post(
            postId = UUID("3f9c7a1d-19e0-4e97-aada-5f78e6f2920e"),
            userId = "user002",
            title = "人工智能学习指南",
            description = "为人工智能初学者提供详细的学习路径，涵盖数学、算法和实际应用。",
            contents = listOf(
                TextContent(
                    contentId = 3,
                    order = 1,
                    text = "学习人工智能需要从基础数学开始，包括线性代数和概率论..."
                ),
                VideoContent(
                    contentId = 4,
                    order = 2,
                    videoUrl = "https://example.com/videos/ai_tutorial.mp4"
                )
            ),
            createdAt = Clock.System.now().minus(15, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().minus(5, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            categories = listOf("教育", "人工智能"),
            tags = listOf("人工智能", "深度学习", "数学基础")
        ),
        Post(
            postId = UUID("b8370b6e-5546-4cb2-af70-e2c81d2d2c66"),
            userId = "user003",
            title = "探索可再生能源",
            description = "深入探讨太阳能、风能等可再生能源的开发与应用技术。",
            contents = listOf(
                TextContent(
                    contentId = 5,
                    order = 1,
                    text = "太阳能和风能是目前最具潜力的可再生能源..."
                ),
                ImageContent(
                    contentId = 6,
                    order = 2,
                    imageUrl = "https://example.com/images/renewable_energy.png"
                ),
                VideoContent(
                    contentId = 7,
                    order = 3,
                    videoUrl = "https://example.com/videos/renewable_energy.mp4"
                )
            ),
            createdAt = Clock.System.now().minus(20, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().minus(7, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            categories = listOf("环境保护", "新能源"),
            tags = listOf("新能源", "太阳能", "风能", "可持续发展")
        ),
        Post(
            postId = UUID("8e937640-9fa1-4699-b823-924f524dfdd4"),
            userId = "user004",
            title = "现代艺术表达方式",
            description = "现代艺术的表达形式不断突破传统，展现创意与文化的碰撞。",
            contents = listOf(
                TextContent(
                    contentId = 8,
                    order = 1,
                    text = "现代艺术强调自由表达，突破传统框架..."
                ),
                ImageContent(
                    contentId = 9,
                    order = 2,
                    imageUrl = "https://example.com/images/modern_art.png"
                )
            ),
            createdAt = Clock.System.now().minus(5, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            updatedAt = Clock.System.now().minus(1, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            categories = listOf("艺术", "现代艺术"),
            tags = listOf("现代艺术", "创意", "文化")
        )
    )
    return posts.find { it.postId == postId }
        ?: throw IllegalArgumentException("Post not found with id: $postId")
}


@Composable
fun RenderTextContent(content: TextContent) {
    Text(
        text = content.text,
        style = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun RenderImageContent(content: ImageContent) {
    Image(
        painter = painterResource(id = R.drawable.newton), // 替换为动态图片加载
        contentDescription = "Image Content",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun RenderVideoContent(content: VideoContent) {
    Text(
        text = "视频: ${content.videoUrl}", // 替换为实际的视频播放器
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}
