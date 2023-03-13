package com.lewan.search.di

import androidx.lifecycle.ViewModel
import com.lewan.core.di.ViewModelKey
import com.lewan.core.di.ViewModelModule
import com.lewan.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface SearchModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bind(viewModel: SearchViewModel): ViewModel
}