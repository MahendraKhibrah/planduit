package com.pens.planduit.presentation.features.budgeting

import com.pens.planduit.domain.models.entity.BudgetResult

data class BudgetState(
    val data : BudgetResult? = null,
    val isLoading : Boolean = false,
    val isError : Boolean = false,
)
