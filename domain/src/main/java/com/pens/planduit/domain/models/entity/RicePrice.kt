package com.pens.planduit.domain.models.entity

import com.google.gson.annotations.SerializedName

data class RicePrice(
    @SerializedName("grain_price") val price : Int,
)
