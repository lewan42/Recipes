package com.lewan.network_impl

import com.lewan.network_api.INetworkServiceGenerator
import com.lewan.network_api.ApiKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module(
    includes = [
        NetworkModuleProvides::class,
        NetworkModuleBinds::class
    ]
)
object BaseNetworkModule

@Module
object NetworkModuleProvides {


    @Provides
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
//                if (BuildConfig.DEBUG)
                addNetworkInterceptor(provideLoggingInterceptor())
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()

    @Reusable
    @Provides
    fun provideRetrofit(
        urlProvider: IUrlProvider,
        client: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(urlProvider.provideUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

}

@Module
interface NetworkModuleBinds {

    @Binds
    fun bindBaseUrl(urlProviderImpl: UrlProviderImpl): IUrlProvider
//
    @Binds
    fun bindApiKey(apiKeyImpl: ApiKeyImpl): ApiKey

    @Binds
    fun bindNetworkGenerator(networkServiceGeneratorImpl: NetworkServiceGeneratorImpl): INetworkServiceGenerator
}