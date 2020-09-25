package com.example.projectschool.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectschool.R
import com.example.projectschool.data.Base
import com.example.projectschool.retrofit.weather.WeatherClient
import com.project.simplecode.spDateFormat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class weatherFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        getCurrentTemp()
        return view
    }

    fun getCurrentTemp() {

        WeatherClient.retrofitService.getCurrentWeather(
            "7UhrTwPM239CrHxGYdXDboLOSh7OmX65p12WEAaev2FNpUNCSPddYen1%2Fjh0VnQyQzXzg5nOwLlOxXPkRuggFQ%3D%3D",
            "10", "1", "JSON", "11H10604"
        ).enqueue(object : Callback<Base> {
            override fun onFailure(call: Call<Base>, t: Throwable) {
                Log.d("Logg", t.message)
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Base>, response: Response<Base>) {
                if (activity!!.time.text.toString().toInt() in 5..11) { // 11~5시 사이에 확인 시 내일아침 기온
                    text2.text = response.body()?.response?.body?.items?.item?.get(2)?.ta.toString()
                    if (text2.text.toString().toInt() < 10) {
                        sentence2.text = "날씨가 굉장히 추워요"
                        sentence2.setTextColor(Color.parseColor("#0080f0"))
                    } else if (text2.text.toString().toInt() in 10..19) {
                        sentence2.text = "날씨가 쌀쌀해요"
                        sentence2.setTextColor(Color.parseColor("#50BCDF"))
                    } else if (text2.text.toString().toInt() in 20..29) {
                        sentence2.text = "날씨가 따뜻해요"
                        sentence2.setTextColor(Color.parseColor("#FF7F00"))
                    } else {
                        sentence2.text = "날씨가 굉장히 더워요"
                        sentence2.setTextColor(Color.parseColor("#ff0000"))
                    }
                } else { // 나머지 시간에 확인 시 내일 아침 기온
                    text2.text =
                        response.body()?.response?.body?.items?.item?.get(1)?.ta.toString()
                    if (text2.text.toString().toInt() < 10) {
                        sentence2.text = "날씨가 굉장히 추워요"
                        sentence2.setTextColor(Color.parseColor("#0080f0"))
                    } else if (text2.text.toString().toInt() in 10..19) {
                        sentence2.text = "날씨가 쌀쌀해요"
                        sentence2.setTextColor(Color.parseColor("#50BCDF"))
                    } else if (text2.text.toString().toInt() in 20..29) {
                        sentence2.text = "날씨가 따뜻해요"
                        sentence2.setTextColor(Color.parseColor("#FF7F00"))
                    } else {
                        sentence2.text = "날씨가 굉장히 더워요"
                        sentence2.setTextColor(Color.parseColor("#ff0000"))
                    }
                }
            } //onResponse끝
        })
    }
}