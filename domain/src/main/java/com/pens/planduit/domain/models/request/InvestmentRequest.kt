package com.pens.planduit.domain.models.request

import com.google.gson.annotations.SerializedName

data class InvestmentRequest(
    @SerializedName("target_money") val targetMoney : Number,
    @SerializedName("target_time") val targetTime : Int,
    @SerializedName("initial_money") val initialMoney : Number,
    @SerializedName("money_investment") val moneyInvestment : Number,
    @SerializedName("time_type") val timeType : String,
    @SerializedName("interest") val interest : Int
    )
