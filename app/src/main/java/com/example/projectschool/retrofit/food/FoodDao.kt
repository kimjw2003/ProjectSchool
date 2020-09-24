package com.example.projectschool.retrofit.food

import com.example.projectschool.data.FoodBase
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodDao {
    @GET("mealServiceDietInfo")
    fun getTomorrowFood(
        @Query("KEY", encoded = true) KEY : String,
        @Query("Type", encoded = true) Type : String,
        @Query("pIndex", encoded = true) pIndex : String,
        @Query("pSize", encoded = true) pSize: String,
        @Query("ATPT_OFCDC_SC_CODE", encoded = true) ATPT_OFCDC_SC_CODE : String,
        @Query("SD_SCHUL_CODE", encoded = true) SD_SCHUL_CODE : String,
        @Query("MLSV_YMD", encoded = true) MLSV_YMD : String
    ): Call<FoodBase>
}