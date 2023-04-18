package com.lewan.search_recipe_impl

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lewan.core_api.IResult
import com.lewan.core_api.Result
import com.lewan.description_recipe_api.DescriptionRecipeMediator
import com.lewan.main_api.MainContainer
import com.lewan.search_recipe_impl.repo.SearchRepositoryApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OpenForTesting
class SearchRecipeViewModel @Inject constructor(
    private val repository: SearchRepositoryApi,
    private val descriptionRecipeMediator: DescriptionRecipeMediator,
    private val mainContainer: MainContainer
) : ViewModel() {

    private val _stateScreen: MutableStateFlow<Result<IResult>> = MutableStateFlow(Result.Loading)
    val stateScreen: StateFlow<Result<IResult>> = _stateScreen

    private val query: MutableStateFlow<String?> = MutableStateFlow(null)

    init {
        query.value = ""
        viewModelScope.launch {
            query
                .filterNotNull()
                .debounce(400)
                .onEach {
                    _stateScreen.emit(Result.Loading)
                }
                .collectLatest {
                    _stateScreen.emitAll(repository.getRecipes(it))
                }
        }
    }

    fun searchRecipesByName(queryString: String?) {
        query.value = queryString
    }

    fun openRecipeFragment(
        fragmentManager: FragmentManager,
        recipeId: Long
    ) {
        descriptionRecipeMediator.openDescriptionRecipeFragment(
            mainContainer.getMainContainerId(),
            fragmentManager,
            recipeId
        )
    }
}