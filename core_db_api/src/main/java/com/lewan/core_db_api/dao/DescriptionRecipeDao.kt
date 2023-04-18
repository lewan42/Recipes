package com.lewan.core_db_api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lewan.core_db_api.entity.DescriptionRecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DescriptionRecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipeInfo: DescriptionRecipeEntity)

    @Query("SELECT * FROM recipe_info WHERE id = :id")
    fun getRecipeInfoById(id: Long): Flow<DescriptionRecipeEntity?>
}