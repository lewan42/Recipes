package com.lewan.search_recipe_impl.di

import com.lewan.search_recipe_impl.SearchRecipeMediatorImpl
import com.lewan.search_api.SearchRecipeMediator
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface SearchMediatorModule {

    @Binds
    @IntoMap
    @ClassKey(SearchRecipeMediator::class)
    fun bindMediator(mediator: SearchRecipeMediatorImpl): Any
}