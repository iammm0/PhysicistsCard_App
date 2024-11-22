@file:Suppress("NAME_SHADOWING")

package com.example.physicistscard.android

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.physicistscard.android.navigation.BottomNavItem
import com.example.physicistscard.android.navigation.NavigationHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController,
) {

    val bottomItems = listOf(
        BottomNavItem.Collection,
        BottomNavItem.Community,
        BottomNavItem.Notification,
        BottomNavItem.Settings
    )

    // 定义需要隐藏底部导航栏的路由集合
    val routesToHideBottomBar = setOf(
        "start",
        "login",
        "phone_register",
        "email_register",
        "phone_login",
        "email_login",
        "community-edit-post",
        "postDetail/{postId}",
        "collection-detail/{workId}",
        "submit-work",
    )

    // 监听当前导航栈的变化
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // 检查当前路由是否在集合中，以决定是否显示底部导航栏
    val showBottomBar = currentRoute?.let { route ->
        // 检查当前路由是否匹配集合中的任一路由模式
        routesToHideBottomBar.none { it in route }
    } ?: true

    Scaffold(
        bottomBar = {
            // 根据showBottomBar的值来决定是否展示底部导航栏
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.background,
                ) {
                    val currentRoute = navController.currentDestination?.route
                    bottomItems.forEachIndexed { _, bottomNavItem->
                        NavigationBarItem(
                            selected = currentRoute == bottomNavItem.route,
                            onClick = {
                                navController.navigate(bottomNavItem.route) {
                                    navController.graph.startDestinationRoute?.let { route ->
                                        popUpTo(route) { saveState = true }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { // 图标
                                Icon(
                                    imageVector = bottomNavItem.icon,
                                    contentDescription = null
                                )
                            },
                            label = { // 文字
                                Text(
                                    text = bottomNavItem.title,
                                    style = TextStyle(
                                        fontSize = 12.sp, // 设置字体大小
                                    )
                                )
                            },
                            alwaysShowLabel = false,
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.secondary,  // 选中图标颜色
                                selectedTextColor = MaterialTheme.colorScheme.secondary, // 选中文字颜色
                                unselectedIconColor = MaterialTheme.colorScheme.onSurface, // 未选中图标颜色
                                unselectedTextColor = MaterialTheme.colorScheme.onSurface // 未选中文字颜色
                            )
                        )
                    }
                }
            }
        }
    ) {
        NavigationHost(navController = navController)
    }
}