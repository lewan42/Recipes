package com.lewan.search_recipe_impl.di

import com.lewan.network_api.ApiKey
import com.lewan.network_api.INetworkServiceGenerator
import com.lewan.search_recipe_impl.repo.SearchRecipeService
import dagger.Module
import dagger.Provides

@Module
class SearchServiceModule {

    @Provides
    fun bind(networkServiceGenerator: INetworkServiceGenerator): SearchRecipeService =
        networkServiceGenerator.create(SearchRecipeService::class.java)

    @Provides
    fun bind2(apiKey: ApiKey) = apiKey.getApiKey()
}