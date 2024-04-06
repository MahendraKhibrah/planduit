package com.pens.planduit.domain.models.request

import com.google.gson.annotations.SerializedName

data class IncomeZakatRequest(
    val income: Long,
    @SerializedName("another_income") val anotherIncome: Long,
    val expenditure: Long,
    @SerializedName("time_type") val timeType: String
)