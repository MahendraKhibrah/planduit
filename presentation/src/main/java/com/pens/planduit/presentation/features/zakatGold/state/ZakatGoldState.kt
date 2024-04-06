package com.pens.planduit.presentation.features.zakatGold.state

import com.pens.planduit.domain.models.entity.GoldPrice
import com.pens.planduit.domain.models.entity.ZakatResult


data class ZakatGoldState(
    val data : ZakatResult = ZakatResult(status = false, zakat = 0),
    val zakatRequest : Long = 0,
    val isLoading : Boolean = false,
    val isError : Boolean = false,
)