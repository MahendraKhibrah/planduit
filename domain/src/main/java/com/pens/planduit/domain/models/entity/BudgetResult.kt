package com.pens.planduit.domain.models.entity

data class BudgetResult(
    val income: Int,
    val needs: Int,
    val wants: Int,
    val savings: Int
)