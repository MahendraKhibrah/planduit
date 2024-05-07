package com.pens.planduit.domain.models.entity

import com.google.gson.annotations.SerializedName

data class Article(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val slug: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("short_description") val shortDescription: String,
    @SerializedName("category_name") val category_name: String
)