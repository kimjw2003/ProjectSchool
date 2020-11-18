package com.example.projectschool.retrofit.Info

import com.example.projectschool.data.InfoBase
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface InfoDao {
    @GET("schoolInfo")
    fun getInfo(
        @Query("KEY", encoded = true) KEY : String,
        @Query("Type", encoded = true) Type : String,
        @Query("pIndex", encoded = true) pIndex : String,
        @Query("pSize", encoded = true) pSize: String,
        @Query("SCHUL_NM", encoded = true) SCHUL_NM : String
        ) : Call<InfoBase>
}