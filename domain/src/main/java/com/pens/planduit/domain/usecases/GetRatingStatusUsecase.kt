package com.pens.planduit.domain.usecases

import android.util.Log
import com.pens.planduit.domain.models.entity.toArray
import com.pens.planduit.domain.repositories.RatingRepository
import javax.inject.Inject

class GetRatingStatusUsecase @Inject constructor(
    private val repository: RatingRepository
) : BaseUseCase<Boolean, Unit> {
    override suspend fun execute(request: Unit): Boolean {
        val ratingStatus = repository.getRatingStatus().toArray()

        var trueCount = 0
        for (status in ratingStatus) {
            if (status) {
                trueCount++
            }
        }
        return trueCount >= 3 && !ratingStatus.last()
    }
}