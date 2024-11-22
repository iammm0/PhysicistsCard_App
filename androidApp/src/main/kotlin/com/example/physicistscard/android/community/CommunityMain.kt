package com.example.physicistscard.android.community

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.example.physicistscard.android.community.components.CommunityMenu
import com.example.physicistscard.android.community.components.Post
import com.example.physicistscard.android.community.components.PostItem

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
        Post(
            postId = "2",
            title = "量子物理的基础",
            description = "量子物理学的基本概念和它如何改变我们的世界观。",
            imageUrl = R.drawable.bohr,  // 替换为实际的图片资源
            likeCount = 100,
            commentCount = 30,
            favoriteCount = 60,
            shareCount = 15
        )
        // 其他推送数据...
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
                                    unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary
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
            // 推送展示部分（列表）
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(bottom = 56.dp),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(posts) { post ->
                    PostItem(
                        post = post,
                        onClick = {
                            navController.navigate("postDetail/${post.postId}")
                        } // 当点击时调用
                    )
                }
            }
        }
    )
}