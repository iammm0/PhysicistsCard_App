package com.example.physicistscard.android.collection.screens

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.physicistscard.android.collection.components.TagInputArea
import com.example.physicistscard.android.themes.PhysicistsCardTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SubmitWorkScreen(navController: NavController) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val predefinedCategories = listOf(
        "粒子物理学", "核物理学", "等离子体物理学", "凝聚态物理学", "应用物理学",
        "量子信息科学", "光学", "理论物理学", "实验物理学", "天体物理学",
        "软物质物理", "生物物理学", "流体物理学", "统计物理学", "复杂系统"
    )

    var tags by remember { mutableStateOf(listOf<String>()) }

    var showSubmitDialog by remember { mutableStateOf(false) }

    var selectedCategories by remember { mutableStateOf(setOf<String>()) }

    var uploadedMedia by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var removeDialogMap by remember { mutableStateOf<Map<Uri, Boolean>>(emptyMap()) }

    // 创建用于选择文件的Launcher
    val pickFileLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            uploadedMedia = uploadedMedia + uri
            Toast.makeText(context, "已选择文件: $uri", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "没有文件被选择", Toast.LENGTH_SHORT).show()
        }
    }

    PhysicistsCardTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("提交作品", style = MaterialTheme.typography.titleLarge) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ChevronLeft, contentDescription = "返回")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            content = { paddingValues ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {

                    // 作品标题
                    item {
                        OutlinedTextField(
                            value = title,
                            onValueChange = { title = it },
                            label = { Text("作品标题", style = MaterialTheme.typography.bodyMedium) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                focusedTextColor = MaterialTheme.colorScheme.secondary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                                errorTextColor = MaterialTheme.colorScheme.error,
                            )
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    // 作品描述
                    item {
                        OutlinedTextField(
                            value = description,
                            onValueChange = { description = it },
                            label = { Text("作品描述", style = MaterialTheme.typography.bodyMedium) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                                .height(180.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                                errorTextColor = MaterialTheme.colorScheme.error,
                            ),
                            maxLines = 5,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    item {
                        Text("标签", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        TagInputArea(tags = tags, onTagsChange = { updatedTags ->
                            tags = updatedTags
                        })
                    }

                    // 标签输入
                    // item {
                    //     OutlinedTextField(
                    //         value = tagsInput,
                    //         onValueChange = { input ->
                    //             tagsInput = input
                    //             parsedTags = parseTags(input)
                    //         },
                    //         label = { Text("输入标签，使用 # 标识", style = MaterialTheme.typography.bodyMedium) },
                    //         modifier = Modifier
                    //             .fillMaxWidth()
                    //             .padding(horizontal = 8.dp)
                    //             .height(180.dp),
                    //         colors = OutlinedTextFieldDefaults.colors(
                    //             focusedBorderColor = MaterialTheme.colorScheme.secondary,
                    //             unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    //             focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    //             unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    //             disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                    //             errorTextColor = MaterialTheme.colorScheme.error,
                    //         ),
                    //     )
                    //     Spacer(modifier = Modifier.height(8.dp))
                    //     FlowRow(
                    //         modifier = Modifier.fillMaxWidth(),
                    //         horizontalArrangement = Arrangement.spacedBy(8.dp),
                    //         verticalArrangement = Arrangement.spacedBy(8.dp)
                    //     ) {
                    //         parsedTags.forEach { tag ->
                    //             AssistChip(
                    //                 onClick = { /* 可添加点击逻辑 */ },
                    //                 label = { Text(tag) }
                    //             )
                    //         }
                    //     }
                    // }

                    // 类别选择
                    item {
                        Text("作品类别", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            predefinedCategories.forEach { category ->
                                FilterChip(
                                    selected = selectedCategories.contains(category),
                                    onClick = {
                                        if (selectedCategories.contains(category)) {
                                            selectedCategories = selectedCategories - category
                                        } else if (selectedCategories.size < 3) {
                                            selectedCategories = selectedCategories + category
                                        } else {
                                            Toast.makeText(context, "最多选择 3 个类别", Toast.LENGTH_SHORT).show()
                                        }
                                    },
                                    label = { Text(category) }
                                )
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }


                    // 上传区域
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "上传作品附件",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 18.sp  // 更加显著的小标题风格
                                ),
                                modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 8.dp)
                            )
                            IconButton(
                                onClick = { showDialog = true },
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(
                                        color = Color(0x9EB388FF),
                                        shape = CircleShape
                                    )
                                    .padding(12.dp)
                            ) {
                                Icon(Icons.Filled.Add, contentDescription = "Add File", tint = MaterialTheme.colorScheme.secondary)
                            }
                        }

                        // 上传文件的选择弹窗
                        if (showDialog) {
                            AlertDialog(
                                onDismissRequest = { showDialog = false },
                                title = { Text("选择文件类型") },
                                text = { Text("请选择您要添加的内容类型。") },
                                containerColor = MaterialTheme.colorScheme.background,
                                confirmButton = {
                                    TextButton(
                                        onClick = {
                                            pickFileLauncher.launch("*/*")
                                            showDialog = false
                                        },
                                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                                    ) {
                                        Text("文件")
                                    }
                                },
                                dismissButton = {
                                    TextButton(
                                        onClick = { showDialog = false },
                                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                                    ) {
                                        Text("取消")
                                    }
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // 已上传的文件预览
                        uploadedMedia.forEachIndexed { _, uri ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                Column {
                                    if (isImageUri(uri)) {
                                        Image(
                                            painter = rememberAsyncImagePainter(uri),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(150.dp),
                                            contentScale = ContentScale.Crop
                                        )
                                    } else if (isVideoUri(uri)) {
                                        AndroidView(
                                            factory = { context ->
                                                VideoView(context).apply {
                                                    setVideoURI(uri)
                                                    start()
                                                }
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(150.dp)
                                        )
                                    } else {
                                        Text(
                                            text = "文件: ${uri.lastPathSegment}",
                                            modifier = Modifier.padding(8.dp),
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        TextButton(
                                            onClick = {
                                                uploadedMedia = uploadedMedia - uri
                                            },
                                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                                        ) {
                                            Text("移除", color = MaterialTheme.colorScheme.onPrimary)
                                        }
                                        // 确认移除文件的对话框
                                        if (removeDialogMap[uri] == true) {
                                            AlertDialog(
                                                onDismissRequest = {
                                                    removeDialogMap = removeDialogMap.toMutableMap().apply {
                                                        this[uri] = false
                                                    }
                                                },
                                                title = { Text("确认移除") },
                                                text = { Text("您确定要移除这个文件吗？") },
                                                confirmButton = {
                                                    TextButton(
                                                        onClick = {
                                                            uploadedMedia = uploadedMedia.toMutableList().apply { remove(uri) }
                                                            removeDialogMap = removeDialogMap.toMutableMap().apply {
                                                                remove(uri)
                                                            }
                                                        },
                                                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                                                    ) {
                                                        Text("确认")
                                                    }
                                                },
                                                dismissButton = {
                                                    TextButton(
                                                        onClick = {
                                                            removeDialogMap = removeDialogMap.toMutableMap().apply {
                                                                this[uri] = false
                                                            }
                                                        },
                                                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                                                    ) {
                                                        Text("取消")
                                                    }
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // 提交按钮
                    item {
                        Button(
                            onClick = {
                                // 执行提交作品逻辑，比如网络请求将数据上传
                                // navController.popBackStack()
                                showSubmitDialog = true
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.onSecondary
                            )
                        ) {
                            Text("提交作品", style = MaterialTheme.typography.titleMedium)
                        }
                    }

                }
            }
        )
        // 提交确认对话框
        if (showSubmitDialog) {
            AlertDialog(
                onDismissRequest = { showSubmitDialog = false },
                title = { Text("确认提交") },
                text = { Text("您确认要提交此作品吗？提交后将进入审核状态。") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showSubmitDialog = false
                            Toast.makeText(context, "您的作品已进入审核状态", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }
                    ) {
                        Text("确认", style = MaterialTheme.typography.bodyMedium)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showSubmitDialog = false }) {
                        Text("取消", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            )
        }
    }
}

// 解析标签的方法
private fun parseTags(input: String): List<String> {
    return input.split(" ")
        .filter { it.startsWith("#") && it.length <= 31 }
        .map { it.trim('#') }
        .distinct()
        .take(10) // 限制最多 10 个标签
}

// 判断是否是图片 URI
private fun isImageUri(uri: Uri): Boolean {
    val mimeType = uri.toString()
    return mimeType.contains("image")
}

// 判断是否是视频 URI
private fun isVideoUri(uri: Uri): Boolean {
    val mimeType = uri.toString()
    return mimeType.contains("video")
}