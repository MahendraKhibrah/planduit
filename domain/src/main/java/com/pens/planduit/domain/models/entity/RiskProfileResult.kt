package com.pens.planduit.domain.models.entity

import com.google.gson.annotations.SerializedName

data class RiskProfileResult(
    val information : String,
    val type : String,
    @SerializedName("time_period") val timePeriod : String,
    val instrument : String,
    val risk : String,
)
