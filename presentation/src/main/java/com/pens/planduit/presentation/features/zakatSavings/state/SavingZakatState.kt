package com.pens.planduit.presentation.features.zakatSavings.state

import com.pens.planduit.domain.models.entity.ZakatResult

data class SavingZakatState(
    val data: ZakatResult = ZakatResult(status = false, zakat = 0),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)