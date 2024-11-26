package com.example.physicistscard.android.collection.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.physicistscard.android.collection.components.work.WorksList
import com.example.physicistscard.transmissionModels.collection.MyWork
import com.example.physicistscard.transmissionModels.collection.WorkReviewStatus

// "我的作品" 界面
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyWorksScreen(navController: NavHostController, allWorks: List<MyWork>) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("审核中的作品", "已发布的作品", "未通过的作品")

    val pendingWorks = allWorks.filter { it.reviewStatus == WorkReviewStatus.PENDING }
    val publishedWorks = allWorks.filter { it.reviewStatus == WorkReviewStatus.APPROVED }
    val rejectedWorks = allWorks.filter { it.reviewStatus == WorkReviewStatus.REJECTED }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("我的作品") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ChevronLeft, contentDescription = "返回")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        selectedContentColor = MaterialTheme.colorScheme.secondary,
                        unselectedContentColor = MaterialTheme.colorScheme.primary,
                        text = { Text(
                            text = title,
                            color = MaterialTheme.colorScheme.onPrimary
                        ) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (selectedTabIndex) {
                0 -> if (pendingWorks.isEmpty()) {
                    EmptyStateScreen(message = "没有审核中的作品")
                } else {
                    WorksList(works = pendingWorks) { /* 不处理点击事件 */ }
                }
                1 -> if (publishedWorks.isEmpty()) {
                    EmptyStateScreen(message = "没有已发布的作品")
                } else {
                    WorksList(works = publishedWorks) { workId ->
                        navController.navigate("collection-detail/$workId") // 跳转到详情界面
                    }
                }
                2 -> if (rejectedWorks.isEmpty()) {
                    EmptyStateScreen(message = "没有未通过的作品")
                } else {
                    WorksList(works = rejectedWorks) { /* 不处理点击事件 */ }
                }
            }
        }
    }
}
