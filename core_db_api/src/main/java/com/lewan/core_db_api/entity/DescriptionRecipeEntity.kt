package com.lewan.core_db_api.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_info")
data class DescriptionRecipeEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val title: String,
    val image: String,
    val servings: Int,
    val ingredients: List<Ingredient>
) {

    data class Ingredient(
        val id: Long,
        val name: String,
        val amount: Double,
        val unit: String
    )
}