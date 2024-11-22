package com.example.physicistscard.transmissionModels.collection

import kotlinx.serialization.Serializable

@Serializable
data class PartialWork(
    val authorId: String,
    val title: String,
    val description: String,
    val categories: List<String>,
    val tags: List<String>,
    val attachment: List<Attachment>
)
