package com.example.projectschool.retrofit.food

import retrofit2.http.GET
import retrofit2.http.Query

interface FoodDao {
    @GET("food")
    fun getTomorrowFood(
        @Query("foodkey", encoded = true) foodkey : String,
        @Query("dataType", encoded = true) dataType : String,
        @Query("pIndex", encoded = true) pIndex : Int,
        @Query("pSize", encoded = true) pSize: Int,
        @Query("ATPT_OFCDC_SC_CODE", encoded = true) ATPT_OFCDC_SC_CODE : String,
        @Query("SD_SCHUL_CODE", encoded = true) SD_SCHUL_CODE : String,
        @Query("MLSV_YMD", encoded = true) MLSV_YMD : String
    )
}