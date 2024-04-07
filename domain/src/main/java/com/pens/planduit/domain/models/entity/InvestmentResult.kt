package com.pens.planduit.domain.models.entity

import com.google.gson.annotations.SerializedName

data class InvestmentResult(
    @SerializedName("is_success") val isSuccess: Boolean,
    @SerializedName("result") val result: Number,
    @SerializedName("invest_primary") val investPrimary: Number,
    @SerializedName("invest_primary_percentage") val investPrimaryPercentage: Number,
    @SerializedName("invest_interest") val investInterest: Number,
    @SerializedName("invest_interest_percentage") val investInterestPercentage: Number,
    @SerializedName("recommendation") val recommendation : RecommendationInvestment
)

data class RecommendationInvestment(
    @SerializedName("year") val year : Int,
    @SerializedName("result") val result: Number,
    @SerializedName("invest_primary") val investPrimary: Number,
    @SerializedName("invest_primary_percentage") val investPrimaryPercentage: Number,
    @SerializedName("invest_interest") val investInterest: Number,
    @SerializedName("invest_interest_percentage") val investInterestPercentage: Number
)