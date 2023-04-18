package com.lewan.search_recipe_impl

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.lewan.core_api.IResult
import com.lewan.core_api.Result
import com.lewan.description_recipe_api.DescriptionRecipeMediator
import com.lewan.main_api.MainContainer
import com.lewan.search_recipe_impl.repo.SearchRepositoryApi
import kotlinx.coroutines.flow.MutableStateFlow
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
@Config(instrumentedPackages = ["androidx.loader.content"])
class SearchRecipeFragmentTest {

    @Test
    fun `testing loading state`() {

        val viewModel = mock<SearchRecipeViewModel>()
        val factory: SearchViewModelFactory = mock()
        whenever(factory.create<SearchRecipeViewModel>(any())).thenReturn(viewModel)

        val flow = MutableStateFlow<Result<IResult>>(Result.Loading)
        whenever(viewModel.stateScreen).thenReturn(flow)
        val fragmentScenario =
            launchFragmentInContainer<SearchRecipeFragment>(initialState = Lifecycle.State.CREATED)

        fragmentScenario.onFragment { fragment ->
            fragment.viewModelFactory = factory
            fragmentScenario.moveToState(Lifecycle.State.STARTED)
        }

        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.text_view_state)).check(matches(not(isDisplayed())))
    }

    @Test
    fun `testing success state with empty list`() {

        val viewModel = mock<SearchRecipeViewModel>()
        val factory: SearchViewModelFactory = mock()
        whenever(factory.create<SearchRecipeViewModel>(any())).thenReturn(viewModel)

        val flow = MutableStateFlow<Result<IResult>>(Result.Success(recipeList4))
        whenever(viewModel.stateScreen).thenReturn(flow)
        val fragmentScenario =
            launchFragmentInContainer<SearchRecipeFragment>(initialState = Lifecycle.State.CREATED)

        fragmentScenario.onFragment { fragment ->
            fragment.viewModelFactory = factory
            fragmentScenario.moveToState(Lifecycle.State.STARTED)
        }

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.text_view_state)).check(matches(isDisplayed()))
    }

    @Test
    fun `testing success state with not empty list`() {

        val viewModel = mock<SearchRecipeViewModel>()
        val factory: SearchViewModelFactory = mock()
        whenever(factory.create<SearchRecipeViewModel>(any())).thenReturn(viewModel)

        val flow = MutableStateFlow<Result<IResult>>(Result.Success(recipeList1))
        whenever(viewModel.stateScreen).thenReturn(flow)
        val fragmentScenario =
            launchFragmentInContainer<SearchRecipeFragment>(initialState = Lifecycle.State.CREATED)

        fragmentScenario.onFragment { fragment ->
            fragment.viewModelFactory = factory
            fragmentScenario.moveToState(Lifecycle.State.STARTED)
        }

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.text_view_state)).check(matches(not(isDisplayed())))
    }

    @Test
    fun `testing error state`() {

        val viewModel = mock<SearchRecipeViewModel>()
        val factory: SearchViewModelFactory = mock()
        whenever(factory.create<SearchRecipeViewModel>(any())).thenReturn(viewModel)

        val flow = MutableStateFlow<Result<IResult>>(Result.Error(""))
        whenever(viewModel.stateScreen).thenReturn(flow)
        val fragmentScenario =
            launchFragmentInContainer<SearchRecipeFragment>(initialState = Lifecycle.State.CREATED)

        fragmentScenario.onFragment { fragment ->
            fragment.viewModelFactory = factory
            fragmentScenario.moveToState(Lifecycle.State.STARTED)
        }

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.text_view_state)).check(matches(not(isDisplayed())))
    }
}

@OpenForTesting
class SearchViewModelFactory @Inject constructor(
    private val repository: SearchRepositoryApi,
    private val descriptionRecipeMediator: DescriptionRecipeMediator,
    private val mainContainer: MainContainer
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchRecipeViewModel(
            repository, descriptionRecipeMediator, mainContainer
        ) as T
    }
}

