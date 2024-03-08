package com.pens.planduit.domain.models.entity

import com.google.gson.annotations.SerializedName

data class InvestmentResult(
    @SerializedName("is_success") val isSuccess: Boolean,
    @SerializedName("result") val result: Int,
    @SerializedName("invest_primary") val investPrimary: Int,
    @SerializedName("invest_primary_percentage") val investPrimaryPercentage: Double,
    @SerializedName("invest_interest") val investInterest: Int,
    @SerializedName("invest_interest_percentage") val investInterestPercentage: Double,
    @SerializedName("recommendation") val recommendation : RecommendationInvestment
)

data class RecommendationInvestment(
    @SerializedName("year") val year : Int,
    @SerializedName("result") val result: Int,
    @SerializedName("invest_primary") val investPrimary: Int,
    @SerializedName("invest_primary_percentage") val investPrimaryPercentage: Double,
    @SerializedName("invest_interest") val investInterest: Int,
    @SerializedName("invest_interest_percentage") val investInterestPercentage: Double
)