package com.lewan

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.lewan.core_db_api.dao.SearchRecipeDao
import com.lewan.core_db_api.entity.RecipeEntity
import com.lewan.core_db_impl.db.AppDatabase
import com.lewan.core_db_impl.recipe1
import com.lewan.core_db_impl.recipe2
import com.lewan.core_db_impl.recipe5
import com.lewan.core_db_impl.recipes123Test
import com.lewan.core_db_impl.recipes124Test
import com.lewan.core_db_impl.recipes5Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class SearchRecipeDaoTest {
    private lateinit var searchRecipeDao: SearchRecipeDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun create() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        searchRecipeDao = appDatabase.searchRecipeDao()
    }

    @Test
    fun insertAllTest() = runTest {
        searchRecipeDao.insertAll(recipes123Test)
        val recipesTest1 = searchRecipeDao.getAll()
        assertEquals(3, recipesTest1.size)

        searchRecipeDao.insertAll(recipes124Test)
        val recipesTest2 = searchRecipeDao.getAll()
        assertEquals(4, recipesTest2.size)
    }

    @Test
    fun recipesByNameTest() = runTest {

        val test1 = listOf<RecipeEntity>()
        val test2 = listOf(recipe1, recipe2)
        val test3 = listOf(recipe1, recipe5, recipe2)

        searchRecipeDao.recipesByName("past").test {
            assertEquals(test1, awaitItem())
            searchRecipeDao.insertAll(recipes123Test)
            assertEquals(test2, awaitItem())
            searchRecipeDao.insertAll(recipes5Test)
            assertEquals(test3, awaitItem())
        }
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }
}