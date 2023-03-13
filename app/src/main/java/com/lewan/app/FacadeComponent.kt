package com.lewan.app

import android.app.Application
import com.lewan.core_api.mediator.AppProvider
import com.lewan.core_api.mediator.MediatorProvider
import com.lewan.core_api.mediator.ProvidersFacade
import dagger.Component

@Component(
    dependencies = [AppProvider::class],
    modules = [MediatorBindings::class]
)
interface FacadeComponent : ProvidersFacade {

    companion object {
        fun create(application: Application): FacadeComponent =
            DaggerFacadeComponent
                .builder()
                .appProvider(AppComponent.create(application))
                .build()
    }
}