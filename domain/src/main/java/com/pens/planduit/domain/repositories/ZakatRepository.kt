package com.pens.planduit.domain.repositories

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.GoldPrice
import com.pens.planduit.domain.models.entity.RicePrice
import com.pens.planduit.domain.models.entity.ZakatResult
import com.pens.planduit.domain.models.request.AgricultureZakatRequest
import com.pens.planduit.domain.models.request.GoldZakatRequest
import com.pens.planduit.domain.models.request.IncomeZakatRequest
import com.pens.planduit.domain.models.request.SavingZakatRequest
import com.pens.planduit.domain.models.request.TradingZakatRequest

interface ZakatRepository {
    suspend fun getIncomeZakatCalculation(request : IncomeZakatRequest): Resource<ZakatResult>
    suspend fun getGoldZakatCalculation(request : GoldZakatRequest): Resource<ZakatResult>
    suspend fun getSavingZakatCalculation(request : SavingZakatRequest): Resource<ZakatResult>
    suspend fun getGoldPrice(): Resource<GoldPrice>
    suspend fun getRicePrice():Resource<RicePrice>
    suspend fun getAgricultureZakat(request: AgricultureZakatRequest) : Resource<ZakatResult>
    suspend fun getTradingZakat(request: TradingZakatRequest) : Resource<ZakatResult>
}