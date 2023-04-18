package com.lewan.description_recipe_impl.repo

import com.lewan.core_api.IResult
import com.lewan.core_api.Result
import com.lewan.core_db_api.dao.DescriptionRecipeDao
import com.lewan.description_recipe_impl.Mapper
import com.lewan.network_api.ApiKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class DescriptionRecipeRepositoryImpl @Inject constructor(
    private val descriptionRecipeDao: DescriptionRecipeDao,
    private val descriptionRecipeService: DescriptionRecipeService,
    private val apiKey: ApiKey,
) : DescriptionRecipeRepository {

    override suspend fun getDescriptionRecipe(recipeId: Long): Flow<Result<IResult>> = channelFlow {
        launch {
            runCatching {
                val res = descriptionRecipeService.getRecipeInfoById(
                    apiKey.getApiKey(),
                    recipeId
                )
                val recipe = Mapper.mapDetailRecipeDtoToDetailRecipeEntity(res)

                descriptionRecipeDao.insert(recipe)
            }.onFailure {
                it.printStackTrace()
            }
        }

        launch {
            runCatching {
                descriptionRecipeDao.getRecipeInfoById(recipeId).map {
                    val t = it?.let { it1 -> Mapper.mapDetailRecipeEntityToRecipeInfo(it1) }
                    t
                }.filterNotNull().collectLatest {
                    send(Result.Success(it))
                }
            }.onFailure {
                send(Result.Error(it.localizedMessage))
            }
        }
    }
}