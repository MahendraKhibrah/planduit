package com.pens.planduit.presentation.features.riskProfile.state

import com.pens.planduit.domain.models.entity.RiskProfileResult

data class RiskProfileResultState(
    val data : RiskProfileResult? = null,
    val isLoading : Boolean = false,
    val isError : Boolean = false,
)
