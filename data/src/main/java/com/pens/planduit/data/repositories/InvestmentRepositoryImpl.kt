package com.pens.planduit.data.repositories

import android.util.Log
import com.pens.planduit.common.extension.tryParse
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.data.apis.TestingApi
import com.pens.planduit.domain.models.entity.InvestmentResult
import com.pens.planduit.domain.repositories.TestingRepository
import javax.inject.Inject

class TestingRepositoryImpl @Inject constructor(
    private val api: TestingApi
) : TestingRepository {
    override suspend fun getInvestmentNotAchieved(): Resource<InvestmentResult?> =
        try {
            api.getInvestmentNotAchieved().tryParse {
                return Resource.Success(it?.data)
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
}
