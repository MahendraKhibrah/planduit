package com.pens.planduit.domain.usecases

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.DictionaryItem
import com.pens.planduit.domain.models.request.DictionaryRequest
import com.pens.planduit.domain.repositories.DictionaryRepository
import javax.inject.Inject


class GetDictionaryUsecase @Inject constructor(
    private val repository: DictionaryRepository
) : BaseUseCase<Resource<List<DictionaryItem>>, DictionaryRequest> {
    override suspend fun execute(request: DictionaryRequest): Resource<List<DictionaryItem>> =
        repository.getDictionary(request)
}