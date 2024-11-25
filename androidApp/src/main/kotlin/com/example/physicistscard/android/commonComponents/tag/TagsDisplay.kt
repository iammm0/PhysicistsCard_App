package com.example.physicistscard.android.commonComponents.tag

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsDisplay(tags: List<String>) {
    if (tags.isNotEmpty()) {
        // 多行展示标签，自动换行
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            maxItemsInEachRow = Int.MAX_VALUE,
        ) {
            tags.forEach { tag ->
                HashTagChip(tag = tag)
            }
        }
    } else {
        // 当标签列表为空时的占位文本
        Text(
            text = "暂无标签",
            style = MaterialTheme.typography.displaySmall,
            color = Color.Gray,
            modifier = Modifier.padding(8.dp)
        )
    }
}
