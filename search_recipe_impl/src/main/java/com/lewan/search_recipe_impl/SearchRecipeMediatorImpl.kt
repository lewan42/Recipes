package com.lewan.search_recipe_impl

import androidx.fragment.app.FragmentManager
import com.lewan.search_api.SearchRecipeMediator
import javax.inject.Inject

class SearchRecipeMediatorImpl @Inject constructor() : SearchRecipeMediator {

    override fun startSearchFragment(containerId: Int, fragmentManager: FragmentManager) {
        val fragment = fragmentManager.findFragmentByTag(SearchRecipeFragment::class.java.name)
            ?: SearchRecipeFragment.newInstance()

        fragmentManager.beginTransaction()
            .replace(containerId, fragment, SearchRecipeFragment::class.java.name)
            .addToBackStack(SearchRecipeFragment::class.java.name)
            .commit()
    }
}