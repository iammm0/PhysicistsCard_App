package com.example.physicistscard.android.settings.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.physicistscard.android.settings.components.ProfileInfoRow
import com.example.physicistscard.android.ui.components.layouts.TopAppBar
import com.example.physicistscard.transmissionModels.auth.user.User
import kotlinx.datetime.format
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserProfileScreen(navController: NavController, user: User) {

    // 将 kotlinx.datetime 的 LocalDateTime 转换为 Java 的 LocalDateTime
    val javaLocalDateTime = user.registerDate.toJavaLocalDateTime()

    // 使用 DateTimeFormatter 格式化日期
    val formattedRegisterDate = javaLocalDateTime.format(
        DateTimeFormatter.ofPattern("yyyy年MM月dd日 a hh:mm")
    )

    Scaffold(
        topBar = { TopAppBar("个人主页") },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(paddingValues)
            ) {
                // 用户头像
                Card(
                    shape = CircleShape,
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    if (!user.avatarUrl.isNullOrEmpty()) {
                        Image(
                            painter = rememberAsyncImagePainter(user.avatarUrl),
                            contentDescription = "用户头像",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        // 显示默认头像
                        Image(
                            painter = painterResource(id = android.R.drawable.ic_menu_report_image),
                            contentDescription = "默认头像",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 用户名
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 个人简介
                user.bio?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 公共信息卡片
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        user.email?.let {
                            ProfileInfoRow(label = "邮箱", value = it)
                        }
                        user.phone?.let {
                            ProfileInfoRow(label = "手机号", value = it)
                        }
                        ProfileInfoRow(label = "注册时间", value = formattedRegisterDate)
                        ProfileInfoRow(label = "邮箱已验证", value = if (user.isEmailVerified) "是" else "否")
                        ProfileInfoRow(label = "手机号已验证", value = if (user.isPhoneVerified) "是" else "否")
                        ProfileInfoRow(label = "角色", value = user.role.name)
                    }
                }
            }
        }
    )
}