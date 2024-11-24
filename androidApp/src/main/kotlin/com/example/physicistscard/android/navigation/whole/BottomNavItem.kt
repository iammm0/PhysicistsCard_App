package com.example.physicistscard.android.navigation.whole

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Collections
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    Collection("store", Icons.Outlined.Collections, "征集"),
    Community("community", Icons.Outlined.Person, "社区"),
    Notification("management", Icons.Outlined.Notifications, "消息"),
    Settings("settings", Icons.Outlined.Settings, "设置")
}