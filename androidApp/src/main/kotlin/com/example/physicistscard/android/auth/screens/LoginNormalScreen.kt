package com.example.physicistscard.android.auth.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.physicistscard.android.auth.components.AppLogo
import com.example.physicistscard.android.auth.components.AuthButton
import com.example.physicistscard.android.auth.components.AuthTextField
import com.example.physicistscard.viewmodels.LoginViewModel
import kotlinx.coroutines.delay

@Composable
fun LoginNormalScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {
    var fieldsVisible by remember { mutableStateOf(false) }

    val loginState by loginViewModel.loginState.collectAsState()

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
                // 邮箱地址/手机号码输入框
                AuthTextField(
                    value = loginState.identifier,
                    onValueChange = { loginViewModel.onIdentifierChange(it) },
                    labelText = "邮箱地址/手机号码",
                    isPassword = false,
                    modifier = Modifier.width(330.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // 密码输入框
                AuthTextField(
                    value = loginState.password,
                    onValueChange = { loginViewModel.onPasswordChange(it) },
                    labelText = "密码",
                    isPassword = true,
                    modifier = Modifier.width(330.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // 显示错误信息
                if (loginState.errorMessage != null) {
                    Text(
                        text = loginState.errorMessage.toString(),
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // 登录按钮
                AuthButton(text = if (loginState.isLoading) "登录中..." else "登录") {
                    if (!loginState.isLoading) {
                        loginViewModel.login()
                    }
                }

                Spacer(modifier = Modifier.height(42.dp))

                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircularButton(icon = Icons.Filled.Phone, description = "手机验证", onClick = {
                        navController.navigate("phone_login")
                    })
                    CircularButton(icon = Icons.Filled.Email, description = "邮箱验证", onClick = {
                        navController.navigate("email_login")
                    })
                }

                Spacer(modifier = Modifier.height(36.dp))

                Text(
                    text = "采用验证登录后不要忘了及时修改密码",
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Light
                    )
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(300)
        fieldsVisible = true
    }

    // 登录成功后导航到主界面
    if (loginState.isLoggedIn) {
        // 您可以根据需要更改导航目标
        LaunchedEffect(Unit) {
            navController.navigate("main") {
                popUpTo("login") { inclusive = true }
            }
        }
    }
}
