package com.lewan.app

import android.app.Application
import com.lewan.core_di_api.AppProvider
import com.lewan.core_di_api.CoreComponentProvider
import com.lewan.core_di_api.ProvidersFacade
import com.lewan.core_factory.CoreComponentFactory
import com.lewan.convert_impl.di.ConvertMediatorModule
import com.lewan.description_recipe_impl.di.DescriptionRecipeMediatorModule
import com.lewan.main_impl.di.ModuleMainContainer
import com.lewan.search_recipe_impl.di.SearchMediatorModule
import dagger.Component

@Component(
    dependencies = [
        AppProvider::class,
        CoreComponentProvider::class,
    ],
    modules = [
        SearchMediatorModule::class,
        ConvertMediatorModule::class,
        DescriptionRecipeMediatorModule::class,
        ModuleMainContainer::class
    ]
)
interface FacadeComponent : ProvidersFacade {

    companion object {
        fun create(application: Application): FacadeComponent =
            DaggerFacadeComponent
                .builder()
                .appProvider(AppComponent.create(application))
                .coreComponentProvider(CoreComponentFactory.create(AppComponent.create(application)))
                .build()
    }
}