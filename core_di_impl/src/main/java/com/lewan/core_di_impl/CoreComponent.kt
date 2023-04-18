package com.lewan.core_di_impl

import com.lewan.core_db_impl.di.DatabaseModule
import com.lewan.core_di_api.AppProvider
import com.lewan.core_di_api.CoreComponentProvider
import com.lewan.network_impl.BaseNetworkModule
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class,
    ],
    modules = [
        DatabaseModule::class,
        BaseNetworkModule::class,
    ]
)
interface CoreComponent : CoreComponentProvider