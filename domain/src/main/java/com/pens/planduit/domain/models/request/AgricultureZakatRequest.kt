package com.pens.planduit.domain.models.request

import com.google.gson.annotations.SerializedName

data class AgricultureZakatRequest(
    @SerializedName("total_harvest") val totalHarvest : Int,
    @SerializedName("is_watered") val isWatered : Boolean,
    @SerializedName("grain_price") val grainPrice : Int
)
