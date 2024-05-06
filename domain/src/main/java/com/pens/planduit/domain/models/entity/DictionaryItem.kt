package com.pens.planduit.domain.models.entity

data class DictionaryItem(
    val id : Int,
    val title : String,
    val description : String = ""
)
