package com.lewan.description_recipe_impl.di

import com.lewan.description_recipe_api.DescriptionRecipeMediator
import com.lewan.description_recipe_impl.DescriptionRecipeMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface DescriptionRecipeMediatorModule {

    @Binds
    @IntoMap
    @ClassKey(DescriptionRecipeMediator::class)
    fun bind(recipeMediatorImpl: DescriptionRecipeMediatorImpl): Any
}