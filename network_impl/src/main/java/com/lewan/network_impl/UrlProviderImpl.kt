package com.lewan.network_impl

import javax.inject.Inject

class UrlProviderImpl @Inject constructor() : IUrlProvider {
    override fun provideUrl(): String = "https://api.spoonacular.com"
}