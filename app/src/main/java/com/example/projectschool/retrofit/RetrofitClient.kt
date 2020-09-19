package com.example.projectschool.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    fun getInstance(): RetrofitClient {
        val retrofit: Retrofit? = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service: RetrofitClient = retrofit!!.create(RetrofitClient::class.java)
        return service
    }
}