package com.example.physicistscard.android.collection.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.physicistscard.android.R
import com.example.physicistscard.android.collection.CollectionItem

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CollectionDetailPage(
    itemId: String?,
    navController: NavController
) {
    // 获取该 itemId 对应的作品详细信息
    val collectionItem = getItemById(itemId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("作品详情") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ChevronLeft, contentDescription = "返回")
                    }
                },
                actions = {
                    IconButton(onClick = { /* 处理分享逻辑 */ }) {
                        Icon(Icons.Filled.Share, contentDescription = "分享")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(16.dp).padding(paddingValues)
            ) {
                // 作品详细信息展示
                Text(text = "作品标题: ${collectionItem.title}", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = painterResource(id = collectionItem.imageUrl),
                    contentDescription = "Collection Image",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = collectionItem.description, style = MaterialTheme.typography.bodyLarge)
            }
        }
    )
}

fun getItemById(itemId: String?): CollectionItem {
    // 根据ID从数据库或本地获取对应的作品数据
    return CollectionItem(
        id = "1",
        title = "作品1",
        description = "这是详细描述内容。",
        imageUrl = R.drawable.ic_launcher,
        likes = 120,
        commentsCount = 30
    )
}
