package com.example.physicistscard.transmissionModels.collection

import kotlinx.serialization.Serializable

@Serializable
data class WorkStats(
    val reviewStatus: String,
    val totalRating: Float?,
    val ratingCount: Int?,
    val favoriteCount: Int?,
    val likedCount: Int?,
    val commentCount: Int?,
    val sharedCount: Int?,
)