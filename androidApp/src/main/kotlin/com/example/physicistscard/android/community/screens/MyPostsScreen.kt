package com.example.physicistscard.android.community.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.physicistscard.android.community.components.PostsList
import com.example.physicistscard.android.data.myPosts
import com.example.physicistscard.transmissionModels.community.post.MyPost
import com.example.physicistscard.transmissionModels.community.post.PostReviewStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPostsScreen(
    navController: NavHostController,
    myPost: List<MyPost>
) {

    // Tab 配置
    val tabs = listOf("已发布", "审核中", "未通过")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    // 根据审核状态分类推送
    val publishedPosts = myPosts.filter { it.reviewStatus == PostReviewStatus.APPROVED }
    val pendingPosts = myPosts.filter { it.reviewStatus == PostReviewStatus.PENDING }
    val rejectedPosts = myPosts.filter {
        it.reviewStatus == PostReviewStatus.REJECTED ||
                it.reviewStatus == PostReviewStatus.REVOKED
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("我发布的推送") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ChevronLeft, contentDescription = "返回")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            // TabRow 显示不同的分类
            TabRow(selectedTabIndex = selectedTabIndex) {
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

            // 根据当前选中的 Tab 显示对应分类的推送
            when (selectedTabIndex) {
                0 -> PostsList(
                    posts = publishedPosts,
                    paddingValues = paddingValues,
                    onPostClick = { postId ->
                        navController.navigate("postDetail/$postId")
                    }
                )
                1 -> PostsList(
                    posts = pendingPosts,
                    paddingValues = paddingValues,
                    onPostClick = null // 审核中的推送不跳转
                )
                2 -> PostsList(
                    posts = rejectedPosts,
                    paddingValues = paddingValues,
                    onPostClick = null // 未通过的推送不跳转
                )
            }
        }
    }
}
