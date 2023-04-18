package com.lewan.search_recipe_impl.di

import com.lewan.core_di_api.ProvidersFacade
import com.lewan.search_recipe_impl.SearchRecipeFragment
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules = [SearchRecipeModule::class, SearchServiceModule::class]
)
interface SearchRecipeComponent {
    companion object {
        fun create(facade: ProvidersFacade): SearchRecipeComponent =
            DaggerSearchRecipeComponent.builder().providersFacade(facade).build()
    }

    fun inject(searchRecipeFragment: SearchRecipeFragment)
}