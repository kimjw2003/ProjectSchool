package com.example.projectschool.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectschool.R
import com.example.projectschool.data.ScheduleBase
import com.example.projectschool.retrofit.schedule.ScheduleClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)


        getScheduletomorrow()

        return view
    }

    private fun getScheduletomorrow() {
        ScheduleClient.retrofitService3.getTomorrowSchedule(
            "4a316512f8fa44279ab02a99bf573341", "JSON", "1", "10",
            "D10", "7240393", "" + activity!!.time2.text
        ).enqueue(object : Callback<ScheduleBase> {
            override fun onFailure(call: Call<ScheduleBase>, t: Throwable) {
                Log.d("Logg", "xx")
            }

            override fun onResponse(call: Call<ScheduleBase>, response: Response<ScheduleBase>) {
                loading.text = null
                activity!!.first_schedule.text = response.body()?.SchoolSchedule?.get(1)?.row?.get(0)?.ONE_GRADE_EVENT_YN
                when(first_schedule.text){
                    "Y" -> {}
                    else -> {}
                }

                activity!!.second_schedule.text = response.body()?.SchoolSchedule?.get(1)?.row?.get(0)?.TW_GRADE_EVENT_YN
                when(second_schedule.text){
                    "Y" -> {}
                    else -> {}
                }

                activity!!.third_schedule.text = response.body()?.SchoolSchedule?.get(1)?.row?.get(0)?.THREE_GRADE_EVENT_YN
                when(third_schedule.text){
                    "Y" -> {}
                    else -> {}
                }

                activity!!.schedule_Tv.text = response.body()?.SchoolSchedule?.get(1)?.row?.get(0)?.EVENT_NM
                Log.d("Logg", "dd")
                when(schedule_Tv.text){
                    ""->{schedule_Tv.text = "내일은 공통된 학사일정이 없습니다"}
                }
            }
        })
    }
}