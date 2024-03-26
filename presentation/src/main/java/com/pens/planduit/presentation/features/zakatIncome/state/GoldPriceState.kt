package com.pens.planduit.presentation.features.zakatIncome.state

import com.pens.planduit.domain.models.entity.GoldPrice
import com.pens.planduit.domain.models.entity.RiskProfileQuiz

data class GoldPriceState(
    val data : GoldPrice = GoldPrice(price = 0),
    val isLoading : Boolean = false,
    val isError : Boolean = false,
)
