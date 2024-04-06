package com.pens.planduit.domain.repositories

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.RatingStatus
import com.pens.planduit.domain.models.request.RatingRequest

interface RatingRepository {
    suspend fun getRatingStatus(): RatingStatus

    suspend fun saveRatingStatus(ratingStatus: RatingStatus): Boolean

    suspend fun postRating(request : RatingRequest) : Resource<Boolean>
}