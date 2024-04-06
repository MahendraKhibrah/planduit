package com.pens.planduit.data.apis

import com.pens.planduit.common.dto.CommonDto
import com.pens.planduit.domain.models.entity.InvestmentResult
import com.pens.planduit.domain.models.request.InvestmentRequest
import com.pens.planduit.domain.models.request.RatingRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RatingApi {
    @POST("common/rating")
    suspend fun postRating(
        @Body request: RatingRequest
    ): Response<CommonDto<Unit>>
}