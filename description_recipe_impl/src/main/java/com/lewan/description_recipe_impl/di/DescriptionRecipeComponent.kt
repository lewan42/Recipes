package com.lewan.description_recipe_impl.di

import com.lewan.core_di_api.ProvidersFacade
import com.lewan.description_recipe_impl.DescriptionRecipeFragment
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules = [DescriptionRecipeModule::class, DescriptionRecipeRetrofitModule::class]
)
interface DescriptionRecipeComponent {

    companion object {
        fun create(facade: ProvidersFacade): DescriptionRecipeComponent =
            DaggerDescriptionRecipeComponent.builder().providersFacade(facade).build()
    }

    fun inject(descriptionRecipeFragment: DescriptionRecipeFragment)
}