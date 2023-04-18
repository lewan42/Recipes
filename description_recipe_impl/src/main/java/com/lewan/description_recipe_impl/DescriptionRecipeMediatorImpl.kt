package com.lewan.description_recipe_impl

import androidx.fragment.app.FragmentManager
import com.lewan.description_recipe_api.DescriptionRecipeMediator
import javax.inject.Inject

class DescriptionRecipeMediatorImpl @Inject constructor() : DescriptionRecipeMediator {

    override fun openDescriptionRecipeFragment(
        containerId: Int,
        fragmentManager: FragmentManager,
        recipeId: Long
    ) {
        val fragment = fragmentManager.findFragmentByTag(DescriptionRecipeFragment::class.java.name)
            ?: DescriptionRecipeFragment.newInstance(recipeId)

        fragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_enter_from_right, R.anim.slide_exit_to_left,
                R.anim.slide_enter_from_left, R.anim.slide_exit_to_right
            )
            .replace(containerId, fragment, DescriptionRecipeFragment::class.java.name)
            .addToBackStack(DescriptionRecipeFragment::class.java.name)
            .commit()
    }
}