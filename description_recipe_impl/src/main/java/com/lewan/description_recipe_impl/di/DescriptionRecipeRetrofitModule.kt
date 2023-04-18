package com.lewan.description_recipe_impl.di

import com.lewan.description_recipe_impl.repo.DescriptionRecipeService
import com.lewan.network_api.INetworkServiceGenerator
import dagger.Module
import dagger.Provides

@Module
class DescriptionRecipeRetrofitModule {

    @Provides
    fun bindService(networkServiceGenerator: INetworkServiceGenerator): DescriptionRecipeService =
        networkServiceGenerator.create(DescriptionRecipeService::class.java)
}