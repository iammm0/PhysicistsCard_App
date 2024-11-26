package com.example.physicistscard.android.collection.components.work

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.physicistscard.transmissionModels.collection.MyWork
import kotlinx.uuid.UUID

// 作品列表组件
@Composable
fun WorksList(
    works: List<MyWork>,
    onClick: (UUID) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(works) { work ->
            WorkCard(work = work, onClick = onClick)
        }
    }
}