package com.example.app_grupob.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL="http://4.211.191.132/App_Api/"
    // 10.249.99.206
    // 10.249.98.178
    // 192.168.8.148
    // 192.168.8.103
    // 192.168.43.33
    val api:ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}