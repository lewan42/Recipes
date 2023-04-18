package com.lewan.convert_api

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface ConvertMediator {

    fun openCreateRecipeFragment(@IdRes containerId: Int, fragmentManager: FragmentManager)
}