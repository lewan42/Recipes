package com.lewan.convert_impl.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.google.android.material.textfield.TextInputEditText
import com.lewan.base_fragment.ViewModelFragment
import com.lewan.convert_impl.ConvertData
import com.lewan.convert_impl.R
import com.lewan.convert_impl.databinding.FragmentFavoriteRecipesBinding
import com.lewan.convert_impl.di.ConvertComponent
import com.lewan.convert_impl.vm.ConvertViewModel
import com.lewan.core_api.Result
import com.lewan.core_di_api.AppWithFacade
import kotlinx.coroutines.flow.collectLatest

class ConvertFragment :
    ViewModelFragment<FragmentFavoriteRecipesBinding>(FragmentFavoriteRecipesBinding::inflate) {

    companion object {
        fun newInstance() = ConvertFragment()
    }

    private val viewModel: ConvertViewModel by viewModels { viewModelFactory }

    private val animationLoader: AnimatedVectorDrawableCompat? by lazy {
        AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.ic_vector_fwd)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ConvertComponent.create((context.applicationContext as AppWithFacade).getFacade())
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            btnCalc.setOnClickListener {
                it.animateClick()
                if (isCheck(ingredientName, sourceAmount, sourceUnit, targetUnit)) {
                    textResult.text = ""
                    viewModel.calculate(
                        ingredientName.text.toString(),
                        sourceAmount.text.toString().toDouble(),
                        sourceUnit.text.toString(),
                        targetUnit.text.toString()
                    )
                }
            }

            lifecycleScope.launchWhenStarted {
                viewModel.stateScreen.collectLatest { result ->
                    when (result) {
                        is Result.Error -> {
                            hideLoading()
                            showSnackBar(text = result.message)
                        }

                        is Result.Loading -> {
                            showLoading()
                        }

                        is Result.Success -> {
                            hideLoading()
                            when (val res = result.data) {
                                is ConvertData -> {
                                    binding.apply {
                                        textResult.text = res.answer
                                    }
                                }
                            }
                        }

                        else -> Unit
                    }
                }
            }
        }
    }

    private fun showLoading() = binding.run {
        animationLoader?.let {
            imageViewVector.isVisible = true
            imageViewVector.setImageDrawable(it)

            it.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                override fun onAnimationEnd(drawable: Drawable?) {
                    super.onAnimationEnd(drawable)
                    it.start()
                }
            })

            it.start()
        }
    }

    private fun hideLoading() = binding.run {
        imageViewVector.isVisible = false
    }

    private fun isCheck(vararg fields: TextInputEditText): Boolean {
        var isCheck = true
        for (field in fields) {
            if (field.text.toString().isBlank()) {
                field.error = getString(R.string.fill_field)
                isCheck = false
            }
        }
        return isCheck
    }
}