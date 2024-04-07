package com.pens.planduit.domain.models.entity

data class BudgetResult(
    val income: Number,
    val needs: Number,
    val wants: Number,
    val savings: Number
)