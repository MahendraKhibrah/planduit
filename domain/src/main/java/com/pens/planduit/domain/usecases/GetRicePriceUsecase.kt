package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.RicePrice
import com.pens.planduit.domain.repositories.ZakatRepository
import javax.inject.Inject

class GetRicePriceUsecase @Inject constructor(
    private val repository: ZakatRepository
) : BaseUseCase<Resource<RicePrice>, Unit> {
    override suspend fun execute(request: Unit): Resource<RicePrice> =
        repository.getRicePrice()
}