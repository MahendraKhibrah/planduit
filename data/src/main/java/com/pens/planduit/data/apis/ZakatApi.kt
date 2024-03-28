package com.pens.planduit.data.apis

import com.pens.planduit.common.dto.CommonDto
import com.pens.planduit.domain.models.entity.GoldPrice
import com.pens.planduit.domain.models.entity.InvestmentResult
import com.pens.planduit.domain.models.entity.RicePrice
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.models.request.AgricultureZakatRequest
import com.pens.planduit.domain.models.request.GoldZakatRequest
import com.pens.planduit.domain.models.request.IncomeZakatRequest
import com.pens.planduit.domain.models.request.InvestmentRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ZakatApi {
    @POST("calculator/zakat-emas")
    suspend fun getGoldZakatCalculation(
        @Body request : GoldZakatRequest
    ): Response<CommonDto<ZakatResult>>

    @POST("calculator/zakat-penghasilan")
    suspend fun getIncomeZakatCalculation(
        @Body request: IncomeZakatRequest
    ): Response<CommonDto<ZakatResult>>

    @GET("c57c7bab-8085-42ec-9371-47cecdc897dc")
    suspend fun getSavingZakatCalculation(
    ): Response<CommonDto<ZakatResult>>

    @GET("info/gold-price")
    suspend fun getGoldPrice(
    ): Response<CommonDto<GoldPrice>>

    @GET("info/grain-price")
    suspend fun getRicePrice(
    ) : Response<CommonDto<RicePrice>>

    @POST("calculator/zakat-pertanian")
    suspend fun getAgricultureZakat(
        @Body request: AgricultureZakatRequest
    ) : Response<CommonDto<ZakatResult>>
}