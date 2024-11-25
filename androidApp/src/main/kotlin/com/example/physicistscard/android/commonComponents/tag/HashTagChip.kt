package com.example.physicistscard.android.commonComponents.tag

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HashTagChip(tag: String) {
    Surface(
        shape = RoundedCornerShape(12.dp), // 圆角形状
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier.padding(2.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = "#$tag", // 带 `#` 样式
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp) // 内边距
        )
    }
}
