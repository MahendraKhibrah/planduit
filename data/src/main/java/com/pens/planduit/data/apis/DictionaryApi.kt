package com.pens.planduit.data.apis

import com.pens.planduit.common.dto.CommonDto
import com.pens.planduit.domain.models.entity.DictionaryItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DictionaryApi {
    @GET("dictionary")
    suspend fun getDictionary(
        @Query("group") group: String,
        @Query("search") search: String
    ): Response<CommonDto<List<DictionaryItem>>>

    @GET("dictionary/{id}")
    suspend fun getDictionaryById(
        @Path(value="id") id: Int
    ): Response<CommonDto<DictionaryItem>>
}
