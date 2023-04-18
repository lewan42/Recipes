package com.lewan.core_di_api

import com.lewan.network_api.ApiKey
import com.lewan.network_api.INetworkServiceGenerator

interface NetworkProvider {

    fun provideServiceGenerator(): INetworkServiceGenerator

    fun provideApiKey(): ApiKey
}