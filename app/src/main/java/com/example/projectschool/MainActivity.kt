package com.example.projectschool

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.projectschool.data.Base
import com.example.projectschool.fragment.*
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
        timeTomorrow.text = spDateFormat("YYYYMMdd", 3)

        getCurrentWeather()

        setFrag(0)

        weather_frag_Btn.setOnClickListener {
            weatherFrag()
        }

        food_frag_Btn.setOnClickListener {
            foodFrag()
        }

        schedule_frag_Btn.setOnClickListener {
            scheduleFrag()
        }

        time_frag_Btn.setOnClickListener {
            timeFrag()
        }
    }

    private fun getCurrentWeather() {

        WeatherClient.retrofitService.getCurrentWeather(
            "7UhrTwPM239CrHxGYdXDboLOSh7OmX65p12WEAaev2FNpUNCSPddYen1%2Fjh0VnQyQzXzg5nOwLlOxXPkRuggFQ%3D%3D",
            "10", "1", "JSON", "11H10604"
        ).enqueue(object : Callback<Base> {
            override fun onFailure(call: Call<Base>, t: Throwable) {
                Log.d("Logg", t.message.toString())
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Base>, response: Response<Base>) {

                when(timeHour.text.toString().toInt()){
                    in 5..11 ->{text1.text =
                        response.body()?.response?.body?.items?.item?.get(2)?.rnSt.toString() + "%"
                        when(text1.text){
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
                        text1.text =
                            response.body()?.response?.body?.items?.item?.get(1)?.rnSt.toString() + "%"
                        when(text1.text){
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
        })
    }

    private fun setFrag(fragNum: Int) {
        val ft = supportFragmentManager.beginTransaction() //화면 교체를 위한 트랜잭션
        when (fragNum) {
            0 -> {
                ft.replace(R.id.main_frame, WeatherFragment()).commit()
            }
            1 -> {
                ft.replace(R.id.main_frame, FoodFragment()).commit()
            }
            2 -> {
                ft.replace(R.id.main_frame, ScheduleFragment()).commit()
            }
            3 -> {
                ft.replace(R.id.main_frame, TimeFragment()).commit()
            }
        }
    }

    private fun weatherFrag() {
        setFrag(0)
        weather_frag_Btn.setTextColor(Color.parseColor("#1385cc"))
        food_frag_Btn.setTextColor(Color.parseColor("#000000"))
        schedule_frag_Btn.setTextColor(Color.parseColor("#000000"))
        time_frag_Btn.setTextColor(Color.parseColor("#000000"))

        weather_frag_Btn.setBackgroundColor(Color.parseColor("#00ff0000"))
        food_frag_Btn.setBackgroundColor(Color.parseColor("#ffffff"))
        schedule_frag_Btn.setBackgroundColor(Color.parseColor("#ffffff"))
        time_frag_Btn.setBackgroundColor(Color.parseColor("#ffffff"))
    }

    private fun foodFrag() {
        setFrag(1)
        weather_frag_Btn.setTextColor(Color.parseColor("#000000"))
        food_frag_Btn.setTextColor(Color.parseColor("#f08400"))
        schedule_frag_Btn.setTextColor(Color.parseColor("#000000"))
        time_frag_Btn.setTextColor(Color.parseColor("#000000"))

        weather_frag_Btn.setBackgroundColor(Color.parseColor("#ffffff"))
        food_frag_Btn.setBackgroundColor(Color.parseColor("#00ff0000"))
        schedule_frag_Btn.setBackgroundColor(Color.parseColor("#ffffff"))
        time_frag_Btn.setBackgroundColor(Color.parseColor("#ffffff"))
    }

    private fun scheduleFrag() {
        setFrag(2)
        weather_frag_Btn.setTextColor(Color.parseColor("#000000"))
        food_frag_Btn.setTextColor(Color.parseColor("#000000"))
        schedule_frag_Btn.setTextColor(Color.parseColor("#00eaff"))
        time_frag_Btn.setTextColor(Color.parseColor("#000000"))

        weather_frag_Btn.setBackgroundColor(Color.parseColor("#ffffff"))
        food_frag_Btn.setBackgroundColor(Color.parseColor("#ffffff"))
        schedule_frag_Btn.setBackgroundColor(Color.parseColor("#00ff0000"))
        time_frag_Btn.setBackgroundColor(Color.parseColor("#ffffff"))
    }

    private fun timeFrag() {
        setFrag(3)

        weather_frag_Btn.setTextColor(Color.parseColor("#000000"))
        food_frag_Btn.setTextColor(Color.parseColor("#000000"))
        schedule_frag_Btn.setTextColor(Color.parseColor("#000000"))
        time_frag_Btn.setTextColor(Color.parseColor("#60e01b"))

        weather_frag_Btn.setBackgroundColor(Color.parseColor("#ffffff"))
        food_frag_Btn.setBackgroundColor(Color.parseColor("#ffffff"))
        schedule_frag_Btn.setBackgroundColor(Color.parseColor("#ffffff"))
        time_frag_Btn.setBackgroundColor(Color.parseColor("#00ff0000"))
    }
}