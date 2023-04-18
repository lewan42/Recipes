package com.lewan.main_impl.di

import com.lewan.core_di_api.ProvidersFacade
import com.lewan.main_impl.MainActivity
import dagger.Component

@Component(
    modules = [MainModule::class],
    dependencies = [ProvidersFacade::class]
)
interface MainComponent {

    companion object {
        fun create(providersFacade: ProvidersFacade): MainComponent =
            DaggerMainComponent.builder().providersFacade(providersFacade).build()
    }

    fun inject(mainActivity: MainActivity)
}