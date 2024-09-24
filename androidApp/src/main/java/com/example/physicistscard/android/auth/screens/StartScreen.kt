package com.example.physicistscard.android.auth.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.physicistscard.android.navigation.BottomNavItem
import com.example.physicistscard.android.auth.components.AppLogo
import com.example.physicistscard.android.auth.components.AuthButton

@Composable
fun StartScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppLogo()
        Spacer(modifier = Modifier.height(188.dp))
        AuthButton(text = "登录") {
            navController.navigate("login")
        }
        Spacer(modifier = Modifier.height(26.dp))
        AuthButton(text = "使用邮箱注册") {
            navController.navigate("email_register")
        }
        Spacer(modifier = Modifier.height(26.dp))
        AuthButton(text = "使用手机号码注册") {
            navController.navigate("phone_register")
        }
        Spacer(modifier = Modifier.height(26.dp))
        AuthButton(text = "进入PHYSICISTS CARD") {
            navController.navigate(BottomNavItem.Collection.route)
        }
    }
}