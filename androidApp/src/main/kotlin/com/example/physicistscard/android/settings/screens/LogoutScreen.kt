package com.example.physicistscard.android.settings.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogoutScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("注销登录") })
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(16.dp).padding(paddingValues)
            ) {
                Text("确定要注销登录吗？")
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Button(onClick = {
                        // 执行注销逻辑
                        navController.popBackStack()
                    }) {
                        Text("确认注销")
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = { navController.popBackStack() }) {
                        Text("取消")
                    }
                }
            }
        }
    )
}
