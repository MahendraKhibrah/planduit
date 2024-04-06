package com.pens.planduit.domain.repositories

import com.pens.planduit.domain.models.entity.RatingStatus

interface RatingRepository {
    suspend fun getRatingStatus(): RatingStatus

    suspend fun saveRatingStatus(ratingStatus: RatingStatus): Boolean
}