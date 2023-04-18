package com.lewan.network_impl

import com.lewan.network_api.ApiKey
import javax.inject.Inject

class ApiKeyImpl @Inject constructor(): ApiKey {
    override fun getApiKey(): String  = "43b46e79f3104979b46ab6862d6232d9"
}