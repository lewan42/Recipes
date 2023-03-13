package com.lewan.app

import com.lewan.core_api.mediator.SearchMediator
import com.lewan.search.SearchMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface MediatorBindings {

    @Binds
    @Reusable
    fun bindsSearchMediator(searchMediatorImpl: SearchMediatorImpl): SearchMediator
}