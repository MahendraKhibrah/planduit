package com.pens.planduit.domain.models.request

data class SavingZakatRequest(
    val savings: Int,
    val bank: Boolean,
    val interest : Int
)
