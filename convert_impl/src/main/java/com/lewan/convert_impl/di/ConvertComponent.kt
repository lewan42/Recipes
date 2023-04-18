package com.lewan.convert_impl.di

import com.lewan.convert_impl.ui.ConvertFragment
import com.lewan.core_di_api.ProvidersFacade
import dagger.Component

@Component(
    dependencies = [ProvidersFacade::class],
    modules = [ConvertModule::class, ConvertServiceModule::class]
)
interface ConvertComponent {

    companion object {
        fun create(facade: ProvidersFacade): ConvertComponent =
            DaggerConvertComponent.builder().providersFacade(facade).build()
    }

    fun inject(convertFragment: ConvertFragment)
}