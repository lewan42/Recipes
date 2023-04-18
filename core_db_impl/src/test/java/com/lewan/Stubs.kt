package com.lewan.core_db_impl

import com.lewan.core_db_api.entity.RecipeEntity

val recipe1 = RecipeEntity(
    id = 0L,
    image = "https://1",
    name = "Pasta 1",
    servings = 2
)

val recipe2 = RecipeEntity(
    id = 1L,
    image = "https://2",
    name = "pastila",
    servings = 3
)

val recipe3 = RecipeEntity(
    id = 2L,
    image = "https://3",
    name = "Burrito",
    servings = 1
)

val recipe4 = RecipeEntity(
    id = 3L,
    image = "https://4",
    name = "Burger",
    servings = 1
)

val recipe5 = RecipeEntity(
    id = 4L,
    image = "https://4",
    name = "Pastila",
    servings = 2
)

val recipes123Test = listOf(recipe1, recipe2, recipe3)

val recipes124Test = listOf(recipe1, recipe2, recipe4)

val recipes5Test = listOf(recipe5)