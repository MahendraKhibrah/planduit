package com.pens.planduit.domain.models.request

import com.google.gson.annotations.SerializedName

data class AgricultureZakatRequest(
    @SerializedName("total_harvest") val totalHarvest : Number,
    @SerializedName("is_watered") val isWatered : Boolean,
    @SerializedName("grain_price") val grainPrice : Number
)
