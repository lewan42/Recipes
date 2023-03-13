package com.lewan.app

import android.app.Application
import android.content.Context
import com.lewan.core_api.mediator.AppProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component
@Singleton
interface AppComponent : AppProvider {

    companion object {

        private var appComponent: AppProvider? = null

        fun create(application: Application): AppProvider = appComponent ?: DaggerAppComponent
            .builder()
            .application(application.applicationContext)
            .build().also {
                appComponent = it
            }
    }


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(context: Context): Builder

        fun build(): AppComponent
    }
}