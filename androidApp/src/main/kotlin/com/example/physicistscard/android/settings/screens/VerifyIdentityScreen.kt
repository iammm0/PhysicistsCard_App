package com.example.physicistscard.android.settings.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyIdentityScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("实名认证") })
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(16.dp)) {
                Text("请输入身份信息进行实名认证：")
                Spacer(
                    modifier = Modifier.height(8.dp).padding(paddingValues)
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* 处理身份输入 */ },
                    label = { Text("身份证号码") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /* 提交身份验证 */ }) {
                    Text("提交认证")
                }
            }
        }
    )
}
