package com.pens.planduit.presentation.features.zakatAgriculture.state

import com.pens.planduit.domain.models.entity.RicePrice
import com.pens.planduit.domain.models.entity.ZakatResult

data class AgricultureZakatState(
    val data : ZakatResult = ZakatResult(status = false, zakat = 0),
    val isLoading : Boolean = false,
    val isError : Boolean = false,
)