package com.example.physicistscard.android.community.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

@Composable
fun CommunityMenu(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.Person, contentDescription = "个人菜单")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("我点赞的推送") },
                onClick = {
                    expanded = false
                    navController.navigate("liked-posts")
                }
            )
            DropdownMenuItem(
                text = { Text("我收藏的推送") },
                onClick = {
                    expanded = false
                    navController.navigate("collected-posts")
                }
            )
            DropdownMenuItem(
                text = { Text("我发布的推送") },
                onClick = {
                    expanded = false
                    navController.navigate("my-posts")
                }
            )
        }
    }
}
