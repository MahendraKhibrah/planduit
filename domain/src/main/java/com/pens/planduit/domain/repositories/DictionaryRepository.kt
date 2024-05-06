package com.pens.planduit.domain.repositories

import com.pens.planduit.common.utils.Resource
import com.pens.planduit.domain.models.entity.DictionaryItem
import com.pens.planduit.domain.models.request.CommonIdRequest
import com.pens.planduit.domain.models.request.DictionaryRequest


interface DictionaryRepository {
    suspend fun getDictionary(request : DictionaryRequest): Resource<List<DictionaryItem>>
    suspend fun getDictionaryById(request : CommonIdRequest): Resource<DictionaryItem>
}