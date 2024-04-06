package com.pens.planduit.presentation.features.main.state


data class RatingState(
    val data : Boolean? = null,
    val isLoading : Boolean = false,
    val isError : Boolean = false,
)
