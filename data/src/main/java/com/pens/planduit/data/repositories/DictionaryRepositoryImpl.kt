package com.pens.planduit.data.repositories

import android.util.Log
import com.pens.planduit.common.utils.Resource
import com.pens.planduit.data.apis.DictionaryApi
import com.pens.planduit.domain.models.entity.DictionaryItem
import com.pens.planduit.domain.models.request.CommonIdRequest
import com.pens.planduit.domain.models.request.DictionaryRequest
import com.pens.planduit.domain.repositories.DictionaryRepository
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val api: DictionaryApi
) : DictionaryRepository {
    override suspend fun getDictionary(request: DictionaryRequest): Resource<List<DictionaryItem>> {
        try {
            val response = api.getDictionary(
                group = request.group,
                search = request.search,
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(
                        it.data ?: emptyList()
                    )
                }
            }
            return Resource.Error(response.message())
        } catch (e: Exception) {
            Log.d("DictionaryRepository", "getDictionary: ${e.message}")
            return Resource.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun getDictionaryById(request: CommonIdRequest): Resource<DictionaryItem> {
        try {
            val response = api.getDictionaryById(
                id = request.id
            )
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(
                        it.data ?: DictionaryItem(
                            id = 0,
                            title = "",
                            description = "",
                        )
                    )
                }
            }
            return Resource.Error(response.message())
        } catch (e: Exception) {
            Log.d("DictionaryRepository", "getDictionaryById: ${e.message}")
            return Resource.Error(e.message ?: "An error occurred")
        }
    }
}