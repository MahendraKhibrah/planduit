package com.pens.planduit.presentation.features.zakatIncome.state

import com.pens.planduit.domain.models.entity.ZakatResult

data class ZIncomeResultState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val data: ZakatResult = ZakatResult(status = true, zakat = 0)
)
