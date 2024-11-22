package com.example.physicistscard.android.community.screens

import android.content.ContentResolver
import android.net.Uri
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPostScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "发表推送",
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                actions = {},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            PostEditCard()
        }
    }
}

@Composable
fun PostEditCard() {
    var text by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var uploadedMedia by remember { mutableStateOf(listOf<Uri>()) }
    val context = LocalContext.current
    var showRemoveDialog by remember { mutableStateOf(false) }


    val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            uploadedMedia = uploadedMedia + uri
            Toast.makeText(context, "已选择图片: $uri", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "没有图片被选择", Toast.LENGTH_SHORT).show()
        }
    }

    val pickVideoLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            uploadedMedia = uploadedMedia + uri
            Toast.makeText(context, "已选择视频: $uri", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "没有视频被选择", Toast.LENGTH_SHORT).show()
        }
    }


    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(14.dp),
    ) {
        LazyColumn(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                BasicTextField(
                    value = text,
                    onValueChange = { text = it },
                    decorationBox = { innerTextField ->
                        if (text.isEmpty()) {
                            Text(
                                text = "大物理学家，请说点什么吧~",
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }

            items(uploadedMedia.size) { index ->
                val uri = uploadedMedia[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column {
                        if (isImageUri(context, uri)) {
                            Image(
                                painter = rememberAsyncImagePainter(uri),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp),
                                contentScale = ContentScale.Crop
                            )
                        } else if (isVideoUri(context, uri)) {
                            AndroidView(
                                factory = { context ->
                                    VideoView(context).apply {
                                        setVideoURI(uri)
                                        setOnPreparedListener { it.isLooping = true }
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
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(
                                onClick = {
                                    showRemoveDialog = true
                                },
                                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                            ) {
                                Text("移除", color = MaterialTheme.colorScheme.onSecondary)
                            }
                            if (showRemoveDialog) {
                                AlertDialog(
                                    onDismissRequest = { showRemoveDialog = false },
                                    title = { Text("确认移除") },
                                    text = { Text("您确定要移除这个文件吗？") },
                                    confirmButton = {
                                        TextButton(
                                            onClick = {
                                                uploadedMedia = uploadedMedia.toMutableList().also { it.removeAt(index) }
                                                showRemoveDialog = false
                                            },
                                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                                        ) {
                                            Text("确认")
                                        }
                                    },
                                    dismissButton = {
                                        TextButton(
                                            onClick = { showRemoveDialog = false },
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

            item {
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
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
                        Icon(Icons.Filled.Add, contentDescription = "Add Image or Video", tint = MaterialTheme.colorScheme.secondary)
                    }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text("选择类型") },
                            text = { Text("请选择您要添加的内容类型。") },
                            containerColor = MaterialTheme.colorScheme.background,
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        pickImageLauncher.launch("image/*")
                                        showDialog = false
                                    },
                                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                                ) {
                                    Text("图片")
                                }
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = {
                                        pickVideoLauncher.launch("video/*")
                                        showDialog = false
                                    },
                                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                                ) {
                                    Text("视频")
                                }
                            }
                        )
                    }
                    Spacer(Modifier.width(14.dp))
                    IconButton(
                        onClick = {
                            Toast.makeText(context, "内容已发布", Toast.LENGTH_SHORT).show()
                            // 这里可以添加发布内容的逻辑
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = Color(0x9EB388FF),
                                shape = CircleShape
                            )
                            .padding(12.dp)
                    ) {
                        Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Send Post", tint = MaterialTheme.colorScheme.secondary)
                    }
                }
            }
        }
    }
}

private fun isImageUri(context: android.content.Context, uri: Uri): Boolean {
    val contentResolver: ContentResolver = context.contentResolver
    val mimeType = contentResolver.getType(uri)
    return mimeType?.startsWith("image") == true
}

private fun isVideoUri(context: android.content.Context, uri: Uri): Boolean {
    val contentResolver: ContentResolver = context.contentResolver
    val mimeType = contentResolver.getType(uri)
    return mimeType?.startsWith("video") == true
}
