package com.lewan.description_recipe_impl.repo

import com.lewan.core_api.IResult
import com.lewan.core_api.Result
import kotlinx.coroutines.flow.Flow

interface DescriptionRecipeRepository {

    suspend fun getDescriptionRecipe(recipeId: Long): Flow<Result<IResult>>
}