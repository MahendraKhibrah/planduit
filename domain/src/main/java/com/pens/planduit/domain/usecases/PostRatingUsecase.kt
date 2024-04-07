package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.request.RatingRequest
import com.pens.planduit.domain.repositories.RatingRepository
import javax.inject.Inject

class PostRatingUsecase @Inject constructor(
    private val repository: RatingRepository
) : BaseUseCase<Resource<Boolean>, RatingRequest> {
    override suspend fun execute(request: RatingRequest): Resource<Boolean> =
        repository.postRating(request)
}