package com.lewan.search_recipe_impl

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.lewan.base_fragment.ViewModelFragment
import com.lewan.core_api.IResult
import com.lewan.core_api.Result
import com.lewan.core_di_api.AppWithFacade
import com.lewan.search_recipe_impl.adapter.ItemDecoration
import com.lewan.search_recipe_impl.adapter.SearchRecipesAdapter
import com.lewan.search_recipe_impl.databinding.FragmentSearchBinding
import com.lewan.search_recipe_impl.di.SearchRecipeComponent
import kotlinx.coroutines.flow.collectLatest

class SearchRecipeFragment :
    ViewModelFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    companion object {
        fun newInstance() = SearchRecipeFragment()
    }

    private val adapter: SearchRecipesAdapter = SearchRecipesAdapter(::handleClick)
    private val viewModel: SearchRecipeViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as? AppWithFacade)?.getFacade()?.let {
            SearchRecipeComponent.create(it).inject(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSearchViewListener()
        setUpRecycler()
        subscribeToStateScreen()
    }

    private fun subscribeToStateScreen() {
        lifecycleScope.launchWhenStarted {
            viewModel
                .stateScreen
                .collectLatest(::showScreenState)
        }
    }

    private fun setUpRecycler() = binding.run {
        recyclerViewRecipes.adapter = adapter
        recyclerViewRecipes.addItemDecoration(ItemDecoration())
    }

    private fun setUpSearchViewListener() = binding.run {
        searchViewRecipes.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchRecipesByName(newText)
                return false
            }
        })
    }

    private fun showScreenState(state: Result<IResult>) = binding.run {
        when (state) {
            is Result.Error -> {
                progressBar.isVisible = false
                textViewState.isVisible = false
            }

            is Result.Loading -> {
                progressBar.isVisible = true
                textViewState.isVisible = false
            }

            is Result.Success -> {
                progressBar.isVisible = false
                showSuccessState(state.data)
            }

            else -> Unit
        }
    }

    private fun showSuccessState(result: IResult) = binding.run {
        when (result) {
            is RecipeList -> {
                adapter.submitList(result.recipes)

                if (result.recipes.isEmpty()) {
                    textViewState.isVisible = true
                    textViewState.text = getString(R.string.we_found_nothing_on_your_request)
                }
            }

            else -> Unit
        }
    }

    private fun handleClick(recipe: Recipe) {
        viewModel.openRecipeFragment(parentFragmentManager, recipe.id)
    }
}