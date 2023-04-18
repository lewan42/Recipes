package com.lewan.search_recipe_impl.di

import androidx.lifecycle.ViewModel
import com.lewan.base_fragment.ViewModelKey
import com.lewan.base_fragment.ViewModelModule
import com.lewan.description_recipe_api.DescriptionRecipeMediator
import com.lewan.main_api.MainContainer
import com.lewan.search_recipe_impl.SearchRecipeViewModel
import com.lewan.search_recipe_impl.repo.SearchRepositoryApi
import com.lewan.search_recipe_impl.repo.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module(includes = [ViewModelModule::class])
interface SearchRecipeModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchRecipeViewModel::class)
    fun bind(viewModel: SearchRecipeViewModel): ViewModel

    @Binds
    fun bindRepo(searchRepositoryImpl: SearchRepositoryImpl): SearchRepositoryApi


    companion object {
        @Provides
        fun provideMediator1(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): DescriptionRecipeMediator {
            return map[DescriptionRecipeMediator::class.java]!!.get() as DescriptionRecipeMediator
        }

        @Provides
        fun provideMediator2(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): MainContainer {
            return map[MainContainer::class.java]!!.get() as MainContainer
        }
    }
}