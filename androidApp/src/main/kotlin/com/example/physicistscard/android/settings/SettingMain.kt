package com.example.physicistscard.android.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.physicistscard.android.settings.components.Setting
import com.example.physicistscard.android.settings.components.SettingItem

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingMain(navController: NavHostController) {
    val settings = listOf(
        Setting("个人主页", Icons.Outlined.PersonOutline, "一展风采", "setting-ourselves"),
        Setting("账户管理", Icons.Outlined.ManageAccounts, "就猜到你记性不好咯", "setting-account"),
        Setting("语言", Icons.Outlined.Menu, "我就知道老外会找这个", "setting-language"),
        Setting("反馈", Icons.Outlined.Email, "与官方开发者取得联系", "setting-feedback"),
        Setting("关于PhysicistsCard", Icons.Outlined.Info, "了解PhysicistsCard更多内容？", "setting-appmore")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "设置", style = MaterialTheme.typography.titleLarge)
                }
            )
        },
        content = { paddingValues ->  // 接收 paddingValues 来适应 TopBar 的高度
            LazyColumn(
                contentPadding = paddingValues, // 将内容的 padding 传递给 LazyColumn
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp) // 适当的内容内边距
            ) {
                items(settings) { setting ->
                    SettingItem(setting = setting) {
                        navController.navigate(setting.route)
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
        }
    )
}