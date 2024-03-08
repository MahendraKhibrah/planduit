package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.BudgetResult
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.repositories.GeneralCalculationRepository
import com.pens.planduit.domain.repositories.ZakatRepository
import javax.inject.Inject

class GetBudgetCalculationUsecase @Inject constructor(
    private val repository: GeneralCalculationRepository
) : BaseUseCase<Resource<BudgetResult>, Unit> {
    override suspend fun execute(request: Unit): Resource<BudgetResult> =
        repository.getBudgetCalculation()
}