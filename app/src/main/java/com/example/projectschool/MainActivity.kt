package com.example.projectschool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.projectschool.data.Base
import com.example.projectschool.retrofit.Dao
import com.example.projectschool.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCurrentWeather()
    }


    fun getCurrentWeather() {

        RetrofitClient.retrofitService.getCurrentWeather(
            "7UhrTwPM239CrHxGYdXDboLOSh7OmX65p12WEAaev2FNpUNCSPddYen1%2Fjh0VnQyQzXzg5nOwLlOxXPkRuggFQ%3D%3D",
            "10", "1", "JSON", "11A00101"
        ).enqueue(object : Callback<Base>{
            override fun onFailure(call: Call<Base>, t: Throwable) {
                Log.d("Logg", t.message)
            }

            override fun onResponse(call: Call<Base>, response: Response<Base>) {
                 text1.text = response.body()?.response!!.body!!.items.item[1].rnSt.toString()
            }
        })

    }
}
