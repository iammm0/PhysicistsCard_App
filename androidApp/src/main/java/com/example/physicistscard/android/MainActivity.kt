package com.example.physicistscard.android

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.physicistscard.android.themes.PhysicistsCardTheme
import com.example.physicistscard.android.utils.SystemUiController

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 启用 Edge-to-Edge 模式
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            PhysicistsCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 隐藏状态栏
                    SystemUiController()

                    // 创建一个单一的 NavController，并在不同模块间共享
                    val navController = rememberNavController()

                    // 启动导航认证部分，之后可以通过相同的 NavController 进入主界面
                    MainScreen(navController)
                }
            }
        }
    }
}