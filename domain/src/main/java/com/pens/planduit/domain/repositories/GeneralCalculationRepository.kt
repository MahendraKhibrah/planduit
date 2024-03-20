package com.pens.planduit.domain.repositories

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.BudgetResult
import com.pens.planduit.domain.models.entity.InvestmentResult
import com.pens.planduit.domain.models.entity.RiskProfileQuiz
import com.pens.planduit.domain.models.entity.RiskProfileResult
import com.pens.planduit.domain.models.request.BudgetingRequest
import com.pens.planduit.domain.models.request.InvestmentRequest
import com.pens.planduit.domain.models.request.RiskProfileRequest

interface GeneralCalculationRepository {
    suspend fun getBudgetCalculation(request : BudgetingRequest): Resource<BudgetResult>
    suspend fun getInvestmentCalculation(request: InvestmentRequest): Resource<InvestmentResult>

    suspend fun getInvestmentRequest(): InvestmentRequest?

    suspend fun saveInvestmentRequest(request: InvestmentRequest) : Boolean

    suspend fun getQuestionRiskProfile(): Resource<List<RiskProfileQuiz>>

    suspend fun getRiskProfileRequest(): RiskProfileRequest?

    suspend fun saveRiskProfileRequest(request: RiskProfileRequest) : Boolean

    suspend fun getRiskProfileCalculation() : Resource<RiskProfileResult>
}