package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.RiskProfileResult
import com.pens.planduit.domain.repositories.GeneralCalculationRepository
import javax.inject.Inject

class GetRiskProfileResultUsecase @Inject constructor(
    private val repository: GeneralCalculationRepository
) : BaseUseCase<Resource<RiskProfileResult>, Unit> {
    override suspend fun execute(request: Unit): Resource<RiskProfileResult> =
        repository.getRiskProfileCalculation()
}