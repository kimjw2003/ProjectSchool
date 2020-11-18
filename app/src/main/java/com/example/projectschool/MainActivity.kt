package com.example.projectschool

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.projectschool.adapter.MyPagerAdapter
import com.example.projectschool.data.Base
import com.example.projectschool.retrofit.weather.WeatherClient
import com.project.simplecode.spDateFormat
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeHour.text = spDateFormat("HH", 0)
        timeTomorrow.text = spDateFormat("YYYYMMdd", 1)

        getCurrentWeather()

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)

    }

    private fun getCurrentWeather() {

        WeatherClient.retrofitService.getCurrentWeather(
            "BVGRPZAsOY6qzmiUtScnKkBraRMnIOJ%2F26fTMonMRLgwniHt5fwhWHMSWxV9k5eVQdY00vxTVc2jNdpWLxrEbQ%3D%3D",
            "10", "1", "JSON", "11H10604"
        ).enqueue(object : Callback<Base> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Base>, response: Response<Base>) {

                when(timeHour.text.toString().toInt()){
                    in 5..11 ->{percent_Tv.text =
                        response.body()?.response?.body?.items?.item?.get(2)?.rnSt.toString() + "%"
                        when(percent_Tv.text){
                            "0%","10%","20%","30%" ->{
                                sentence.text = "아침점호 안나가긴 글렀네요.."
                            }
                            "40%","50%","60%" ->{
                                sentence.text = "희망이 있어요~!"
                                imageView.setImageResource(R.drawable.soso)
                            }
                            else ->{
                                sentence.text = "내일은 꿀잠 자겠네요 ㅎㅎ"
                                imageView.setImageResource(R.drawable.happy)
                            }
                        }
                    }
                    else -> {
                        percent_Tv.text =
                            response.body()?.response?.body?.items?.item?.get(1)?.rnSt.toString() + "%"
                        when(percent_Tv.text){
                            "0%","10%","20%","30%" ->{
                                sentence.text = "아침점호 안나가긴 글렀네요.."
                            }
                            "40%","50%","60%" ->{
                                sentence.text = "희망이 있어요~!"
                                imageView.setImageResource(R.drawable.soso)
                            }
                            else ->{
                                sentence.text = "내일은 꿀잠 자겠네요 ㅎㅎ"
                                imageView.setImageResource(R.drawable.happy)
                            }
                        }
                    }
                }
            } // OnResponse 끝맺음
            override fun onFailure(call: Call<Base>, t: Throwable) {
                Log.d("Logg", t.message.toString())
            }
        })
    }
}