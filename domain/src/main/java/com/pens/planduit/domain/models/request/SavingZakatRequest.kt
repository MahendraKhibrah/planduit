package com.pens.planduit.domain.models.request

data class SavingZakatRequest(
    val savings: Number,
    val bank: Boolean,
    val interest : Int
)
