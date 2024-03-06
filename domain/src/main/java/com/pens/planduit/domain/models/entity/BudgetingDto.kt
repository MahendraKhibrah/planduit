package com.pens.planduit.domain.models.entity

data class BudgetingDto(
    val income: Int,
    val needs: Int,
    val wants: Int,
    val savings: Int
)