package com.pens.planduit.data.repositories

import android.util.Log
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.data.apis.GeneralCalculationApi
import com.pens.planduit.data.sharedPreferences.GeneralCalculationPref
import com.pens.planduit.domain.models.entity.BudgetResult
import com.pens.planduit.domain.models.entity.InvestmentResult
import com.pens.planduit.domain.models.entity.RecommendationInvestment
import com.pens.planduit.domain.models.request.BudgetingRequest
import com.pens.planduit.domain.models.request.InvestmentRequest
import com.pens.planduit.domain.repositories.GeneralCalculationRepository
import javax.inject.Inject

class GeneralCalculationRepositoryImpl @Inject constructor(
    private val api: GeneralCalculationApi,
    private val sharedPref: GeneralCalculationPref
) : GeneralCalculationRepository {
    override suspend fun getBudgetCalculation(request: BudgetingRequest): Resource<BudgetResult> {
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

    override suspend fun getInvestmentCalculation(request: InvestmentRequest): Resource<InvestmentResult> {
        try {
            val response = api.getInvestmentCalculation(request)
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(
                        InvestmentResult(
                            isSuccess = it.data?.isSuccess ?: false,
                            result = it.data?.result ?: 0,
                            investPrimary = it.data?.investPrimary ?: 0,
                            investPrimaryPercentage = it.data?.investPrimaryPercentage ?: 0.0,
                            investInterest = it.data?.investInterest ?: 0,
                            investInterestPercentage = it.data?.investInterestPercentage ?: 0.0,
                            recommendation = it.data?.recommendation ?: RecommendationInvestment(
                                0,
                                0,
                                0,
                                0.0,
                                0,
                                0.0
                            )
                        )
                    )
                }
            }
            return Resource.Error(response.message())
        } catch (e: Exception) {
            Log.d("GeneralCalculationRepo", e.message ?: "Unknown Error")
            return Resource.Error(e.message ?: "Unknown Error")
        }
    }

    override suspend fun getInvestmentRequest(): InvestmentRequest? {
        return sharedPref.getInvestmentRequest()
    }

    override suspend fun saveInvestmentRequest(request: InvestmentRequest): Boolean {
        sharedPref.saveInvestmentRequest(request)
        return true
    }
}