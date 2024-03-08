package com.pens.planduit.data.apis

import com.pens.planduit.common.dto.CommonDto
import com.pens.planduit.domain.models.entity.BudgetResult
import com.pens.planduit.domain.models.entity.InvestmentResult
import retrofit2.Response
import retrofit2.http.GET

interface GeneralCalculationApi {
    @GET("c57c7bab-8085-42ec-9371-47cecdc897dc")
    suspend fun getInvestmentCalculation(
    ): Response<CommonDto<InvestmentResult>>

    @GET("c57c7bab-8085-42ec-9371-47cecdc897dc")
    suspend fun getBudgetCalculation(
    ): Response<CommonDto<BudgetResult>>
}