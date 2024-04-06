package com.pens.planduit.data.repositories

import com.pens.planduit.data.sharedPreferences.RatingPref
import com.pens.planduit.domain.models.entity.RatingStatus
import com.pens.planduit.domain.repositories.RatingRepository
import javax.inject.Inject

class RatingRepositoryImpl @Inject constructor(
    private val sharedPref: RatingPref
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
}