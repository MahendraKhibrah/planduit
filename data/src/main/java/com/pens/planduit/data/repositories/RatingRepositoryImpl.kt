package com.pens.planduit.data.repositories

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.data.apis.RatingApi
import com.pens.planduit.data.sharedPreferences.RatingPref
import com.pens.planduit.domain.models.entity.RatingStatus
import com.pens.planduit.domain.models.request.RatingRequest
import com.pens.planduit.domain.repositories.RatingRepository
import javax.inject.Inject

class RatingRepositoryImpl @Inject constructor(
    private val sharedPref: RatingPref,
    private val api: RatingApi
) : RatingRepository {
    override suspend fun getRatingStatus(): RatingStatus {
        return sharedPref.getRatingStatus() ?: RatingStatus(
            isZakatGoldFilled = false,
            isZakatIncomeFilled = false,
            isZakatTrading = false,
            isZakatSaving = false,
            isZakatAgricultureFilled = false,
            isBudgetingFilled = false,
            isInvestmentFilled = false,
            isRiskProfileFilled = false
        )
    }

    override suspend fun saveRatingStatus(ratingStatus: RatingStatus): Boolean {
        sharedPref.saveRatingStatus(ratingStatus)
        return true
    }

    override suspend fun postRating(request: RatingRequest): Resource<Boolean> {
        try {
            val response = api.postRating(request)
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(true)
                }
            }
            return Resource.Error(response.message())
        } catch (e: Exception) {
            return Resource.Error(e.message ?: "An error occurred")
        }
    }
}