package com.example.physicistscard.android.community.screens

import com.example.physicistscard.android.community.components.PostDetailSection
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.physicistscard.android.commonComponents.comment.Comment
import com.example.physicistscard.android.commonComponents.comment.CommentSection
import com.example.physicistscard.android.community.components.Post

@Composable
fun PostDetailScreen(
    post: Post,
    comments: List<Comment>,
    navController: NavHostController
) {
    Column {
        PostDetailSection(post = post, navController)

        Spacer(modifier = Modifier.height(16.dp))

        CommentSection(
            comments = comments,
            onCommentSubmitted = { commentText ->
                println("用户提交的评论: $commentText")
            }
        )
    }
}
