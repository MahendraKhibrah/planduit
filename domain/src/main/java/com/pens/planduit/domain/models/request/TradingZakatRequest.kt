package com.pens.planduit.domain.models.request

import com.google.gson.annotations.SerializedName

data class TradingZakatRequest(
    @SerializedName("rotated_capital") val rotatedCapital : Long,
    @SerializedName("current_recievable") val currentRecievable : Long,
    val profit : Long,
    @SerializedName("current_payable") val currentPayable : Long,
    val loss : Long
)