package com.example.physicistscard.android.collection.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.physicistscard.android.collection.CollectionItem
import com.example.physicistscard.android.collection.components.CollectionItemCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CollectionDisplayScreen(
    navController: NavController,
    collectionItems: List<CollectionItem>, // 用于显示征集作品列表
    paddingValues: PaddingValues
) {
    // 使用LazyColumn展示征集作品列表
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(paddingValues)
    ) {
        items(collectionItems) { item ->
            CollectionItemCard(
                item = item,
                onClick = {
                    // 点击作品后导航到作品详情页面
                    navController.navigate("collection-detail/${item.id}")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
