package com.pens.planduit.domain.usecases

import com.pens.planduit.domain.models.entity.RatingStatus
import com.pens.planduit.domain.models.entity.toArray
import com.pens.planduit.domain.models.entity.toRatingStatus
import com.pens.planduit.domain.repositories.RatingRepository
import javax.inject.Inject


class SaveRatingStatusUsecase @Inject constructor(
    private val repository: RatingRepository
) : BaseUseCase<Boolean, RatingStatus> {
    override suspend fun execute(request: RatingStatus): Boolean {
        val ratingStatus = repository.getRatingStatus().toArray()
        val requestArray = request.toArray()

        for (i in ratingStatus.indices) {
            if (requestArray[i]) {
                ratingStatus[i] = true
            }
        }

        return repository.saveRatingStatus(ratingStatus.toRatingStatus())
    }
}