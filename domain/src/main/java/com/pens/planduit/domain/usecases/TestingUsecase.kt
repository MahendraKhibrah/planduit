package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.InvestmentResult
import com.pens.planduit.domain.repositories.TestingRepository
import javax.inject.Inject

class TestingUsecase @Inject constructor(
    private val repository: TestingRepository
) : BaseUseCase<Resource<InvestmentResult?>, Unit> {
    override suspend fun execute(request: Unit): Resource<InvestmentResult?> =
         repository.getInvestmentNotAchieved()
}