package com.example.physicistscard.android.community.components

import UserComment
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

@Composable
fun CommunityCommentsSection(
    comments: List<UserComment>,
    onReply: (String, String) -> Unit // parentId 和内容
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
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
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(comments.filter { it.parentId == null }) { comment ->
                    CommunityCommentCard(
                        comment = comment,
                        replies = comments.filter { it.parentId == comment.commentId },
                        onReply = onReply
                    )
                }
            }
        }
    }
}
