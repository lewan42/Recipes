package com.lewan.core_di_api

import javax.inject.Provider

interface MediatorProvider {

    fun mediatorMap(): Map<Class<*>, @JvmSuppressWildcards Provider<Any>>
}