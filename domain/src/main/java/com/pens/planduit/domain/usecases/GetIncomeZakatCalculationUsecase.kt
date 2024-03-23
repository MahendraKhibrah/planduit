package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.models.request.IncomeZakatRequest
import com.pens.planduit.domain.repositories.ZakatRepository
import javax.inject.Inject

class GetIncomeZakatCalculationUsecase @Inject constructor(
    private val repository: ZakatRepository
) : BaseUseCase<Resource<ZakatResult>, IncomeZakatRequest> {
    override suspend fun execute(request: IncomeZakatRequest): Resource<ZakatResult> =
        repository.getIncomeZakatCalculation(request)
}