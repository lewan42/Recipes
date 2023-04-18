package com.lewan.convert_impl.di

import com.lewan.convert_impl.repo.ConvertService
import com.lewan.network_api.INetworkServiceGenerator
import dagger.Module
import dagger.Provides

@Module
class ConvertServiceModule {

    @Provides
    fun bind(networkServiceGenerator: INetworkServiceGenerator): ConvertService {
        return networkServiceGenerator.create(ConvertService::class.java)
    }
}