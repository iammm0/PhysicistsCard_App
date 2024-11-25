package com.example.physicistscard.android.settings.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountManagementScreen(navController: NavHostController) {
    val accountOptions = listOf(
        "绑定邮箱" to "account-bind-email",
        "绑定手机号码" to "account-bind-phone",
        "实名认证" to "account-verify-identity",
        "注销登录" to "account-logout",
        "重置密码" to "account-reset-password"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("账户管理") }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(accountOptions) { option ->
                    ListItem(
                        headlineContent = { Text(option.first) },
                        modifier = Modifier.clickable { navController.navigate(option.second) }
                    )
                }
            }
        }
    )
}
