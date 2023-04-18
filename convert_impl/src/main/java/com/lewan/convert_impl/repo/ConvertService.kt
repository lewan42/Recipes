package com.lewan.convert_impl.repo

import com.lewan.convert_impl.ConvertDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ConvertService {

    @GET("recipes/convert")
    suspend fun convert(
        @Header("x-api-key") xApiKey: String,
        @Query("ingredientName") query: String,
        @Query("sourceAmount") sourceAmount: Double,
        @Query("sourceUnit") sourceUnit: String,
        @Query("targetUnit") targetUnit: String,
    ): ConvertDto
}