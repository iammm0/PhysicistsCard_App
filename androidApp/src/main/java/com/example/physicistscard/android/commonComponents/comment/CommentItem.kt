package com.example.physicistscard.android.commonComponents.comment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Comment(
    val userId: String,
    val username: String,
    val commentText: String,
    val timestamp: String,
    val author: String,
    val replies: MutableList<Comment> = mutableListOf()
)

@Composable
fun CommentItem(comment: Comment) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = comment.username,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary
            )
        Spacer(Modifier.height(2.dp))
        Text(
            text = comment.commentText,
            color = MaterialTheme.colorScheme.secondary
            )
        Spacer(Modifier.height(4.dp))
        Text(
            text = comment.timestamp,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary
            )
        Spacer(modifier = Modifier.height(4.dp))
    }
}