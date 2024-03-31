package com.pens.planduit.domain.models.request

import com.google.gson.annotations.SerializedName

data class TradingZakatRequest(
    @SerializedName("rotated_capital") val rotatedCapital : Int,
    @SerializedName("current_recievable") val currentRecievable : Int,
    val profit : Int,
    @SerializedName("current_payable") val currentPayable : Int,
    val loss : Int
)