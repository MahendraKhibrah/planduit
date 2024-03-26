package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.models.request.GoldZakatRequest
import com.pens.planduit.domain.repositories.ZakatRepository
import javax.inject.Inject

class GetGoldZakatCalculationUsecase @Inject constructor(
    private val repository: ZakatRepository
) : BaseUseCase<Resource<ZakatResult>, GoldZakatRequest> {
    override suspend fun execute(request: GoldZakatRequest): Resource<ZakatResult> =
        repository.getGoldZakatCalculation(request)
}