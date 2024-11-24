package com.example.physicistscard.android.collection.screens

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.rounded.CommentBank
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.physicistscard.android.collection.components.AttachmentItem
import com.example.physicistscard.android.collection.components.CollectionInteractionButtons
import com.example.physicistscard.android.commonComponents.TagsDisplay
import com.example.physicistscard.android.commonComponents.comment.Comment
import com.example.physicistscard.android.commonComponents.comment.CommentsSection
import com.example.physicistscard.android.data.exampleAttachments
import com.example.physicistscard.transmissionModels.collection.Attachment
import com.example.physicistscard.transmissionModels.collection.AttachmentType
import com.example.physicistscard.transmissionModels.collection.AttachmentType.FILE
import com.example.physicistscard.transmissionModels.collection.Work
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import kotlinx.uuid.UUID
import kotlinx.uuid.generateUUID
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CollectionDetailScreen(
    workId: UUID,
    navController: NavController
) {
    // 获取该 itemId 对应的作品详细信息
    val workItem = getWorkItemById(workId)

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
                    .padding(16.dp)
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 16.dp) // 增加底部间距，防止被底部栏遮挡
            ) {
                // 作者和提交日期卡片
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = workItem.authorId,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "提交日期: ${formatInstantToSeconds(workItem.submitDate)}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                // 标签展示卡片
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "标签",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            TagsDisplay(tags = workItem.tags)
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // 其余内容卡片
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            Text(
                                text = workItem.title,
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = workItem.description,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                // 作品附件预览卡片
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "作品附件",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                items(workItem.attachment) { attachment ->
                                    AttachmentItem(attachment)
                                }
                            }
                        }
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

fun formatInstantToSeconds(instant: Instant, timeZone: TimeZone = TimeZone.currentSystemDefault()): String {
    val localDateTime: LocalDateTime = instant.toLocalDateTime(timeZone)
    val year = localDateTime.year
    val month = localDateTime.monthNumber // 月份（1-12）
    val day = localDateTime.dayOfMonth
    val hour = localDateTime.hour
    val minute = localDateTime.minute
    val second = localDateTime.second
    return "%04d-%02d-%02d %02d:%02d:%02d".format(year, month, day, hour, minute, second)
}


