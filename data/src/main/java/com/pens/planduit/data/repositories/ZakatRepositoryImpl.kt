package com.pens.planduit.data.repositories

import android.util.Log
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.data.apis.ZakatApi
import com.pens.planduit.domain.models.entity.GoldPrice
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.models.request.IncomeZakatRequest
import com.pens.planduit.domain.repositories.ZakatRepository
import javax.inject.Inject

class ZakatRepositoryImpl @Inject constructor(
    private val api: ZakatApi
) : ZakatRepository {
    override suspend fun getIncomeZakatCalculation(request : IncomeZakatRequest): Resource<ZakatResult> {
        try {
            val response = api.getIncomeZakatCalculation(request)
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(
                        ZakatResult(
                            it.data?.status ?: true,
                            it.data?.zakat ?: 0
                        )
                    )
                }
            }
            return Resource.Error(response.message())
        } catch (e: Exception) {
            Log.d("ZakatRepositoryImpl", "getIncomeZakatCalculation: ${e.message}")
            return Resource.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun getGoldZakatCalculation(): Resource<ZakatResult> {
        TODO("Not yet implemented")
    }

    override suspend fun getSavingZakatCalculation(): Resource<ZakatResult> {
        TODO("Not yet implemented")
    }

    override suspend fun getGoldPrice(): Resource<GoldPrice> {
        try {
            val response = api.getGoldPrice()
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(
                        GoldPrice(
                            it.data?.price ?: 0
                        )
                    )
                }
            }
            return Resource.Error(response.message())
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "An error occurred")
        }
    }
}