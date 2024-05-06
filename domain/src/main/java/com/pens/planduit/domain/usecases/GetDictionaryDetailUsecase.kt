package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.DictionaryItem
import com.pens.planduit.domain.models.request.CommonIdRequest
import com.pens.planduit.domain.repositories.DictionaryRepository
import javax.inject.Inject

class GetDictionaryDetailUsecase @Inject constructor(
    private val repository: DictionaryRepository
) : BaseUseCase<Resource<DictionaryItem>, CommonIdRequest> {
    override suspend fun execute(request: CommonIdRequest): Resource<DictionaryItem> =
        repository.getDictionaryById(request)
}