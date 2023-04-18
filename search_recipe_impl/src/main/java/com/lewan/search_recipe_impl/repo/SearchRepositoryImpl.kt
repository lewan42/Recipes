package com.lewan.search_recipe_impl.repo

import com.lewan.core_api.IResult
import com.lewan.core_api.Result
import com.lewan.core_db_api.dao.SearchRecipeDao
import com.lewan.network_api.ApiKey
import com.lewan.search_recipe_impl.RecipeList
import com.lewan.search_recipe_impl.RecipeMappers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchRecipeService,
    private val searchRecipeDao: SearchRecipeDao,
    private val recipeMappers: RecipeMappers,
    private val apiKey: ApiKey
) : SearchRepositoryApi {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getRecipes(query: String): Flow<Result<IResult>> = channelFlow {

        launch {
            runCatching {
                val res = searchService.searchRecipes(
                    xApiKey = apiKey.getApiKey(),
                    query = query,
                    number = 100
                )

                val recipes = res.results.map(recipeMappers::recipeDtoMapToRecipeEntity)

                searchRecipeDao.insertAll(recipes)
            }.onFailure {
                //todo report to crashlytics
            }
        }

        searchRecipeDao.recipesByName(query)
            .map { it.map(recipeMappers::recipeEntityToRecipe) }
            .collect {
                send(Result.Success(RecipeList(it)))
            }
    }
}