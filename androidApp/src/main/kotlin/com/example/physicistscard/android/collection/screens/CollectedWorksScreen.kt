package com.example.physicistscard.android.collection.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.physicistscard.android.collection.components.work.WorksList
import com.example.physicistscard.transmissionModels.collection.MyWork

// "我收藏的作品" 界面
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectedWorksScreen(navController: NavHostController, collectedWorks: List<MyWork>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("我收藏的作品") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ChevronLeft, contentDescription = "返回")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (collectedWorks.isEmpty()) {
            EmptyStateScreen(message = "您还没有收藏任何作品", paddingValues)
        } else {
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                WorksList(works = collectedWorks) { workId ->
                    navController.navigate("collection-detail/$workId")
                }
            }
        }
    }
}