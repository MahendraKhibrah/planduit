package com.pens.planduit.data.repositories

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.data.apis.GeneralCalculationApi
import com.pens.planduit.domain.models.entity.BudgetResult
import com.pens.planduit.domain.models.entity.InvestmentResult
import com.pens.planduit.domain.models.request.BudgetingRequest
import com.pens.planduit.domain.repositories.GeneralCalculationRepository
import javax.inject.Inject

class GeneralCalculationRepositoryImpl @Inject constructor(
    private val api: GeneralCalculationApi,
) : GeneralCalculationRepository {
    override suspend fun getBudgetCalculation(request : BudgetingRequest): Resource<BudgetResult> {
        val response = api.getBudgetCalculation(request)
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(
                    BudgetResult(
                        it.data?.income ?: 0,
                    it.data?.needs ?: 0,
                    it.data?.wants ?: 0,
                    it.data?.savings ?: 0
                    )
                )
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getInvestmentCalculation(): Resource<InvestmentResult> {
        TODO("Not yet implemented")
    }
}