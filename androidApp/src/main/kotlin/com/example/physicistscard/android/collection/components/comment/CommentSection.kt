package com.example.physicistscard.android.collection.components.comment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.physicistscard.transmissionModels.collection.Comment
import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CommentsSection(
    comments: List<Comment>,
    onReply: (Int, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "评论区",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        if (comments.isEmpty()) {
            // 显示提示信息
            Text(
                text = "暂时还没有评论，快来抢占第一个评论吧！",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(comments) { comment ->
                    CommentCard(
                        comment = comment,
                        onReply = { replyContent ->
                            onReply(comment.commentId, replyContent)
                        }
                    )
                }
            }
        }
    }
}


fun formatInstantToReadable(instant: Instant): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val zonedDateTime = ZonedDateTime.ofInstant(instant.toJavaInstant(), ZoneId.systemDefault())
    return formatter.format(zonedDateTime)
}