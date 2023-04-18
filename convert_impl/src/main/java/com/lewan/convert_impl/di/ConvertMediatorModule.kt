package com.lewan.convert_impl.di

import com.lewan.convert_api.ConvertMediator
import com.lewan.convert_impl.ConvertMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface ConvertMediatorModule {

    @Binds
    @IntoMap
    @ClassKey(ConvertMediator::class)
    fun bind(convertMediatorImpl: ConvertMediatorImpl): Any
}