package com.lewan.core_factory

import com.lewan.core_di_api.AppProvider
import com.lewan.core_di_api.CoreComponentProvider
import com.lewan.core_di_impl.DaggerCoreComponent

object CoreComponentFactory {

    fun create(appProvider: AppProvider): CoreComponentProvider =
        DaggerCoreComponent.builder().appProvider(appProvider).build()
}