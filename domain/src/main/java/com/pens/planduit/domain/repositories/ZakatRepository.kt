package com.pens.planduit.domain.repositories

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.GoldPrice
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.models.request.IncomeZakatRequest

interface ZakatRepository {
    suspend fun getIncomeZakatCalculation(request : IncomeZakatRequest): Resource<ZakatResult>
    suspend fun getGoldZakatCalculation(): Resource<ZakatResult>
    suspend fun getSavingZakatCalculation(): Resource<ZakatResult>
    suspend fun getGoldPrice(): Resource<GoldPrice>
}