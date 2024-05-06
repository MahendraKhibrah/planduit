package com.pens.planduit.presentation.features.dictionary.state

import com.pens.planduit.domain.models.entity.DictionaryItem

data class DictionaryDetailState(
    val isLoading : Boolean = false,
    val isError : Boolean = false,
    val data : DictionaryItem? = null
)
