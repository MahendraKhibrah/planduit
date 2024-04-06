package com.pens.planduit.domain.models.request

data class SavingZakatRequest(
    val savings: Long,
    val bank: Boolean,
    val interest : Int
)
