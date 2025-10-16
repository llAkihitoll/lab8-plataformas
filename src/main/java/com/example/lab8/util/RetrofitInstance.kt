package com.example.lab8.util

import com.example.lab8.data.remote.Lab8Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.example.com/"

    val api: Lab8Api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Lab8Api::class.java)
    }
}
