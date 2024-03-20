package com.pens.planduit.domain.repositories

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.BudgetResult
import com.pens.planduit.domain.models.entity.InvestmentResult
import com.pens.planduit.domain.models.entity.RiskProfileQuiz
import com.pens.planduit.domain.models.request.BudgetingRequest
import com.pens.planduit.domain.models.request.InvestmentRequest

interface GeneralCalculationRepository {
    suspend fun getBudgetCalculation(request : BudgetingRequest): Resource<BudgetResult>
    suspend fun getInvestmentCalculation(request: InvestmentRequest): Resource<InvestmentResult>

    suspend fun getInvestmentRequest(): InvestmentRequest?

    suspend fun saveInvestmentRequest(request: InvestmentRequest) : Boolean

    suspend fun getQuestionRiskProfile(): Resource<List<RiskProfileQuiz>>
}