package com.pens.planduit.presentation.features.dictionary.state

import com.pens.planduit.domain.models.entity.DictionaryItem

data class DictionaryState(
    val isLoading : Boolean = false,
    val isError : Boolean = false,
    val data : List<DictionaryItem> = emptyList()
)
