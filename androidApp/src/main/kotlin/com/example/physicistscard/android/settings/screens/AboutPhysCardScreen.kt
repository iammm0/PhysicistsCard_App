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
fun AboutPhysCardScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar("关于PhysicistsCard") },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(paddingValues) // 应用 Scaffold 提供的 paddingValues
            ) {
                Text("PhysicistsCard 是一个致力于...")
                Spacer(modifier = Modifier.height(8.dp))
                // 其他关于 PhysCard 的信息...
            }
        }
    )
}
