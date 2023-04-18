package com.lewan.search_recipe_impl

import com.lewan.core_api.IResult

data class RecipeList(
    val recipes: List<Recipe>
) : IResult