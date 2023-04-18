package com.lewan.core_db_api.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val image: String,
    val servings: Int,
)