package com.pens.planduit.domain.usecases

interface BaseUseCase<RES, REQ>{
    suspend fun execute(request: REQ): RES
}