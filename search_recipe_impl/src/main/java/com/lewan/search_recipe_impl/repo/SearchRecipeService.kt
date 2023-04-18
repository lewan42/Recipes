package com.lewan.search_recipe_impl.repo

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchRecipeService {

    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Header("x-api-key") xApiKey: String,
        @Query("query") query: String,
        @Query("number") number: Int,
    ): RecipeDto
}