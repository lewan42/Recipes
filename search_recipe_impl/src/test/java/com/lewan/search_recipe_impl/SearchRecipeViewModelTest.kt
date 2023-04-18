package com.lewan.search_recipe_impl

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.lewan.core_api.Result
import com.lewan.description_recipe_api.DescriptionRecipeMediator
import com.lewan.main_api.MainContainer
import com.lewan.search_recipe_impl.repo.SearchRepositoryApi
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.annotation.Config

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@Config(
    instrumentedPackages = ["androidx.loader.content"]
)
class SearchRecipeViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutinesTestRule()

    private val repository: SearchRepositoryApi = mock()
    private val mediator: DescriptionRecipeMediator = mock()
    private val mainContainer: MainContainer = mock()

    @Test
    fun `testing screen state`() = runTest {

        val viewModel = SearchRecipeViewModel(repository, mediator, mainContainer)

        val query1 = "p"
        val query2 = "pa"
        val query3 = "pas"
        val query4 = "pass"

        val expected1 = Result.Success(recipeList1)
        val expected2 = Result.Success(recipeList2)
        val expected3 = Result.Success(recipeList3)
        val expected4 = Result.Success(recipeList4)

        val flow1 = flow { emit(expected1) }
        val flow2 = flow { emit(expected2) }
        val flow3 = flow { emit(expected3) }
        val flow4 = flow { emit(expected4) }

        whenever(repository.getRecipes(query1)).thenReturn(flow1)
        whenever(repository.getRecipes(query2)).thenReturn(flow2)
        whenever(repository.getRecipes(query3)).thenReturn(flow3)
        whenever(repository.getRecipes(query4)).thenReturn(flow4)

        viewModel.stateScreen.test {
            viewModel.searchRecipesByName(query1)
            delay(399)
            viewModel.searchRecipesByName(query2)
            delay(399)
            viewModel.searchRecipesByName(query1)
            delay(399)
            viewModel.searchRecipesByName(query2)
            delay(401)
            assertEquals(Result.Loading, awaitItem())
            assertEquals(expected2, awaitItem())

            viewModel.searchRecipesByName(query1)
            assertEquals(Result.Loading, awaitItem())
            assertEquals(expected1, awaitItem())

            viewModel.searchRecipesByName(query2)
            viewModel.searchRecipesByName(query3)
            assertEquals(Result.Loading, awaitItem())
            assertEquals(expected3, awaitItem())

            viewModel.searchRecipesByName(query4)
            assertEquals(Result.Loading, awaitItem())
            assertEquals(expected4, awaitItem())
        }
    }
}