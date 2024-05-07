package com.pens.planduit.domain.models.entity

import com.google.gson.annotations.SerializedName

data class ArticleDetail(
    val id: Int,
    val title: String,
    val slug: String,
    val description: String,
    val thumbnail: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    val status: String,
    @SerializedName("category_name") val categoryName: String
)