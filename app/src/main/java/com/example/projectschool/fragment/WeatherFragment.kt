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

    var weather_Information : String? = null

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

                when(activity!!.timeHour.text.toString().toInt()){
                    in 5..11 ->{percent_Tv.text =
                        response.body()?.response?.body?.items?.item?.get(2)?.rnSt.toString() + "%"
                        when(percent_Tv.text){
                            "0%","10%","20%","30%" ->{
                                weather_icon.setImageResource(R.drawable.redman)
                            }
                            "40%","50%","60%" ->{
                                weather_icon.setImageResource(R.drawable.yellowman)
                            }
                            else ->{
                                weather_icon.setImageResource(R.drawable.blueman)
                            }
                        }
                    }
                    else -> {
                        percent_Tv.text =
                            response.body()?.response?.body?.items?.item?.get(1)?.rnSt.toString() + "%"
                        when(percent_Tv.text){
                            "0%","10%","20%","30%" ->{
                                weather_icon.setImageResource(R.drawable.redman)
                            }
                            "40%","50%","60%" ->{
                                weather_icon.setImageResource(R.drawable.yellowman)
                            }
                            else ->{
                                weather_icon.setImageResource(R.drawable.blueman)
                            }
                        }
                    }
                }

                when(checkTime) {
                    in 5..11 -> {
                        temp_Tv.text =
                            response.body()?.response?.body?.items?.item?.get(2)?.ta.toString()

                        if (temp_Tv.text.toString().toInt() < 10) {
                            weather_info.text = "굉장히 추워요"
                        } else if (temp_Tv.text.toString().toInt() in 10..19) {
                            weather_info.text = "쌀쌀해요"
                        } else if (temp_Tv.text.toString().toInt() in 20..29) {
                            weather_info.text = "따뜻해요"
                        } else {
                            weather_info.text = "굉장히 더워요"
                        }
                    }

                    else -> {
                        temp_Tv.text =
                            response.body()?.response?.body?.items?.item?.get(1)?.ta.toString()

                        if (temp_Tv.text.toString().toInt() < 10) {
                            weather_info.text = "내일은 굉장히 추워요"
                        } else if (temp_Tv.text.toString().toInt() in 10..19) {
                            weather_info.text = "내일은 쌀쌀해요"
                        } else if (temp_Tv.text.toString().toInt() in 20..29) {
                            weather_info.text = "내일은 따뜻해요"
                        } else {
                            weather_info.text = "내일은 굉장히 더워요"
                        }
                    }
                }

                if (checkTime in 5..11) { // 11~5시 사이에 확인 시 내일아침 날씨
                        weather_Information = response.body()?.response?.body?.items?.item?.get(2)?.wf.toString()

                    if (weather_Information == "맑음"){
                        sunny_ani.visibility = View.VISIBLE //애니메이션 추가
                        cloud_ani.visibility = View.GONE
                        cloudy_ani.visibility = View.GONE

                    }else if(weather_Information =="구름많음"){
                        sunny_ani.visibility = View.GONE
                        cloud_ani.visibility = View.VISIBLE
                        cloudy_ani.visibility = View.GONE

                    }else{
                        sunny_ani.visibility = View.GONE
                        cloud_ani.visibility = View.GONE
                        cloudy_ani.visibility = View.VISIBLE
                    }
                }else{
                    weather_Information = response.body()?.response?.body?.items?.item?.get(1)?.wf.toString()
                    if (weather_Information == "맑음"){
                        sunny_ani.visibility = View.VISIBLE
                        cloud_ani.visibility = View.GONE
                        cloudy_ani.visibility = View.GONE
                    }else if(weather_Information =="구름많음"){
                        sunny_ani.visibility = View.GONE
                        cloud_ani.visibility = View.VISIBLE
                        cloudy_ani.visibility = View.GONE
                    }else{
                        sunny_ani.visibility = View.GONE
                        cloud_ani.visibility = View.GONE
                        cloudy_ani.visibility = View.VISIBLE
                    }
                }

            } // onResponse끝
        })
    }
}