package com.pens.planduit.common.dto

data class CommonDto<T>(
    val data: T? = null,
    val message: String? = null,
)
