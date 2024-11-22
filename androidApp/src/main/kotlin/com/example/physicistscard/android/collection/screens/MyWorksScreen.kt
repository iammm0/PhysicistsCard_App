package com.example.physicistscard.android.collection.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Card
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyWorksScreen(navController: NavController) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("审核中的作品", "已发布的作品", "未通过的作品")

    // 模拟不同状态下的作品数据
    val pendingWorks = listOf(
        Work(id = 1, title = "作品A", description = "审核中 - 作品A 描述"),
        Work(id = 2, title = "作品B", description = "审核中 - 作品B 描述")
    )
    val publishedWorks = listOf(
        Work(id = 3, title = "作品C", description = "已发布 - 作品C 描述"),
        Work(id = 4, title = "作品D", description = "已发布 - 作品D 描述")
    )
    val rejectedWorks = listOf(
        Work(id = 5, title = "作品E", description = "未通过 - 作品E 描述"),
        Work(id = 6, title = "作品F", description = "未通过 - 作品F 描述")
    )

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
        Column(modifier = Modifier.fillMaxSize()) {
            // Tab 切换
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = title, color = MaterialTheme.colorScheme.onPrimary) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 根据选中的 Tab 显示不同状态的作品
            when (selectedTabIndex) {
                0 -> WorksList(works = pendingWorks)
                1 -> WorksList(works = publishedWorks)
                2 -> WorksList(works = rejectedWorks)
            }
        }
    }
}

@Composable
fun WorksList(works: List<Work>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(works) { work ->
            WorkCard(work = work)
        }
    }
}

@Composable
fun WorkCard(work: Work) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = { /* 查看作品详情 */ }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = work.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = work.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// 数据模型
data class Work(
    val id: Int,
    val title: String,
    val description: String
)
