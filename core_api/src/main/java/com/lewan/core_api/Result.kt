package com.lewan.core_api

interface IResult

sealed class Result<out T : IResult> {

    data class Success<R : IResult>(val data: R) : Result<R>()

    data class Error(
        val message: String?
    ) : Result<Nothing>()

    object Loading : Result<Nothing>()

    object Starting : Result<Nothing>()
}