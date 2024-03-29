package com.pens.planduit.domain.repositories

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.InvestmentResult

interface TestingRepository {
    suspend fun getInvestmentNotAchieved(): Resource<InvestmentResult?>
}

