package com.lewan.network_api

interface INetworkServiceGenerator {

    fun <Service : Any> create(service: Class<Service>): Service
}