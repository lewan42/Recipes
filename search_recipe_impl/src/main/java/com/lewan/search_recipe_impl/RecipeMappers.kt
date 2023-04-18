package com.lewan.search_recipe_impl

import com.lewan.core_db_api.entity.RecipeEntity
import com.lewan.search_recipe_impl.repo.RecipeDto
import javax.inject.Inject

@OpenForTesting
class RecipeMappers @Inject constructor() {

    fun recipeDtoMapToRecipeEntity(recipeDto: RecipeDto.Recipe): RecipeEntity =
        RecipeEntity(recipeDto.id, recipeDto.title, recipeDto.image, 1)

    fun recipeEntityToRecipe(recipeEntity: RecipeEntity): Recipe =
        Recipe(recipeEntity.id, recipeEntity.name, recipeEntity.image)
}