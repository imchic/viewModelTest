package com.example.viewmodeltest

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("iced")
    suspend fun getCoffeeList(): Response<List<Coffee>>

    companion object {

        private const val baseUrl = "https://api.sampleapis.com/coffee/"
        private val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS).build()

        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)

        }
    }
}
