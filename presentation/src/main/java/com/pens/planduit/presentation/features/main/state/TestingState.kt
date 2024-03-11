package com.pens.planduit.presentation.features.main.state

import com.pens.planduit.domain.models.entity.InvestmentResult

data class TestingState(
    val data : InvestmentResult? = null,
    val isLoading : Boolean = false,
    val isError : Boolean = false,
)
