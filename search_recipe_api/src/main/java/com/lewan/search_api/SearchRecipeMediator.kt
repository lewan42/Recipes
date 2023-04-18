package com.lewan.search_api

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface SearchRecipeMediator {

    fun startSearchFragment(@IdRes containerId: Int, fragmentManager: FragmentManager)
}