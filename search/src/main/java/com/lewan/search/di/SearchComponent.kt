package com.lewan.search.di

import com.lewan.core_api.mediator.ProvidersFacade
import com.lewan.search.SearchFragment
import dagger.Component


@Component(
    dependencies = [ProvidersFacade::class],
    modules = [SearchModule::class]
)
interface SearchComponent {
    companion object {
        fun create(facade: ProvidersFacade): SearchComponent =
            DaggerSearchComponent.builder().providersFacade(facade).build()
    }

    fun inject(searchFragment: SearchFragment)
}