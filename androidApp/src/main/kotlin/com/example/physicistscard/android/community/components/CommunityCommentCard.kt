package com.example.physicistscard.android.community.components

import UserComment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            Text(
                text = "用户: ${comment.userId}",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = comment.content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "发布时间: ${TimeUtils.formatKotlinxInstant(comment.createdAt)}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))

            // 展示子回复
            replies.forEach { reply ->
                CommunityReplyCard(reply)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { showReplyInput = !showReplyInput },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("回复", color = MaterialTheme.colorScheme.onSecondary)
            }

            if (showReplyInput) {
                OutlinedTextField(
                    value = replyContent,
                    onValueChange = { replyContent = it },
                    placeholder = { Text("写下你的回复...") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = {
                        if (replyContent.isNotEmpty()) {
                            onReply(comment.commentId, replyContent)
                            replyContent = ""
                            showReplyInput = false
                        }
                    })
                )
            }
        }
    }
}
