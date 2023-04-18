package com.lewan.search_recipe_impl

import com.lewan.core_db_api.entity.RecipeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

val recipeList1 = RecipeList(
    recipes = listOf(
        Recipe(
            id = 1,
            image = "https://1",
            name = "pasta"
        ),
        Recipe(
            id = 2,
            image = "https://2",
            name = "pizza"
        ),
        Recipe(
            id = 3,
            image = "https://3",
            name = "panto"
        )
    )
)

val recipeList2 = RecipeList(
    recipes = listOf(
        Recipe(
            id = 1,
            image = "https://1",
            name = "pasta"
        ),
        Recipe(
            id = 3,
            image = "https://3",
            name = "panto"
        )
    )
)

val recipeList3 = RecipeList(
    recipes = listOf(
        Recipe(
            id = 1,
            image = "https://1",
            name = "pasta"
        ),
    )
)

val recipeList4 = RecipeList(recipes = listOf())

@ExperimentalCoroutinesApi
class CoroutinesTestRule(
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}

val recipe1 = Recipe(
    id = 0L,
    name = "pasta1",
    image = "https:///11",
)

val recipe2 = Recipe(
    id = 1L,
    name = "pasta2",
    image = "https:///22",
)

val recipe3 = Recipe(
    id = 21L,
    name = "pasta",
    image = "https:///33",
)

val recipeEntity1 = RecipeEntity(
    id = 0L,
    name = "pasta1",
    image = "https:///11",
    servings = 1
)

val recipeEntity2 = RecipeEntity(
    id = 1L,
    name = "pasta2",
    image = "https:///22",
    servings = 1
)

val recipeEntity3 = RecipeEntity(
    id = 2L,
    name = "pasta3",
    image = "https:///33",
    servings = 1
)
