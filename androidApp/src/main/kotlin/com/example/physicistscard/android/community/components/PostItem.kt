package com.example.physicistscard.android.community.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Post(
    val postId: String,           // 帖子唯一ID
    val title: String,            // 帖子标题
    val description: String,      // 帖子描述
    val imageUrl: Int,            // 帖子图片资源ID（如使用本地资源可以用Int, 否则可以改为String表示URL）
    val likeCount: Int,           // 点赞数量
    val commentCount: Int,        // 评论数量
    val favoriteCount: Int,       // 收藏数量
    val shareCount: Int           // 转发数量
)

@Composable
fun PostItem(
    post: Post,
    onClick: () -> Unit
) {
    var favorites by remember { mutableIntStateOf(post.favoriteCount) }
    var likes by remember { mutableIntStateOf(post.likeCount) }
    var shares by remember { mutableIntStateOf(post.shareCount) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()  // 确保推送项占据每列的全宽
            .height(280.dp)  // 设置合理的固定高度
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            // 图片部分
            Image(
                painter = painterResource(id = post.imageUrl),
                contentDescription = "Post Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),  // 设置图片高度
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(8.dp))

            // 标题
            Text(
                text = post.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                maxLines = 1,  // 限制显示一行
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(Modifier.height(4.dp))

            // 描述
            Text(
                text = if (post.description.length > 30) post.description.take(30) + "..." else post.description,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,  // 限制描述最多两行
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(Modifier.height(8.dp))

            // 底部的互动按钮
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly  // 均匀分布按钮
            ) {
                // 评论数，仅作为标签显示
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Edit, // 使用评论图标
                        contentDescription = "Comment",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "${post.commentCount}",  // 显示评论数
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

                // 点赞按钮和数量
                IconButton(
                    onClick = { likes++ },  // 增加点赞数
                    modifier = Modifier.size(54.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.ThumbUp, // 使用点赞图标
                            contentDescription = "Like",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "$likes",  // 显示点赞数
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }

                // 收藏按钮和数量
                IconButton(
                    onClick = { favorites++ },  // 增加收藏数
                    modifier = Modifier.size(54.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Rounded.Star, // 使用收藏图标
                            contentDescription = "Favorite",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "$favorites",  // 显示收藏数
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }

                // 分享按钮和数量
                IconButton(
                    onClick = { shares++ },  // 增加分享数
                    modifier = Modifier.size(54.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Share,  // 使用分享图标
                            contentDescription = "Share",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "$shares",  // 显示分享数
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}