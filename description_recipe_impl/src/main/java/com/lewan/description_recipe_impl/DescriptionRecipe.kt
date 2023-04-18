package com.lewan.description_recipe_impl

import com.lewan.core_api.IResult

data class DescriptionRecipe(
    val id: Long,
    val title: String,
    val image: String,
    val servings: Int,
    val extendedIngredients: List<Ingredient>
) : IResult {

    data class Ingredient(
        val id: Long,
        val name: String,
        val amount: Double,
        val unit: String,
    )
}