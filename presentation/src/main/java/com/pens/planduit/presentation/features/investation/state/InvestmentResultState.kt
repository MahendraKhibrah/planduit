package com.pens.planduit.presentation.features.investation.state

import com.pens.planduit.domain.models.entity.InvestmentResult
import com.pens.planduit.domain.models.request.InvestmentRequest

data class InvestmentResultState(
    val investmentResult : InvestmentResult? = null,
    val investmentRequest : InvestmentRequest? = null,
    val isLoading : Boolean = false,
    val isError : Boolean = false,
)
