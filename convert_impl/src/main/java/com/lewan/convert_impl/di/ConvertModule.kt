package com.lewan.convert_impl.di

import androidx.lifecycle.ViewModel
import com.lewan.base_fragment.ViewModelKey
import com.lewan.base_fragment.ViewModelModule
import com.lewan.convert_impl.repo.ConvertRepository
import com.lewan.convert_impl.repo.ConvertRepositoryImpl
import com.lewan.convert_impl.vm.ConvertViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
interface ConvertModule {

    @Binds
    @IntoMap
    @ViewModelKey(ConvertViewModel::class)
    fun bind(viewModel: ConvertViewModel): ViewModel

    @Binds
    fun bindRepo(convertRepository: ConvertRepositoryImpl): ConvertRepository
}