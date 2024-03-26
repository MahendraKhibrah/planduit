package com.pens.planduit.domain.models.entity

import com.google.gson.annotations.SerializedName

data class GoldPrice(
    @SerializedName("gold_price") val price: Int,
)
