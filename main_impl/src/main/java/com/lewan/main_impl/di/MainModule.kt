package com.lewan.main_impl.di

import com.lewan.convert_api.ConvertMediator
import com.lewan.search_api.SearchRecipeMediator
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
interface MainModule {

    companion object {

        @Provides
        fun provideMediator1(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): SearchRecipeMediator {
            return map[SearchRecipeMediator::class.java]!!.get() as SearchRecipeMediator
        }

        @Provides
        fun provideMediator2(map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>): ConvertMediator {
            return map[ConvertMediator::class.java]!!.get() as ConvertMediator
        }
    }
}