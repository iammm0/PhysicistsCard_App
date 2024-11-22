package com.example.physicistscard.android.commonComponents.comment

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Comment(
    val id: Int,
    val userName: String,
    val content: String,
    val timestamp: String,
    val replies: List<Comment> = emptyList()
)

@Composable
fun CommentsSection(
    comments: List<Comment>,
    onReply: (Int, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth() // 填满宽度，保持合理的高度
            .padding(16.dp) // 避免和屏幕边界重叠
    ) {
        Text(
            text = "评论区",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 22.sp),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(comments) { comment ->
                CommentCard(
                    comment = comment,
                    onReply = { replyContent ->
                        onReply(comment.id, replyContent)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}



@Preview(showBackground = true)
@Composable
fun CommentsSectionPreview() {
    val comments = listOf(
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
    CommentsSection(
        comments = comments,
        onReply = { _, _ -> }
    )
}
