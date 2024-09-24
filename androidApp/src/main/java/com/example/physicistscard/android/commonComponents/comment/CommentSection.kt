package com.example.physicistscard.android.commonComponents.comment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CommentSection(
    comments: List<Comment>,
    onCommentSubmitted: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // 标题
        Text(
            text = "评论",
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 评论列表
        LazyColumn {
            items(comments) { comment ->
                CommentCard(comment = comment)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 评论输入框部分
        CommentInputBar(onCommentSubmitted = onCommentSubmitted, onDismissRequest = {})
    }
}
