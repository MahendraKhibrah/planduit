package com.pens.planduit.data.apis

import com.pens.planduit.common.dto.CommonDto
import com.pens.planduit.domain.models.entity.BudgetResult
import com.pens.planduit.domain.models.entity.InvestmentResult
import com.pens.planduit.domain.models.request.BudgetingRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GeneralCalculationApi {
    @GET("c57c7bab-8085-42ec-9371-47cecdc897dc")
    suspend fun getInvestmentCalculation(
    ): Response<CommonDto<InvestmentResult>>

    @POST("calculator/budgeting-503020")
    suspend fun getBudgetCalculation(
        @Body request: BudgetingRequest
    ): Response<CommonDto<BudgetResult>>
}