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
import com.example.physicistscard.android.collection.components.CollectMenu
import com.example.physicistscard.android.collection.components.CollectionItemCard
import com.example.physicistscard.android.data.exampleWorks

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
                                .height(40.dp)
                        )

                        Spacer(Modifier.weight(1f))

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
            // 使用 LazyVerticalGrid 展示征集作品列表
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues).padding(bottom = 84.dp),
                verticalArrangement = Arrangement.spacedBy(1.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
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
