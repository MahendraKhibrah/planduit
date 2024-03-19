package com.pens.planduit.domain.repositories

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.BudgetResult
import com.pens.planduit.domain.models.entity.InvestmentResult
import com.pens.planduit.domain.models.request.BudgetingRequest

interface GeneralCalculationRepository {
    suspend fun getBudgetCalculation(request : BudgetingRequest): Resource<BudgetResult>
    suspend fun getInvestmentCalculation(): Resource<InvestmentResult>
}