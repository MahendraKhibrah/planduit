package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.models.request.SavingZakatRequest
import com.pens.planduit.domain.repositories.ZakatRepository
import javax.inject.Inject

class GetSavingZakatCalculationUsecase @Inject constructor(
    private val repository: ZakatRepository
) : BaseUseCase<Resource<ZakatResult>, SavingZakatRequest> {
    override suspend fun execute(request: SavingZakatRequest): Resource<ZakatResult> =
        repository.getSavingZakatCalculation(request)
}