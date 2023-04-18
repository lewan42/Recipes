package com.lewan.core_db_impl.di

import android.content.Context
import androidx.room.Room
import com.lewan.core_db_api.dao.DescriptionRecipeDao
import com.lewan.core_db_api.dao.SearchRecipeDao
import com.lewan.core_db_impl.db.AppDatabase
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    private const val DATABASE_NAME = "DatabaseRecipes"

    @Provides
    fun provideAppDataBase(
        context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    fun provideRecipeDao(appDatabase: AppDatabase): SearchRecipeDao =
        appDatabase.searchRecipeDao()

    @Provides
    fun provideDescriptionRecipeDao(appDatabase: AppDatabase): DescriptionRecipeDao =
        appDatabase.descriptionRecipeDao()
}