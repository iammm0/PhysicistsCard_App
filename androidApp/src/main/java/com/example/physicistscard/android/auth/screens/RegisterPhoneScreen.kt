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
import com.example.physicistscard.android.auth.components.AppLogo
import com.example.physicistscard.android.auth.components.AuthButton
import com.example.physicistscard.android.auth.components.AuthTextField
import com.example.physicistscard.android.auth.components.RowWithTextFieldAndButton

@Composable
fun RegisterPhoneScreen(
    navController: NavController,
) {
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var phoneCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AppLogo()
        Spacer(modifier = Modifier.height(148.dp))

        // 手机号码输入框
        AuthTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            labelText = "手机号码",
            isPassword = false,
            modifier = Modifier.width(330.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // 密码输入框
        AuthTextField(
            value = password,
            onValueChange = { password = it },
            labelText = "密码",
            isPassword = true,
            modifier = Modifier.width(330.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // 确认密码输入框
        AuthTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            labelText = "确认密码",
            isPassword = true,
            modifier = Modifier.width(330.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // 手机验证码输入框
        RowWithTextFieldAndButton(
            textFieldValue = phoneCode,
            onTextFieldValueChange = { phoneCode = it },
            onButtonClick = {

            },
            textFieldLabel = "验证码",
            buttonText = "发送验证码"
        )
        Spacer(modifier = Modifier.height(32.dp))

        // 注册按钮
        AuthButton(text = "完成注册") {

        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
