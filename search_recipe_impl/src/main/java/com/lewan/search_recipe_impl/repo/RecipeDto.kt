package com.lewan.search_recipe_impl.repo

data class RecipeDto(
    val results: List<Recipe>,
) {
    data class Recipe(
        val id: Long,
        val title: String,
        val image: String,
    )
}