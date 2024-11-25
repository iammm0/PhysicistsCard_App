package com.example.physicistscard.android.data

import com.example.physicistscard.transmissionModels.collection.CommentReply
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus

// 示例评论回复数据
val exampleCommentReplies = listOf(
    CommentReply(
        replyId = 1,
        commentId = 1,
        userId = "子洋哥哥",
        content = "确实，量子力学的应用前景也很值得期待！",
        replyDate = Clock.System.now().minus(12, DateTimeUnit.HOUR, TimeZone.currentSystemDefault())
    ),
    CommentReply(
        replyId = 2,
        commentId = 2,
        userId = "星辰大海与人间地狱",
        content = "人工智能和深度学习真是现代科技的核心！",
        replyDate = Clock.System.now().minus(1, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
    )
)
