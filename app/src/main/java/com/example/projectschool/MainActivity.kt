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
        getTomorrowFood()
        getScheduletomorrow()

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

                if (time.text.toString().toInt() in 5..11) { // 11~5시 사이에 확인 시 내일아침 기온
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
                } else { // 나머지 시간에 확인 시 내일 아침 강수 확률
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

            } // OnResponse 끝맺음
        })
    }

    fun getTomorrowFood(){
        FoodClient.retrofitService2.getTomorrowFood("e40fc13904d84da4a8d398649c324133", "JSON", "1","100",
            "D10", "7240393",""+time2.text
        ).enqueue(object : Callback<FoodBase>{
            override fun onFailure(call: Call<FoodBase>, t: Throwable) {
                Log.d("Logg", "x")
            }

            override fun onResponse(call: Call<FoodBase>, response: Response<FoodBase>) {

                foodText.text = response.body()?.mealServiceDietInfo?.get(1)?.row?.get(0)?.DDISH_NM
                foodText.text = Html.fromHtml(foodText.text.replace("[0-9]".toRegex(),"").replace(".",""))
            }

        })

    }

    fun getScheduletomorrow(){
        ScheduleClient.retrofitService3.getTomorrowSchedule("4a316512f8fa44279ab02a99bf573341", "JSON", "1", "10",
            "D10","7240393", ""+time2.text
        ).enqueue(object : Callback<ScheduleBase>{
            override fun onFailure(call: Call<ScheduleBase>, t: Throwable) {
                    Log.d("Logg", "xx")
            }

            override fun onResponse(call: Call<ScheduleBase>, response: Response<ScheduleBase>) {

                if(response.body()?.SchoolSchedule?.get(0)?.head2?.get(1)?.resulT2?.CODE == "INFO-200"){
                    schedule_Tv.text = " 오늘은 학사일정이 없습니다."
                }else {
                    schedule_Tv.text =
                        response.body()?.SchoolSchedule?.get(1)?.row?.get(1)?.EVENT_NM
                    Log.d("Logg", "dd")
                }
            }

        })
    }
}