package com.example.physicistscard.android.collection

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Upload
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
import com.example.physicistscard.android.collection.components.CollectMenu
import com.example.physicistscard.android.collection.components.CollectionItemCard
import com.example.physicistscard.android.data.exampleWorks

data class CollectionItem(
    val id: String,             // 作品的唯一ID
    val title: String,          // 作品标题
    val description: String,    // 作品描述
    val imageUrl: Int,          // 作品图片资源ID (可根据需要修改为String)
    val likes: Int,             // 点赞数量
    val commentsCount: Int      // 评论数量
)

// 假设我们有一些征集的作品数据
val collectionItems = listOf(
    CollectionItem(
        id = "1",
        title = "Albert Enstein",
        description = "爱因斯坦卡牌",
        imageUrl = R.drawable.enstein,
        likes = 150,
        commentsCount = 20
    ),
    CollectionItem(
        id = "2",
        title = "Issac Newton",
        description = "牛顿卡牌",
        imageUrl = R.drawable.newton,
        likes = 200,
        commentsCount = 50
    ),
    CollectionItem(
        id = "3",
        title = "Bohr",
        description = "玻尔卡牌",
        imageUrl = R.drawable.bohr,
        likes = 200,
        commentsCount = 50
    ),
    CollectionItem(
        id = "5",
        title = "Fourier",
        description = "菲涅尔卡牌",
        imageUrl = R.drawable.fourier,
        likes = 200,
        commentsCount = 50
    ),
    CollectionItem(
        id = "6",
        title = "Curie",
        description = "居里卡牌",
        imageUrl = R.drawable.curie,
        likes = 200,
        commentsCount = 50
    ),
    // 添加更多作品...
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CollectionMain(navController: NavHostController) {

    Scaffold(
        topBar = {
            var searchText by remember { mutableStateOf("") }
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "征集", style = MaterialTheme.typography.titleLarge)
                        Spacer(Modifier.width(16.dp))

                        // 搜索框
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
                                IconButton(onClick = { /* 触发搜索逻辑 */ }) {
                                    Icon(Icons.Filled.Search, contentDescription = "搜索")
                                }
                            },
                            modifier = Modifier
                                .width(188.dp)
                                .height(30.dp)
                        )

                        // 个人中心按钮，点击后弹出下拉菜单
                        CollectMenu(navController = navController)

                        // 提交作品按钮
                        IconButton(onClick = { navController.navigate("submit-work") }) {
                            Icon(Icons.Outlined.Upload, contentDescription = "提交作品")
                        }
                    }
                }
            )
        },
        content = {  paddingValues ->

            // 调用 CollectionDisplayPage 展示征集作品列表
            // 使用LazyColumn展示征集作品列表
            // LazyColumn(
            //     modifier = Modifier
            //         .fillMaxSize()
            //         .padding(10.dp)
            //         .padding(bottom = 56.dp) // 增加额外的底部间距，避免内容被底部导航栏遮挡
            //         .padding(paddingValues) // 尽量使用系统提供的 padding
            // ) {
            //     items(collectionItems) { item ->
            //         CollectionItemCard(
            //             item = item,
            //             onClick = {
            //                 navController.navigate("collection-detail/${item.id}")
            //             }
            //         )
            //         Spacer(modifier = Modifier.height(16.dp))
            //     }
            // }

            // 使用 LazyVerticalGrid 展示征集作品列表
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 设置为两列布局
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp)
                    .padding(bottom = 72.dp) // 增加额外的底部间距，避免内容被底部导航栏遮挡
                    .padding(paddingValues), // 使用系统提供的 padding
                verticalArrangement = Arrangement.spacedBy(10.dp), // 设置垂直间距
                horizontalArrangement = Arrangement.spacedBy(10.dp), // 设置水平间距
                contentPadding = PaddingValues(6.dp) // 设置内容的内边距
            ) {
                items(exampleWorks) { work ->
                    CollectionItemCard(
                        work = work,
                        onClick = {
                            navController.navigate("collection-detail/${work.workId}")
                        }
                    )
                }
            }
        }
    )
}
