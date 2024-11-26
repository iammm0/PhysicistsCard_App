package com.example.physicistscard.android.community.components

import UserComment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.physicistscard.android.utils.TimeUtils

@Composable
fun CommunityCommentCard(
    comment: UserComment,
    replies: List<UserComment>,
    onReply: (String, String) -> Unit
) {
    var showReplies by remember { mutableStateOf(false) }
    var replyContent by remember { mutableStateOf("") }
    var showReplyInput by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // 显示用户和评论内容
            Text(
                text = comment.userId,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = comment.content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = TimeUtils.formatKotlinxInstant(comment.createdAt),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 操作区：展开/折叠回复和唤醒回复框
            Row(verticalAlignment = Alignment.CenterVertically) {
                // 展开/折叠回复按钮
                if (comment.parentId == null && replies.isNotEmpty()) {
                    TextButton(
                        onClick = { showReplies = !showReplies },
                        colors = ButtonColors(
                            contentColor = MaterialTheme.colorScheme.secondary,
                            containerColor = MaterialTheme.colorScheme.onSecondary,
                            disabledContentColor = MaterialTheme.colorScheme.secondary,
                            disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(if (showReplies) "折叠回复" else "展开更多回复")
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // 唤醒回复输入框按钮
                TextButton(
                    onClick = { showReplyInput = !showReplyInput },
                    colors = ButtonColors(
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        containerColor = MaterialTheme.colorScheme.primary,
                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                        disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                    ),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("回复")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 回复输入框
            if (showReplyInput) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    value = replyContent,
                    onValueChange = { replyContent = it },
                    label = { Text("回复${comment.userId}") },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary,
                        focusedLabelColor = MaterialTheme.colorScheme.secondary,
                        focusedBorderColor = MaterialTheme.colorScheme.secondary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = {
                        if (replyContent.isNotEmpty()) {
                            onReply(comment.commentId, replyContent)
                            replyContent = ""
                            showReplyInput = false
                        }
                    })
                )

                IconButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                        if (replyContent.isNotEmpty()) {
                            onReply(comment.commentId, replyContent)
                            replyContent = ""
                            showReplyInput = false
                        }
                    },
                    colors = IconButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        disabledContentColor = MaterialTheme.colorScheme.secondary,
                        disabledContainerColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "发送回复",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            // 子回复展示
            if (showReplies) {
                replies.forEach { reply ->
                    Spacer(modifier = Modifier.height(8.dp))
                    CommunityReplyCard(reply)
                }
            }
        }
    }
}