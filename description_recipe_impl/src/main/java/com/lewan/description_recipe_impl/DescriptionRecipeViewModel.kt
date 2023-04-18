package com.lewan.description_recipe_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lewan.core_api.IResult
import com.lewan.core_api.Result
import com.lewan.description_recipe_impl.repo.DescriptionRecipeRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class DescriptionRecipeViewModel @Inject constructor(
    private val repository: DescriptionRecipeRepository
) : ViewModel() {

    private val _stateScreen: MutableStateFlow<Result<IResult>> = MutableStateFlow(Result.Loading)
    val stateScreen: MutableStateFlow<Result<IResult>> = _stateScreen

    fun getDescriptionRecipe(recipeId: Long) = viewModelScope.launch {
        repository.getDescriptionRecipe(recipeId).collectLatest {
            stateScreen.value = it
        }
    }

    fun cancelViewModelScope() {
        viewModelScope.cancel()
    }
}

class DescriptionRecipeViewModelFactory @Inject constructor(
    private val repository: DescriptionRecipeRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DescriptionRecipeViewModel(
            repository,
        ) as T
    }
}