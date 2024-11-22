package com.example.physicistscard.android.collection.screens

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.physicistscard.android.themes.PhysicistsCardTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SubmitWorkScreen(navController: NavController) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val categories = listOf(
        "理论物理", "实验物理", "天体物理", "粒子物理", "核物理", "等离子体物理",
        "凝聚态物理", "应用物理", "量子物理", "光学物理"
    )

    val fields = listOf(
        "量子力学", "相对论", "热力学", "经典力学", "电磁学", "统计力学",
        "固体物理", "宇宙学", "原子物理", "分子物理", "超导", "等离子体物理学"
    )

    var selectedCategories by remember { mutableStateOf(setOf<String>()) }
    var selectedFields by remember { mutableStateOf(setOf<String>()) }

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
                                unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary,
                                focusedTextColor = MaterialTheme.colorScheme.secondary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                                errorTextColor = MaterialTheme.colorScheme.error,
                            )
                        )
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
                                unfocusedBorderColor = MaterialTheme.colorScheme.onSecondary,
                                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                                disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                                errorTextColor = MaterialTheme.colorScheme.error,
                            ),
                            maxLines = 5,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )
                    }

                    // 类别选择
                    item {
                        Text(
                            "作品类别",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 18.sp  // 更加显著的小标题风格
                            ),
                            modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 8.dp)
                        )
                        FlowRow(
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp), // 每个元素之间的水平方向间隔
                            verticalArrangement = Arrangement.spacedBy(8.dp),    // 每行之间的垂直方向间隔
                            maxItemsInEachRow = 3 // 每行最多显示 3 个元素
                        ) {
                            categories.forEach { category ->
                                FilterChip(
                                    selected = selectedCategories.contains(category),
                                    onClick = {
                                        selectedCategories = if (selectedCategories.contains(category)) {
                                            selectedCategories - category
                                        } else {
                                            selectedCategories + category
                                        }
                                    },
                                    label = { Text(category) },
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                        }
                    }

                    // 物理学领域选择
                    item {
                        Text(
                            "物理学领域",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 18.sp  // 更加显著的小标题风格
                            ),
                            modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 8.dp)
                        )
                        FlowRow(
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp), // 每个元素之间的水平方向间隔
                            verticalArrangement = Arrangement.spacedBy(8.dp),    // 每行之间的垂直方向间隔
                            maxItemsInEachRow = 3 // 每行最多显示 3 个元素
                        ) {
                            fields.forEach { field ->
                                FilterChip(
                                    selected = selectedFields.contains(field),
                                    onClick = {
                                        selectedFields = if (selectedFields.contains(field)) {
                                            selectedFields - field
                                        } else {
                                            selectedFields + field
                                        }
                                    },
                                    label = { Text(field) },
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                        }
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
                                navController.popBackStack()
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
    }
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