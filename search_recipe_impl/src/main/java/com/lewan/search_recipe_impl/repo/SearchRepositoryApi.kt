package com.lewan.search_recipe_impl.repo

import com.lewan.core_api.IResult
import com.lewan.core_api.Result
import kotlinx.coroutines.flow.Flow

interface SearchRepositoryApi {

     fun getRecipes(query: String=""): Flow<Result<IResult>>
}