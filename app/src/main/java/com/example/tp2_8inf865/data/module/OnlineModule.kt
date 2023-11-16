package com.example.tp2_8inf865.data.module

import com.example.tp2_8inf865.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnlineModule {

    @Provides
    @Named("BASE_URL")
    fun getBaseUrl() : String {
        return "https://api.yomomma.info/"
    }

    @Singleton
    @Provides
    fun getNewJoke(): OkHttpClient{
        return OkHttpClient().newBuilder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun getConvertorFactory(): JacksonConverterFactory {
        return JacksonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun getJokeApiService(@Named("BASE_URL") baseUrl: String,
                          client: OkHttpClient,
                          factory: JacksonConverterFactory) : ApiService{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(ApiService::class.java)
    }
}