package com.lewan.description_recipe_impl.repo

data class DescriptionRecipeDto(
    val id: Long,
    val title: String,
    val image: String,
    val servings: Int,
    val readyInMinutes: Int,
    val extendedIngredients: List<Ingredient>
) {
    data class Ingredient(
        val id: Long,
        val name: String,
        val amount: Double,
        val unit: String
    )
}