fun getWorkItemById(workId: UUID): Work {
    // 模拟从数据库或网络获取作品数据
    val works = listOf(
        Work(
            workId = UUID("68af0708-40c5-4b6f-b78a-91d98d9561bf"),
            authorId = "理天帝",
            title = "量子力学概论",
            description = "本作品介绍了量子力学的基本概念与应用，包括量子态、叠加原理和测量等。",
            categories = listOf("物理", "量子力学"),
            tags = listOf("量子", "物理学", "科学研究"),
            attachment = exampleAttachments,
            updateDate = Clock.System.now().minus(2, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            submitDate = Clock.System.now().minus(10, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
        ),
        Work(
            workId = UUID("3f9c7a1d-19e0-4e97-aada-5f78e6f2920e"),
            authorId = "我是明明啊",
            title = "人工智能与深度学习",
            description = "本作品探讨了深度学习在图像识别中的应用，涵盖了CNN架构及优化算法。",
            categories = listOf("计算机科学", "人工智能"),
            tags = listOf("深度学习", "计算机视觉", "AI"),
            attachment = listOf(
                Attachment(
                    attachmentId = 4,
                    workId = UUID.generateUUID(Random),
                    attachmentUrl = "https://example.com/代码.zip",
                    attachmentName = "神经网络代码",
                    attachmentSize = 30720L,
                    attachmentType = FILE,
                    uploadAt = Clock.System.now().minus(1, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
                )
            ),
            updateDate = Clock.System.now().minus(5, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            submitDate = Clock.System.now().minus(15, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
        ),
        Work(
            workId = UUID("B8370B6E-5546-4CB2-AF70-E2C81D2D2C66"),
            authorId = "子洋哥哥",
            title = "新能源技术的探索",
            description = "本作品分析了太阳能与风能的最新研究进展，提出了多能互补的技术解决方案。",
            categories = listOf("能源", "环境科学"),
            tags = listOf("新能源", "太阳能", "风能", "可持续发展"),
            attachment = listOf(
                Attachment(
                    attachmentId = 5,
                    workId = UUID.generateUUID(Random),
                    attachmentUrl = "https://example.com/技术报告.pdf",
                    attachmentName = "新能源技术报告",
                    attachmentSize = 10240L,
                    attachmentType = FILE,
                    uploadAt = Clock.System.now().minus(7, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
                )
            ),
            updateDate = Clock.System.now().minus(6, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            submitDate = Clock.System.now().minus(20, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
        ),
        Work(
            workId = UUID("8e937640-9fa1-4699-b823-924f524dfdd4"),
            authorId = "泰德·莫斯比",
            title = "脑机接口技术的研究",
            description = "探讨脑机接口技术的最新研究进展，分析其在医疗和增强现实领域的潜力。",
            categories = listOf("神经科学", "计算机科学"),
            tags = listOf("脑机接口", "增强现实", "医疗科技"),
            attachment = listOf(
                Attachment(
                    attachmentId = 6,
                    workId = UUID(),
                    attachmentUrl = "https://example.com/研究论文.pdf",
                    attachmentName = "脑机接口论文",
                    attachmentSize = 20480L,
                    attachmentType = AttachmentType.FILE,
                    uploadAt = Clock.System.now().minus(3, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
                )
            ),
            updateDate = Clock.System.now().minus(7, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            submitDate = Clock.System.now().minus(25, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
        ),
        Work(
            workId = UUID("fc3e2239-61a8-4961-93d1-b5a87e5b521d"),
            authorId = "杰斯",
            title = "3D打印技术的工业应用",
            description = "探索3D打印技术在制造业中的广泛应用，包括其优势和未来的发展方向。",
            categories = listOf("工业技术", "机械工程"),
            tags = listOf("3D打印", "制造业", "工业革命"),
            attachment = listOf(
                Attachment(
                    attachmentId = 7,
                    workId = UUID.generateUUID(Random),
                    attachmentUrl = "https://example.com/3D模型.zip",
                    attachmentName = "打印模型文件",
                    attachmentSize = 51200L,
                    attachmentType = AttachmentType.FILE,
                    uploadAt = Clock.System.now().minus(2, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
                )
            ),
            updateDate = Clock.System.now().minus(6, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            submitDate = Clock.System.now().minus(12, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
        ),
        Work(
            workId = UUID("47e59a2a-df12-4e9b-97d3-f843f6d48ea8"),
            authorId = "星辰大海与人间地狱",
            title = "航天器热控制系统研究",
            description = "研究航天器热控制技术的原理和实现，分析其对太空任务的关键意义。",
            categories = listOf("航天工程", "物理"),
            tags = listOf("航天器", "热控制", "太空技术"),
            attachment = listOf(
                Attachment(
                    attachmentId = 8,
                    workId = UUID.generateUUID(Random),
                    attachmentUrl = "https://example.com/热控设计图.pdf",
                    attachmentName = "热控设计图",
                    attachmentSize = 30720L,
                    attachmentType = AttachmentType.FILE,
                    uploadAt = Clock.System.now().minus(5, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
                )
            ),
            updateDate = Clock.System.now().minus(8, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            submitDate = Clock.System.now().minus(30, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
        ),
        Work(
            workId = UUID("38b2136e-3a4c-40a7-b6fa-e2bb412d3cf5"),
            authorId = "没头脑的盖茨比",
            title = "生态恢复技术与实践",
            description = "介绍生态恢复技术的理论与实际应用，特别是在湿地和森林的修复中。",
            categories = listOf("环境科学", "生态学"),
            tags = listOf("生态恢复", "环境保护", "可持续发展"),
            attachment = listOf(
                Attachment(
                    attachmentId = 9,
                    workId = UUID.generateUUID(Random),
                    attachmentUrl = "https://example.com/生态报告.pdf",
                    attachmentName = "生态恢复报告",
                    attachmentSize = 10240L,
                    attachmentType = AttachmentType.FILE,
                    uploadAt = Clock.System.now().minus(4, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
                )
            ),
            updateDate = Clock.System.now().minus(9, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            submitDate = Clock.System.now().minus(22, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
        ),
        Work(
            workId = UUID("edfdc432-4f5c-4914-8f71-13a7bda94864"),
            authorId = "不养猫的薛定谔",
            title = "自然语言处理的进展",
            description = "分析自然语言处理技术在语义理解和机器翻译中的新突破。",
            categories = listOf("计算机科学", "人工智能"),
            tags = listOf("NLP", "语义理解", "机器翻译"),
            attachment = listOf(
                Attachment(
                    attachmentId = 10,
                    workId = UUID.generateUUID(Random),
                    attachmentUrl = "https://example.com/代码示例.zip",
                    attachmentName = "自然语言处理代码",
                    attachmentSize = 20480L,
                    attachmentType = AttachmentType.FILE,
                    uploadAt = Clock.System.now().minus(6, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
                )
            ),
            updateDate = Clock.System.now().minus(8, DateTimeUnit.DAY, TimeZone.currentSystemDefault()),
            submitDate = Clock.System.now().minus(18, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
        )
    )
    return works.find { it.workId == workId } ?: throw IllegalArgumentException("Work not found with id: $workId")
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