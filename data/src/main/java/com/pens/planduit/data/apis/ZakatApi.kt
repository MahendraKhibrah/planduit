package com.pens.planduit.data.apis

import com.pens.planduit.common.dto.CommonDto
import com.pens.planduit.domain.models.entity.InvestmentResult
import com.pens.planduit.domain.models.entity.ZakatResult
import retrofit2.Response
import retrofit2.http.GET

interface ZakatApi {
    @GET("c57c7bab-8085-42ec-9371-47cecdc897dc")
    suspend fun getGoldZakatCalculation(
    ): Response<CommonDto<ZakatResult>>

    @GET("c57c7bab-8085-42ec-9371-47cecdc897dc")
    suspend fun getIncomeZakatCalculation(
    ): Response<CommonDto<ZakatResult>>

    @GET("c57c7bab-8085-42ec-9371-47cecdc897dc")
    suspend fun getSavingZakatCalculation(
    ): Response<CommonDto<ZakatResult>>
}