package com.example.projectschool

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import com.example.projectschool.data.Base
import com.example.projectschool.data.FoodBase
import com.example.projectschool.data.ScheduleBase
import com.example.projectschool.fragment.FoodFragment
import com.example.projectschool.fragment.ScheduleFragment
import com.example.projectschool.fragment.weatherFragment
import com.example.projectschool.retrofit.food.FoodClient
import com.example.projectschool.retrofit.schedule.ScheduleClient
import com.example.projectschool.retrofit.weather.WeatherClient
import com.project.simplecode.spDateFormat
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        time.text = spDateFormat("HH", 0)
        time2.text = spDateFormat("YYYYMMdd", 1)

        getCurrentWeather()

        setFrag(0)

        weather_frag_Btn.setOnClickListener {
            setFrag(0)
        }
        food_frag_Btn.setOnClickListener {
            setFrag(1)
        }
        schedule_frag_Btn.setOnClickListener {
            setFrag(2)
        }
    }

    fun getCurrentWeather() {

        WeatherClient.retrofitService.getCurrentWeather(
            "7UhrTwPM239CrHxGYdXDboLOSh7OmX65p12WEAaev2FNpUNCSPddYen1%2Fjh0VnQyQzXzg5nOwLlOxXPkRuggFQ%3D%3D",
            "10", "1", "JSON", "11H10604"
        ).enqueue(object : Callback<Base> {
            override fun onFailure(call: Call<Base>, t: Throwable) {
                Log.d("Logg", t.message)
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Base>, response: Response<Base>) {
                if (time.text.toString().toInt() in 5..11) { //5~11시 사이에 확인 시 강수확률
                    text1.text =
                        response.body()?.response?.body?.items?.item?.get(2)?.rnSt.toString() + "%"
                    if (text1.text == "30%" || text1.text == "20%" || text1.text == "10%" || text1.text == "0%") {
                        sentence.text = "아침점호 안나가긴 글렀네요.."
                    } else if (text1.text == "40%" || text1.text == "50%" || text1.text == "60%") {
                        sentence.text = "희망이 있어요~!"
                        imageView.setImageResource(R.drawable.soso)
                    } else {
                        sentence.text = "내일은 꿀잠 자겠네요 ㅎㅎ"
                        imageView.setImageResource(R.drawable.happy)
                    }

                } else { //나머지 시간에 확인 시 강수 확률
                    text1.text =
                        response.body()?.response?.body?.items?.item?.get(1)?.rnSt.toString() + "%"
                    if (text1.text == "30%" || text1.text == "20%" || text1.text == "10%" || text1.text == "0%") {
                        sentence.text = "아침점호 안나가긴 글렀네요.."
                    } else if (text1.text == "40%" || text1.text == "50%" || text1.text == "60%") {
                        sentence.text = "희망이 있어요~!"
                        imageView.setImageResource(R.drawable.soso)
                    } else {
                        sentence.text = "내일은 꿀잠 자겠네요 ㅎㅎ"
                        imageView.setImageResource(R.drawable.happy)
                    }
                }

            } // OnResponse 끝맺음
        })
    }

    private fun setFrag(fragNum: Int) {
        val ft = supportFragmentManager.beginTransaction() //화면 교체를 위한 트랜잭션
        when (fragNum) {
            0 -> {
                ft.replace(R.id.main_frame, weatherFragment()).commit()
            }
            1 -> {
                ft.replace(R.id.main_frame, FoodFragment()).commit()
            }
            2 -> {
                ft.replace(R.id.main_frame, ScheduleFragment()).commit()
            }
        }
    }
}