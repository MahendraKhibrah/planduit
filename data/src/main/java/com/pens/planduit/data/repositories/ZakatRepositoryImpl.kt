package com.pens.planduit.data.repositories

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.data.apis.ZakatApi
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.repositories.ZakatRepository
import javax.inject.Inject

class ZakatRepositoryImpl @Inject constructor(
    private val api: ZakatApi
) : ZakatRepository {
    override suspend fun getIncomeZakatCalculation(): Resource<ZakatResult> {
        TODO("Not yet implemented")
    }

    override suspend fun getGoldZakatCalculation(): Resource<ZakatResult> {
        TODO("Not yet implemented")
    }

    override suspend fun getSavingZakatCalculation(): Resource<ZakatResult> {
        TODO("Not yet implemented")
    }
}