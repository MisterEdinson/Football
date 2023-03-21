package com.example.football.data.host

import com.example.football.utils.Constants.Companion.BASE_URL
import com.example.football.utils.Constants.Companion.TOKEN
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor{
                val request = it.request().newBuilder()
                    .addHeader("X-Auth-Token", TOKEN)
                    .build()
                it.proceed(request)
            }.build())
            .build()
    }

    val api:SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
}