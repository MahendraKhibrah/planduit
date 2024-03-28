package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.models.request.AgricultureZakatRequest
import com.pens.planduit.domain.repositories.ZakatRepository
import javax.inject.Inject

class GetAgricultureZakatUsecase @Inject constructor(
    private val repository: ZakatRepository
) : BaseUseCase<Resource<ZakatResult>, AgricultureZakatRequest> {
    override suspend fun execute(request: AgricultureZakatRequest): Resource<ZakatResult> =
        repository.getAgricultureZakat(request)
}