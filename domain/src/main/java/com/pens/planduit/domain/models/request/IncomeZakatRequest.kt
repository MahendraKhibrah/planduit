package com.pens.planduit.domain.models.request

import com.google.gson.annotations.SerializedName

data class IncomeZakatRequest(
    val income: Int,
    @SerializedName("another_income") val anotherIncome: Int,
    val expenditure: Int,
    @SerializedName("time_type") val timeType: String
)