package com.example.physicistscard.android.community

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.sharp.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.physicistscard.android.R
import com.example.physicistscard.android.commonComponents.comment.Comment
import com.example.physicistscard.android.community.components.CommunityMenu
import com.example.physicistscard.android.community.components.Post
import com.example.physicistscard.android.community.screens.PostDisplayScreen

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CommunityMain(navController: NavHostController) {

    val posts = listOf(
        Post(
            postId = "1",
            title = "物理学革命：从牛顿到爱因斯坦",
            description = "这是对物理学历史的深入探讨，从经典力学到相对论的演变过程。",
            imageUrl = R.drawable.newton,  // 替换为实际的图片资源
            likeCount = 120,
            commentCount = 45,
            favoriteCount = 80,
            shareCount = 10
        ),
        // 其他推送数据...
    )

    listOf(
        Comment(userId = "1", username = "赵明俊", commentText = "物理学非常有趣！", timestamp = "2024-02-27", author = "赵明俊"),
        Comment(userId = "2", username = "李培梁", commentText = "这篇文章让我学到了很多。", timestamp = "2024-01-06", author = "李培梁")
    )

    var searchText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "社区", style = MaterialTheme.typography.titleLarge)
                        Spacer(Modifier.width(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = searchText,
                                onValueChange = { searchText = it },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.secondary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary
                                ),
                                singleLine = true,
                                shape = RoundedCornerShape(14.dp),
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                                keyboardActions = KeyboardActions(onSearch = {
                                    // 执行搜索逻辑
                                }),
                                trailingIcon = {
                                    IconButton(onClick = {
                                        // 触发搜索逻辑
                                    }) {
                                        Icon(Icons.Filled.Search, contentDescription = "搜索")
                                    }
                                },
                                modifier = Modifier
                                    .width(188.dp)
                                    .height(30.dp)
                            )
                            CommunityMenu(navController)
                            IconButton(onClick = { navController.navigate("community-edit-post") }) {
                                Icon(Icons.Sharp.Create, contentDescription = "EditPost")
                            }
                            Spacer(Modifier.width(10.dp))
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            PostDisplayScreen(
                posts = posts,
                onPostSelected = { post ->
                    // 点击推送时导航到推送详情页面，并传递postId
                    navController.navigate("postDetail/${post.postId}") },
                paddingValues = paddingValues
            )
        }
    )
}