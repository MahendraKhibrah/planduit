package com.pens.planduit.presentation.features.state

import com.pens.planduit.domain.models.entity.InvestmentDto

data class TestingState(
    val data : InvestmentDto? = null,
    val isLoading : Boolean = false,
    val isError : Boolean = false,
)
