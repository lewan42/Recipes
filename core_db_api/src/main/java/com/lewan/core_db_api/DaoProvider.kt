package com.lewan.core_db_api

import com.lewan.core_db_api.dao.SearchRecipeDao
import com.lewan.core_db_api.dao.DescriptionRecipeDao

interface DaoProvider {

    val provideSearchRecipeDao: SearchRecipeDao

    val provideDescriptionRecipeDao: DescriptionRecipeDao
}