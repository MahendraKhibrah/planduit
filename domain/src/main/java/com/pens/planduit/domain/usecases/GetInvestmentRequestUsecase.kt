package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.InvestmentResult
import com.pens.planduit.domain.models.request.InvestmentRequest
import com.pens.planduit.domain.repositories.GeneralCalculationRepository
import javax.inject.Inject

class GetInvestmentRequestUsecase @Inject constructor(
    private val repository: GeneralCalculationRepository
) : BaseUseCase<InvestmentRequest?, Unit> {
    override suspend fun execute(request: Unit): InvestmentRequest? =
        repository.getInvestmentRequest()
}