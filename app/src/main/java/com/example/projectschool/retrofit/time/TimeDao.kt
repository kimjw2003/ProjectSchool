package com.example.projectschool.retrofit.time

import com.example.projectschool.data.TimeBase
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeDao {
    @GET("hisTimetable")
    fun getTomorrowTime(
        @Query("KEY", encoded = true) KEY:String,
        @Query("Type", encoded = true) Type : String,
        @Query("pIndex", encoded = true) pIndex : String,
        @Query("pSize", encoded = true) pSize : String,
        @Query("ATPT_OFCDC_SC_CODE", encoded = true) ATPT_OFCDC_SC_CODE : String,
        @Query("SD_SCHUL_CODE", encoded = true) SD_SCHUL_CODE : String,
        @Query("AY", encoded = true) AY:String,
        @Query("ALL_TI_YMD", encoded = true) ALL_TI_YMD:String,
        @Query("GRADE", encoded = true) GRADE:String,
        @Query("CLASS_NM", encoded = true) CLASS_NM : String
    ):Call<TimeBase>
}