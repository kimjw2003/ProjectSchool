package com.example.projectschool

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.projectschool.data.Base
import com.example.projectschool.retrofit.RetrofitClient
import com.project.simplecode.spDateFormatNow
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        time.text = spDateFormatNow("HH")

        getCurrentWeather()

    }

    fun getCurrentWeather() {

        RetrofitClient.retrofitService.getCurrentWeather(
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

            }
        })
    }
}