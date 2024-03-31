package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.models.request.TradingZakatRequest
import com.pens.planduit.domain.repositories.ZakatRepository
import javax.inject.Inject

class GetTradingZakatUsecase @Inject constructor(
    private val repository: ZakatRepository
) : BaseUseCase<Resource<ZakatResult>, TradingZakatRequest> {
    override suspend fun execute(request: TradingZakatRequest): Resource<ZakatResult> =
        repository.getTradingZakat(request)
}