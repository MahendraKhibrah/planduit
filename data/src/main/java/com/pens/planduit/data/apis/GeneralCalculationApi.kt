package com.pens.planduit.data.apis

import com.pens.planduit.common.dto.CommonDto
import com.pens.planduit.domain.models.entity.BudgetResult
import com.pens.planduit.domain.models.entity.InvestmentResult
import com.pens.planduit.domain.models.entity.RiskProfileQuiz
import com.pens.planduit.domain.models.entity.RiskProfileResult
import com.pens.planduit.domain.models.request.BudgetingRequest
import com.pens.planduit.domain.models.request.InvestmentRequest
import com.pens.planduit.domain.models.request.RiskProfileRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GeneralCalculationApi {
    @POST("calculator/investment")
    suspend fun getInvestmentCalculation(
        @Body request: InvestmentRequest
    ): Response<CommonDto<InvestmentResult>>

    @POST("calculator/budgeting-503020")
    suspend fun getBudgetCalculation(
        @Body request: BudgetingRequest
    ): Response<CommonDto<BudgetResult>>

    @GET("calculator/profile-resiko")
    suspend fun getRiskProfileQuestion(): Response<CommonDto<List<RiskProfileQuiz>>>

    @POST("calculator/profile-resiko")
    suspend fun getRiskProfileCalculation(
        @Body request: RiskProfileRequest
    ): Response<CommonDto<RiskProfileResult>>
}