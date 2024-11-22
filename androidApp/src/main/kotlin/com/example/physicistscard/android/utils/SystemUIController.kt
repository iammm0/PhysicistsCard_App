package com.example.physicistscard.android.utils

import android.os.Build
import android.app.Activity
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SystemUiController(
    statusBarColor: Color = Color.Transparent,
    navigationBarColor: Color = Color.Transparent,
    isStatusBarVisible: Boolean = true,
    isNavigationBarVisible: Boolean = true,
    isSystemBarsBehaviorTransient: Boolean = true
) {
    val context = LocalContext.current
    val window = (context as Activity).window

    SideEffect {
        // 设置状态栏颜色
        window.statusBarColor = statusBarColor.toArgb()

        // 设置DecorFitsSystemWindows为false，允许内容延伸到状态栏
        window.setDecorFitsSystemWindows(false)

        // 设置导航栏颜色
        window.navigationBarColor = navigationBarColor.toArgb()

        // 使用WindowInsetsController
        window.insetsController?.let { controller ->
            if (isStatusBarVisible) {
                controller.show(WindowInsets.Type.statusBars())
            } else {
                controller.hide(WindowInsets.Type.statusBars())
            }

            if (isNavigationBarVisible) {
                controller.show(WindowInsets.Type.navigationBars())
            } else {
                controller.hide(WindowInsets.Type.navigationBars())
            }

            if (isSystemBarsBehaviorTransient) {
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            } else {
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_DEFAULT
            }
        }

        // 对于所有版本，确保内容可以延伸至系统栏区域
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}
