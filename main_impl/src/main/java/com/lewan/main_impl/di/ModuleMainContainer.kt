package com.lewan.main_impl.di

import com.lewan.main_impl.MainContainerImpl
import com.lewan.main_api.MainContainer
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface ModuleMainContainer {

    @Binds
    @IntoMap
    @ClassKey(MainContainer::class)
    fun bindMainContainer(mediator: MainContainerImpl): Any
}