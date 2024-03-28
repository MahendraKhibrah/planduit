package com.pens.planduit.presentation.features.zakatAgriculture.state

import com.pens.planduit.domain.models.entity.RicePrice


data class RicePriceState(
    val data : RicePrice = RicePrice(price = 0),
    val isLoading : Boolean = false,
    val isError : Boolean = false,
)

