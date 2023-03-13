package com.lewan.core_api.mediator

import javax.inject.Provider

interface MediatorProvider {

    fun provideSearchMediator(): SearchMediator

//    fun mediatorMap(): Map<Class<*>, @JvmSuppressWildcards Provider<Any>>
}