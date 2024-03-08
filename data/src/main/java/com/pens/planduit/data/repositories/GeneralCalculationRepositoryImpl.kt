package com.pens.planduit.data.repositories

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.data.apis.GeneralCalculationApi
import com.pens.planduit.domain.models.entity.BudgetResult
import com.pens.planduit.domain.models.entity.InvestmentResult
import com.pens.planduit.domain.repositories.GeneralCalculationRepository
import javax.inject.Inject

class GeneralCalculationRepositoryImpl @Inject constructor(
    private val api: GeneralCalculationApi
) : GeneralCalculationRepository {
    override suspend fun getBudgetCalculation(): Resource<BudgetResult> {
        TODO("Not yet implemented")
    }

    override suspend fun getInvestmentCalculation(): Resource<InvestmentResult> {
        TODO("Not yet implemented")
    }
}