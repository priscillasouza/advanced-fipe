package com.advancedfipe.consult.data.source.remote.api

import com.advancedfipe.consult.data.source.remote.RoutesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api(private val baseUrl: String) {
    fun create(): RoutesApi {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })

        val httpClientBuild = httpClient.build()
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClientBuild)
            .build()
            .create(RoutesApi::class.java)
    }
}