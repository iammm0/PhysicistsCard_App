package com.example.physicistscard.android.settings.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.physicistscard.android.ui.components.layouts.TopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountManagerScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar("账户管理") },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(paddingValues) // 应用 Scaffold 提供的 paddingValues
            ) {
                Text("在这里处理账户相关的一切操作")

            }
        }
    )
}
