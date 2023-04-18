package com.lewan.description_recipe_impl.di

import androidx.lifecycle.ViewModel
import com.lewan.base_fragment.ViewModelKey
import com.lewan.base_fragment.ViewModelModule
import com.lewan.description_recipe_impl.DescriptionRecipeViewModel
import com.lewan.description_recipe_impl.repo.DescriptionRecipeRepository
import com.lewan.description_recipe_impl.repo.DescriptionRecipeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface DescriptionRecipeModule {

    @Binds
    @IntoMap
    @ViewModelKey(DescriptionRecipeViewModel::class)
    fun bind(descriptionRecipeViewModel: DescriptionRecipeViewModel): ViewModel

    @Binds
    fun bindRepo(detailRecipeRepositoryImpl: DescriptionRecipeRepositoryImpl): DescriptionRecipeRepository
}