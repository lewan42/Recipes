package com.lewan.convert_impl.repo

import com.lewan.core_api.IResult
import com.lewan.core_api.Result

interface ConvertRepository {

    suspend fun convert(
        ingredientName: String,
        sourceAmount: Double,
        sourceUnit: String,
        targetUnit: String,
    ): Result<IResult>
}