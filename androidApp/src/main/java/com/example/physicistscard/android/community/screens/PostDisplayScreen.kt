package com.example.physicistscard.android.community.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.physicistscard.android.community.components.Post
import com.example.physicistscard.android.community.components.PostItem

@Composable
fun PostDisplayScreen(
    posts: List<Post>,
    onPostSelected: (Post) -> Unit,    // 当用户选择某个推送时触发
    paddingValues: PaddingValues
) {
    // 推送展示部分（列表）
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(posts) { post ->
            PostItem(
                post = post,
                onClick = { onPostSelected(post) } // 当点击时调用
            )
        }
    }
}