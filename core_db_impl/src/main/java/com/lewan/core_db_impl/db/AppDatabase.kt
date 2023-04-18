package com.lewan.core_db_impl.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lewan.core_db_api.Converter
import com.lewan.core_db_api.dao.DescriptionRecipeDao
import com.lewan.core_db_api.dao.SearchRecipeDao
import com.lewan.core_db_api.entity.DescriptionRecipeEntity
import com.lewan.core_db_api.entity.RecipeEntity

@Database(
    entities = [
        RecipeEntity::class,
        DescriptionRecipeEntity::class,
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchRecipeDao(): SearchRecipeDao
    abstract fun descriptionRecipeDao(): DescriptionRecipeDao
}
