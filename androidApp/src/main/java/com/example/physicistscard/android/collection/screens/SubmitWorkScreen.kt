package com.example.physicistscard.android.collection.screens

// SubmitWorkScreen.kt
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SubmitWorkScreen(navController: NavController) {
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var field by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var selectedImage by remember { mutableStateOf<String?>(null) } // 模拟图片上传
    var selectedFile by remember { mutableStateOf<String?>(null) } // 模拟文件上传
    var selectedVideo by remember { mutableStateOf<String?>(null) } // 模拟视频上传

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("提交作品") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ChevronLeft, contentDescription = "返回")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("作品标题") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("作品类别") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = field,
                onValueChange = { field = it },
                label = { Text("物理学领域") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("作品描述") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                maxLines = 5
            )

            // 模拟上传图片、文件和视频
            OutlinedButton(onClick = { selectedImage = "image.jpg" }) {
                Text(if (selectedImage != null) "图片已上传: $selectedImage" else "上传图片")
            }

            OutlinedButton(onClick = { selectedFile = "file.pdf" }) {
                Text(if (selectedFile != null) "文件已上传: $selectedFile" else "上传文件")
            }

            OutlinedButton(onClick = { selectedVideo = "video.mp4" }) {
                Text(if (selectedVideo != null) "视频已上传: $selectedVideo" else "上传视频")
            }

            Button(
                onClick = {
                    // 执行提交作品逻辑，比如网络请求将数据上传
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("提交作品")
            }
        }
    }
}
