package com.pens.planduit.domain.models.request

data class DictionaryRequest(
    val group : String,
    val search : String = ""
)
