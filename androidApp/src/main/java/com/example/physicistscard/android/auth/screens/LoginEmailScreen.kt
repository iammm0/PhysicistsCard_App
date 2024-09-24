package com.example.physicistscard.android.auth.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.physicistscard.android.auth.components.AuthButton
import com.example.physicistscard.android.auth.components.AuthTextField

@Composable
fun LoginEmailScreen(
    navController: NavController,
) {
    var email by remember { mutableStateOf("") }
    var emailCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(160.dp))

        AuthTextField(
            value = email,
            onValueChange = { email = it },
            labelText = "邮箱地址",
            isPassword = false,
            modifier = Modifier.width(330.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        AuthTextField(
            value = emailCode,
            onValueChange = { emailCode = it },
            labelText = "验证码",
            isPassword = false,
            modifier = Modifier.width(330.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthButton(
            text = "发送验证码",
            onClick = {

            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        AuthButton(
            text = "登录",
            onClick = {

            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        AuthButton(text = "返回") {
            navController.popBackStack()
        }
    }
}

