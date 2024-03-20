package com.pens.planduit.domain.usecases

import com.pens.planduit.domain.models.request.InvestmentRequest
import com.pens.planduit.domain.models.request.RiskProfileRequest
import com.pens.planduit.domain.repositories.GeneralCalculationRepository
import javax.inject.Inject

class SaveRiskProfileRequestUsecase @Inject constructor(
    private val repository: GeneralCalculationRepository
) : BaseUseCase<Boolean, RiskProfileRequest> {
    override suspend fun execute(request: RiskProfileRequest): Boolean =
        repository.saveRiskProfileRequest(request)
}