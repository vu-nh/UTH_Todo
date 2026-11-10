package com.example.uth_todo.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL =
        "https://share.tryapps.store/mock/teach/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val taskApiService: TaskApiService by lazy {
        retrofit.create(TaskApiService::class.java)
    }
}
