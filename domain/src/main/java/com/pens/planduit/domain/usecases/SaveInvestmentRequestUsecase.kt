package com.pens.planduit.domain.usecases

import com.pens.planduit.domain.models.request.InvestmentRequest
import com.pens.planduit.domain.repositories.GeneralCalculationRepository
import javax.inject.Inject

class SaveInvestmentRequestUsecase @Inject constructor(
    private val repository: GeneralCalculationRepository
) : BaseUseCase<Boolean, InvestmentRequest> {
    override suspend fun execute(request: InvestmentRequest): Boolean =
        repository.saveInvestmentRequest(request)
}