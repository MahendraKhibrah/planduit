package com.pens.planduit.domain.models.request

import com.google.gson.annotations.SerializedName

data class TradingZakatRequest(
    @SerializedName("rotated_capital") val rotatedCapital : Number,
    @SerializedName("current_recievable") val currentRecievable : Number,
    val profit : Number,
    @SerializedName("current_payable") val currentPayable : Number,
    val loss : Number
)