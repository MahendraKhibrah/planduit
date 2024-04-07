package com.pens.planduit.domain.models.request

import com.google.gson.annotations.SerializedName

data class IncomeZakatRequest(
    val income: Number,
    @SerializedName("another_income") val anotherIncome: Number,
    val expenditure: Number,
    @SerializedName("time_type") val timeType: String
)