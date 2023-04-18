package com.lewan.network_impl

import com.lewan.network_api.INetworkServiceGenerator
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkServiceGeneratorImpl @Inject constructor(
    private val retrofit: Retrofit
) : INetworkServiceGenerator {

    override fun <Service : Any> create(service: Class<Service>): Service =
        retrofit.create(service)
}