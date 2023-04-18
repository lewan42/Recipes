package com.lewan.description_recipe_impl

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.lewan.base_fragment.ViewModelFragment
import com.lewan.core_api.IResult
import com.lewan.core_api.Result
import com.lewan.core_di_api.AppWithFacade
import com.lewan.description_recipe_impl.adapter.IngredientAdapter
import com.lewan.description_recipe_impl.databinding.DescriptionRecipeFragmentBinding
import com.lewan.description_recipe_impl.di.DescriptionRecipeComponent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val RECIPE_ID = "RECIPE_ID"

class DescriptionRecipeFragment :
    ViewModelFragment<DescriptionRecipeFragmentBinding>(DescriptionRecipeFragmentBinding::inflate) {

    private val adapterIngredients = IngredientAdapter()

    private val recipeId: Long? by lazy { arguments?.get(RECIPE_ID) as? Long }

    private val viewModel: DescriptionRecipeViewModel by viewModels { viewModelFactory }

    companion object {
        fun newInstance(recipeId: Long) = DescriptionRecipeFragment().apply {
            arguments = bundleOf(
                RECIPE_ID to recipeId,
            )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DescriptionRecipeComponent.create((context.applicationContext as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onStart() {
        super.onStart()
        recipeId?.let {
            viewModel.getDescriptionRecipe(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            toolbar.setNavigationOnClickListener { parentFragmentManager.popBackStack() }
        }
        setUpRecyclers()
        subscribeToStateScreen()
    }

    private fun subscribeToStateScreen() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel
                    .stateScreen
                    .collectLatest(::showScreenState)
            }
        }
    }

    private fun showScreenState(state: Result<IResult>) = binding.run {
        when (state) {
            is Result.Loading -> {
                shimmerLayout.shimmerLayout.startShimmerAnimation()
            }

            is Result.Success -> {
                showSuccessState(state.data)
                shimmerLayout.shimmerLayout.isVisible = false
                shimmerLayout.shimmerLayout.stopShimmerAnimation()
                container.isVisible = true
            }

            else -> Unit
        }
    }

    private fun showSuccessState(result: IResult) = binding.run {
        when (result) {
            is DescriptionRecipe -> {
                Glide
                    .with(requireContext())
                    .load(result.image)
                    .placeholder(R.drawable.dish)
                    .into(recipeImage)

                recipeName.text = result.title

                adapterIngredients.submitList(result.extendedIngredients)
            }

            else -> Unit
        }
    }

    private fun setUpRecyclers() = binding.run {
        recyclerIngredients.adapter = adapterIngredients
        recyclerIngredients.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

    }

    override fun onDestroyView() {
        viewModel.cancelViewModelScope()
        super.onDestroyView()
    }
}