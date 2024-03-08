package com.pens.planduit.domain.repositories

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.ZakatResult

interface ZakatRepository {
    suspend fun getIncomeZakatCalculation(): Resource<ZakatResult>
    suspend fun getGoldZakatCalculation(): Resource<ZakatResult>
    suspend fun getSavingZakatCalculation(): Resource<ZakatResult>
}