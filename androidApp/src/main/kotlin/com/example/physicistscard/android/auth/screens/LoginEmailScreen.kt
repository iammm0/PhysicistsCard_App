package com.example.physicistscard.android.auth.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
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
import com.example.physicistscard.android.auth.components.AppLogo
import com.example.physicistscard.android.auth.components.AuthButton
import com.example.physicistscard.android.auth.components.AuthTextField
import com.example.physicistscard.android.auth.components.RowWithTextFieldAndButton

@Composable
fun LoginEmailScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var emailCode by remember { mutableStateOf("") }
    var fieldsVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppLogo()
        Spacer(modifier = Modifier.height(160.dp))

        AnimatedVisibility(visible = fieldsVisible, enter = androidx.compose.animation.fadeIn(animationSpec = tween(800))) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // 邮箱地址输入框
                AuthTextField(
                    value = email,
                    onValueChange = { email = it },
                    labelText = "邮箱地址",
                    isPassword = false,
                    modifier = Modifier.width(330.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // 邮件验证码输入框
                RowWithTextFieldAndButton(
                    textFieldValue = emailCode,
                    onTextFieldValueChange = { emailCode = it },
                    onButtonClick = {
                        // Implement the send email verification code logic here
                    },
                    textFieldLabel = "邮件代码",
                    buttonText = "发送验证代码"
                )
                Spacer(modifier = Modifier.height(16.dp))
                // 登录按钮
                AuthButton(text = "登录") {
                    // Handle login logic here
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }

    // Update visibility states with delay for animation effect
    androidx.compose.runtime.LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(300)
        fieldsVisible = true
    }
}

