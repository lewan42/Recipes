package com.lewan.description_recipe_impl

import com.lewan.core_db_api.entity.DescriptionRecipeEntity
import com.lewan.description_recipe_impl.repo.DescriptionRecipeDto

object Mapper {

    fun mapDetailRecipeDtoToDetailRecipeEntity(descriptionRecipeDto: DescriptionRecipeDto): DescriptionRecipeEntity =
        DescriptionRecipeEntity(
            id = descriptionRecipeDto.id,
            title = descriptionRecipeDto.title,
            image = descriptionRecipeDto.image,
            servings = descriptionRecipeDto.servings,
            ingredients = descriptionRecipeDto.extendedIngredients.map(::mapIngredientDtoToIngredientEntity)
        )

    fun mapDetailRecipeEntityToRecipeInfo(recipeEntity: DescriptionRecipeEntity): DescriptionRecipe =
        DescriptionRecipe(
            id = recipeEntity.id,
            title = recipeEntity.title,
            image = recipeEntity.image,
            servings = recipeEntity.servings,
            extendedIngredients = recipeEntity.ingredients.map(::mapIngredientEntityToIngredient)
        )

    private fun mapIngredientDtoToIngredientEntity(ingredient: DescriptionRecipeDto.Ingredient): DescriptionRecipeEntity.Ingredient =
        DescriptionRecipeEntity.Ingredient(
            id = ingredient.id,
            name = ingredient.name,
            amount = ingredient.amount,
            unit = ingredient.unit,
        )

    private fun mapIngredientEntityToIngredient(ingredient: DescriptionRecipeEntity.Ingredient): DescriptionRecipe.Ingredient =
        DescriptionRecipe.Ingredient(
            id = ingredient.id,
            name = ingredient.name,
            amount = ingredient.amount,
            unit = ingredient.unit,
        )
}