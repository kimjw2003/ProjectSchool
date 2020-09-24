package com.example.projectschool.retrofit.weather

import com.example.projectschool.data.Base
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherDao {
    @GET("getLandFcst")
    fun getCurrentWeather(
        @Query("serviceKey", encoded = true) serviceKey: String,
        @Query("numOfRows", encoded = true) numOfRows: String,
        @Query("pageNo", encoded = true) pageNo: String,
        @Query("dataType", encoded = true) dataType: String,
        @Query("regId", encoded = true) regld: String
    ): Call<Base>

}