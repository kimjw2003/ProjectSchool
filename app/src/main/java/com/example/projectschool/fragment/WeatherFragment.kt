package com.example.projectschool.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectschool.R
import com.example.projectschool.data.Base
import com.example.projectschool.retrofit.weather.WeatherClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        getCurrentTemp()
        return view
    }

    private fun getCurrentTemp() {

        WeatherClient.retrofitService.getCurrentWeather(
            "BVGRPZAsOY6qzmiUtScnKkBraRMnIOJ%2F26fTMonMRLgwniHt5fwhWHMSWxV9k5eVQdY00vxTVc2jNdpWLxrEbQ%3D%3D",
            "10", "1", "JSON", "11H10604"
        ).enqueue(object : Callback<Base> {
            override fun onFailure(call: Call<Base>, t: Throwable) {
                Log.d("Logg", t.message.toString())
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Base>, response: Response<Base>) {
                var checkTime = activity!!.timeHour.text.toString().toInt()

                loading2.text = null
                C.text = "ºC"


                if (checkTime in 5..11) { // 11~5시 사이에 확인 시 내일아침 기온
                    text2.text = response.body()?.response?.body?.items?.item?.get(2)?.ta.toString()

                    if (text2.text.toString().toInt() < 10) {
                        sentence2.text = "굉장히 추워요"
                        sentence2.setTextColor(Color.parseColor("#0080f0"))
                    } else if (text2.text.toString().toInt() in 10..19) {
                        sentence2.text = "쌀쌀해요"
                        sentence2.setTextColor(Color.parseColor("#50BCDF"))
                    } else if (text2.text.toString().toInt() in 20..29) {
                        sentence2.text = "따뜻해요"
                        sentence2.setTextColor(Color.parseColor("#FF7F00"))
                    } else {
                        sentence2.text = "굉장히 더워요"
                        sentence2.setTextColor(Color.parseColor("#ff0000"))
                    }


                } else { // 나머지 시간에 확인 시 내일 아침 기온
                    text2.text =
                        response.body()?.response?.body?.items?.item?.get(1)?.ta.toString()
                    if (text2.text.toString().toInt() < 10) {
                        sentence2.text = "굉장히 추워요"
                        sentence2.setTextColor(Color.parseColor("#0080f0"))
                    } else if (text2.text.toString().toInt() in 10..19) {
                        sentence2.text = "쌀쌀해요"
                        sentence2.setTextColor(Color.parseColor("#50BCDF"))
                    } else if (text2.text.toString().toInt() in 20..29) {
                        sentence2.text = "따뜻해요"
                        sentence2.setTextColor(Color.parseColor("#FF7F00"))
                    } else {
                        sentence2.text = "굉장히 더워요"
                        sentence2.setTextColor(Color.parseColor("#ff0000"))
                    }
                }

                if (checkTime in 5..11) { // 11~5시 사이에 확인 시 내일아침 날씨
                        weather_info.text= response.body()?.response?.body?.items?.item?.get(2)?.wf.toString()
                    if (weather_info.text == "맑음"){
                        weather_info.setTextColor(Color.parseColor("#ff8000"))
                        sunny_ani.visibility = View.VISIBLE //애니메이션 추가
                        cloud_ani.visibility = View.GONE
                        cloudy_ani.visibility = View.GONE

                    }else if(weather_info.text=="구름많음"){
                        weather_info.setTextColor(Color.parseColor("#c4c2bd"))
                        sunny_ani.visibility = View.GONE
                        cloud_ani.visibility = View.VISIBLE
                        cloudy_ani.visibility = View.GONE

                    }else{
                        weather_info.setTextColor(Color.parseColor("#848484"))
                        sunny_ani.visibility = View.GONE
                        cloud_ani.visibility = View.GONE
                        cloudy_ani.visibility = View.VISIBLE
                    }
                }else{
                    weather_info.text= response.body()?.response?.body?.items?.item?.get(1)?.wf.toString()
                    if (weather_info.text == "맑음"){
                        weather_info.setTextColor(Color.parseColor("#ff8000"))
                        sunny_ani.visibility = View.VISIBLE
                        cloud_ani.visibility = View.GONE
                        cloudy_ani.visibility = View.GONE
                    }else if(weather_info.text=="구름많음"){
                        weather_info.setTextColor(Color.parseColor("#c4c2bd"))
                        sunny_ani.visibility = View.GONE
                        cloud_ani.visibility = View.VISIBLE
                        cloudy_ani.visibility = View.GONE
                    }else{
                        weather_info.setTextColor(Color.parseColor("#848484"))
                        sunny_ani.visibility = View.GONE
                        cloud_ani.visibility = View.GONE
                        cloudy_ani.visibility = View.VISIBLE
                    }
                }

            } // onResponse끝
        })
    }
}