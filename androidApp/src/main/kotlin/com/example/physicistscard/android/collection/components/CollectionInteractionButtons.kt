package com.example.physicistscard.android.collection.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.HowToVote
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

@Composable
fun CollectionInteractionButtons(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Outlined.MoreHoriz, contentDescription = "分享主菜单")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                leadingIcon = {
                    Icon(Icons.Outlined.StarRate, contentDescription = "评分", tint = MaterialTheme.colorScheme.onPrimary)
                },
                text = { Text("评分") },
                onClick = {
                    expanded = false
                }
            )
            DropdownMenuItem(
                leadingIcon = {
                    Icon(Icons.Outlined.ThumbUp, contentDescription = "点赞", tint = MaterialTheme.colorScheme.onPrimary)
                },
                text = { Text("点赞") },
                onClick = {
                    expanded = false
                }
            )
            DropdownMenuItem(
                leadingIcon = {
                    Icon(Icons.Outlined.Favorite, contentDescription = "收藏", tint = MaterialTheme.colorScheme.onPrimary)
                },
                text = { Text("收藏") },
                onClick = {
                    expanded = false
                }
            )
            DropdownMenuItem(
                leadingIcon = {
                    Icon(Icons.Filled.Share, contentDescription = "分享", tint = MaterialTheme.colorScheme.onPrimary)
                },
                text = { Text("分享") },
                onClick = {
                    expanded = false
                }
            )
            DropdownMenuItem(
                leadingIcon = {
                    Icon(Icons.Rounded.HowToVote, contentDescription = "投票", tint = MaterialTheme.colorScheme.onPrimary)
                },
                text = { Text("投票") },
                onClick = {
                    expanded = false
                }
            )
        }
    }
}