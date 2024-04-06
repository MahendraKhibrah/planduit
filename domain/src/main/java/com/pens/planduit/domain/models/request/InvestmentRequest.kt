package com.pens.planduit.domain.models.request

import com.google.gson.annotations.SerializedName

data class InvestmentRequest(
    @SerializedName("target_money") val targetMoney : Long,
    @SerializedName("target_time") val targetTime : Int,
    @SerializedName("initial_money") val initialMoney : Long,
    @SerializedName("money_investment") val moneyInvestment : Long,
    @SerializedName("time_type") val timeType : String,
    @SerializedName("interest") val interest : Int
    )
