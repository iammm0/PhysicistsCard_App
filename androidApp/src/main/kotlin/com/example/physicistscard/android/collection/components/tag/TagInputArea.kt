package com.example.physicistscard.android.collection.components.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TagInputArea(
    tags: List<String>,
    onTagsChange: (List<String>) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        BasicTextField(
            value = text,
            onValueChange = { inputText ->
                if (inputText.endsWith(" ")) {
                    val tag = inputText.trim()
                    if (tag.startsWith("#") && tag.length <= 30) {
                        val updatedTags = tags + tag
                        onTagsChange(updatedTags)
                        text = "" // 清空输入框
                    }
                } else {
                    text = inputText
                }
            },
            decorationBox = { innerTextField ->
                if (text.isEmpty()) {
                    Text(
                        text = "用#开头输入标签，每个标签之间用空格隔开~",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                }
                innerTextField()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(84.dp)
                .padding(8.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 标签展示区域
        if (tags.isNotEmpty()) {
            Text(
                text = "已输入的标签：",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                tags.forEach { tag ->
                    TagCard(tag = tag, onRemove = {
                        val updatedTags = tags.toMutableList().apply { remove(tag) }
                        onTagsChange(updatedTags)
                    })
                }
            }
        }
    }
}
