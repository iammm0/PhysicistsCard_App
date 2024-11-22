package com.example.physicistscard.android.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun AppLogo() {
    var currentText by remember { mutableStateOf("") }
    val fullText = "PHYSICISTS\nCARD"

    LaunchedEffect(Unit) {
        for (i in fullText.indices) {
            delay(100)
            currentText = fullText.substring(0, i + 1)
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentText,
            style = TextStyle(
                fontSize = 60.sp, // 字体大小，根据需要调整
                color = MaterialTheme.colorScheme.secondary
            ),
            textAlign = TextAlign.Center
        )
    }
}