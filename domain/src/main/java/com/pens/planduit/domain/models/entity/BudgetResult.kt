package com.pens.planduit.domain.models.entity

data class BudgetResult(
    val income: Long,
    val needs: Long,
    val wants: Long,
    val savings: Long
)