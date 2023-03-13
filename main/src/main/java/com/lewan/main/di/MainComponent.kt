package com.lewan.main.di

import com.lewan.core_api.mediator.ProvidersFacade
import com.lewan.main.MainActivity
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class]
)
interface MainComponent {

    companion object {
        fun create(providersFacade: ProvidersFacade): MainComponent =
            DaggerMainComponent.builder().providersFacade(providersFacade).build()
    }

    fun inject(mainActivity: MainActivity)
}