package com.lewan.core_di_api

import android.content.Context

interface AppProvider {

    fun provideContext(): Context
}