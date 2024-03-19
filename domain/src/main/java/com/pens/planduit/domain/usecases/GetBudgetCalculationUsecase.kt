package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.BudgetResult
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.models.request.BudgetingRequest
import com.pens.planduit.domain.repositories.GeneralCalculationRepository
import com.pens.planduit.domain.repositories.ZakatRepository
import javax.inject.Inject

class GetBudgetCalculationUsecase @Inject constructor(
    private val repository: GeneralCalculationRepository
) : BaseUseCase<Resource<BudgetResult>, BudgetingRequest> {
    override suspend fun execute(request: BudgetingRequest): Resource<BudgetResult> =
        repository.getBudgetCalculation(request)
}