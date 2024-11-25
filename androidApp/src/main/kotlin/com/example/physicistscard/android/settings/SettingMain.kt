package com.example.physicistscard.android.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

data class Setting(
    val settingName: String,
    val settingIcon: ImageVector,
    val description: String = "This is a description",
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingMain(navController: NavHostController) {
    val settings = listOf(
        Setting("个人主页", Icons.Outlined.PersonOutline, "一展风采", "setting-ourselves"),
        Setting("账户管理", Icons.Outlined.ManageAccounts, "就猜到你记性不好咯", "account-management"),
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
        content = { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(settings) { setting ->
                    ListItem(
                        headlineContent = {
                            Text(
                                text = setting.settingName,
                                style = MaterialTheme.typography.titleMedium
                            )
                        },
                        supportingContent = {
                            Text(
                                text = setting.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        leadingContent = {
                            Icon(
                                imageVector = setting.settingIcon,
                                contentDescription = setting.settingName,
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        },
                        trailingContent = {
                            Icon(
                                imageVector = Icons.Outlined.ChevronRight,
                                contentDescription = "Navigate",
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        },
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxSize()
                            .clickable { navController.navigate(setting.route) }, // 添加导航逻辑
                        colors = ListItemDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
        }
    )
}
