package com.lewan.core_db_api.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lewan.core_db_api.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchRecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<RecipeEntity>)

    @Query("SELECT * FROM recipe WHERE name LIKE '%' || :queryString || '%' ORDER BY name ASC")
    fun recipesByName(queryString: String): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipe")
    suspend fun getAll(): List<RecipeEntity>
}