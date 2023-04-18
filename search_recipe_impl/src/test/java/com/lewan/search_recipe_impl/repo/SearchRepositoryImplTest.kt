package com.lewan.search_recipe_impl.repo

import app.cash.turbine.test
import com.lewan.core_api.Result
import com.lewan.core_db_api.dao.SearchRecipeDao
import com.lewan.network_api.ApiKey
import com.lewan.search_recipe_impl.RecipeList
import com.lewan.search_recipe_impl.RecipeMappers
import com.lewan.search_recipe_impl.recipe1
import com.lewan.search_recipe_impl.recipe2
import com.lewan.search_recipe_impl.recipe3
import com.lewan.search_recipe_impl.recipeEntity1
import com.lewan.search_recipe_impl.recipeEntity2
import com.lewan.search_recipe_impl.recipeEntity3
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SearchRepositoryImplTest {

    private val searchService: SearchRecipeService = mock()
    private val searchRecipeDao: SearchRecipeDao = mock()
    private val recipeMappers: RecipeMappers = mock()
    private val apiKey: ApiKey = mock()

    private val repository =
        SearchRepositoryImpl(searchService, searchRecipeDao, recipeMappers, apiKey)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test() = runTest {

        val recipe1 = recipe1
        val recipe2 = recipe2
        val recipe3 = recipe3

        val recipeEntity1 = recipeEntity1
        val recipeEntity2 = recipeEntity2
        val recipeEntity3 = recipeEntity3

        val recipesEntity = listOf(recipeEntity1, recipeEntity2, recipeEntity3)
        val recipesFlow = flow { emit(recipesEntity) }

        whenever(searchRecipeDao.recipesByName(any())).thenReturn(recipesFlow)

        whenever(recipeMappers.recipeEntityToRecipe(recipeEntity1)).thenReturn(recipe1)
        whenever(recipeMappers.recipeEntityToRecipe(recipeEntity2)).thenReturn(recipe2)
        whenever(recipeMappers.recipeEntityToRecipe(recipeEntity3)).thenReturn(recipe3)

        val actual = Result.Success(RecipeList(listOf(recipe1, recipe2, recipe3)))

        repository.getRecipes("").test {
            TestCase.assertEquals(actual, awaitItem())
            awaitComplete()
        }

        verify(searchRecipeDao).recipesByName(any())
        verify(recipeMappers, times(3)).recipeEntityToRecipe(any())
    }
}