package com.lewan.description_recipe_api

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface DescriptionRecipeMediator {

    fun openDescriptionRecipeFragment(
        @IdRes containerId: Int,
        fragmentManager: FragmentManager,
        recipeId: Long
    )
}