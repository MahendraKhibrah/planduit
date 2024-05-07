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
import com.pens.planduit.domain.models.request.SavingZakatRequest
import com.pens.planduit.domain.models.request.TradingZakatRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ZakatApi {
    @POST("calculator/zakat-emas")
    suspend fun getGoldZakatCalculation(
        @Body request : GoldZakatRequest
    ): Response<CommonDto<ZakatResult>>

    @POST("calculator/zakat-penghasilan")
    suspend fun getIncomeZakatCalculation(
        @Body request: IncomeZakatRequest
    ): Response<CommonDto<ZakatResult>>

    @POST("calculator/zakat-tabungan")
    suspend fun getSavingZakatCalculation(
        @Body request: SavingZakatRequest
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

    @POST("calculator/zakat-perdagangan")
    suspend fun getTradingZakat(
        @Body request: TradingZakatRequest
    ) : Response<CommonDto<ZakatResult>>
}