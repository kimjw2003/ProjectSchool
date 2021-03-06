package com.example.projectschool.fragment

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectschool.R
import com.example.projectschool.data.FoodBase
import com.example.projectschool.retrofit.food.FoodClient
import com.project.simplecode.spDateFormat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_food.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodFragment : Fragment(){

    var timeHourFood : String? = null
    var timeTomFood : String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_food, container, false)
        timeHourFood = spDateFormat("HH", 0)
        timeTomFood = spDateFormat("YYYYMMdd", 1)

        getTomorrowFood()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pullToRefreshFood.setOnRefreshListener {
            timeHourFood = spDateFormat("HH", 0)
            timeTomFood = spDateFormat("YYYYMMdd", 1)

            getTomorrowFood()
            pullToRefreshFood.isRefreshing = false
        }
    }

    private fun getTomorrowFood() {
        FoodClient.retrofitService2.getTomorrowFood(
            "e40fc13904d84da4a8d398649c324133", "JSON", "1", "100",
            "D10", "7240393", "" + timeTomFood
        ).enqueue(object : Callback<FoodBase> {
            override fun onFailure(call: Call<FoodBase>, t: Throwable) {
                Log.d("Logg", "x")
            }

            override fun onResponse(call: Call<FoodBase>, response: Response<FoodBase>) {

                Log.d("Logd", timeTomFood.toString())

                activity!!.foodText.text = response.body()?.mealServiceDietInfo?.get(1)?.row?.get(0)?.DDISH_NM
                when(foodText.text){
                    ""->{foodText.text = "급식이 없어요"}
                    else->{activity!!.foodText.text =
                        Html.fromHtml(foodText.text.replace("[0-9]".toRegex(), "").replace(".", ""))}
                }

                activity!!.calori.text = response.body()?.mealServiceDietInfo?.get(1)?.row?.get(0)?.CAL_INFO

            } //onResponse
        })
    }
}