package com.example.projectschool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.projectschool.retrofit.RetrofitClient
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getCurrentWeather() {
        var res: Call<JsonObject> = RetrofitClient
            .getInstance()
            .buildRetrofit()
            .getCurrentWeather(35.662725, 128.413960, "eaaabc3933459f98ffcea8128c0a2908")

        res.enqueue(object: Callback<JsonObject> {

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.d("TAG", "Failure : ${t.message.toString()}")
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                var jsonObj = JSONObject(response.body().toString())
                Log.d("TAG" , "Success :: $jsonObj")

            }
        })
    }
}
