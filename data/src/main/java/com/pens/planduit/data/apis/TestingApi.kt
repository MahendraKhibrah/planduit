package com.pens.planduit.data.apis

import com.pens.planduit.common.dto.CommonDto
import com.pens.planduit.domain.models.entity.InvestmentDto
import retrofit2.Response
import retrofit2.http.GET

interface TestingApi {
    @GET("c57c7bab-8085-42ec-9371-47cecdc897dc")
    suspend fun getInvestmentNotAchieved(
    ): Response<CommonDto<InvestmentDto>>
}