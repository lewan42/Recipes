package com.lewan.description_recipe_impl.repo

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface DescriptionRecipeService {

    @GET("recipes/{id}/information")
    suspend fun getRecipeInfoById(
        @Header("x-api-key") xApiKey: String,
        @Path("id") id: Long,
    ): DescriptionRecipeDto
}