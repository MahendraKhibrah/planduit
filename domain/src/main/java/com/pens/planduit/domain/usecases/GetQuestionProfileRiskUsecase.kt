package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.RiskProfileQuiz
import com.pens.planduit.domain.models.request.InvestmentRequest
import com.pens.planduit.domain.repositories.GeneralCalculationRepository
import javax.inject.Inject

class GetQuestionProfileRiskUsecase @Inject constructor(
    private val repository: GeneralCalculationRepository
) : BaseUseCase<Resource<List<RiskProfileQuiz>>, Unit> {
    override suspend fun execute(request: Unit): Resource<List<RiskProfileQuiz>> =
        repository.getQuestionRiskProfile()
}