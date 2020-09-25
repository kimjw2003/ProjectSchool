package com.example.projectschool.retrofit.schedule

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ScheduleClient {
    var retrofitService3: ScheduleDao

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val logger = OkHttpClient.Builder().addInterceptor(interceptor)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://open.neis.go.kr/hub/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(logger)
            .build()

        retrofitService3 = retrofit.create(ScheduleDao::class.java)
    }
}