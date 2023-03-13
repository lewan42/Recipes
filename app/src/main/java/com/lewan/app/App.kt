package com.lewan.app

import android.app.Application
import com.lewan.core_api.mediator.AppWithFacade
import com.lewan.core_api.mediator.ProvidersFacade

class App : Application(), AppWithFacade {

    companion object {
        private var facadeComponent: FacadeComponent? = null
    }

    override fun getFacade(): ProvidersFacade =
        facadeComponent ?: FacadeComponent.create(this).also {
            facadeComponent = it
        }

    override fun onCreate() {
        super.onCreate()
        getFacade()
    }
}