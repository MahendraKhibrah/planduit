package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.GoldPrice
import com.pens.planduit.domain.repositories.ZakatRepository
import javax.inject.Inject


class GetGoldPriceUsecase @Inject constructor(
    private val repository: ZakatRepository
) : BaseUseCase<Resource<GoldPrice>, Unit> {
    override suspend fun execute(request: Unit): Resource<GoldPrice> =
        repository.getGoldPrice()
}