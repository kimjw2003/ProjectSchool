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
import com.project.simplecode.spDateFormat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleFragment : Fragment(){
    var first_schedule : String? = null
    var second_schedule : String? = null
    var third_schedule : String? = null

    var timeHourSche : String? = null
    var timeTomSche : String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        timeHourSche = spDateFormat("HH", 0)
        timeTomSche = spDateFormat("YYYYMMdd", 1)

        getScheduletomorrow()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pullToRefreshSche.setOnRefreshListener {
            timeHourSche = spDateFormat("HH", 0)
            timeTomSche = spDateFormat("YYYYMMdd", 1)

            getScheduletomorrow()
            pullToRefreshSche.isRefreshing = false
        }
    }

    private fun getScheduletomorrow() {
        ScheduleClient.retrofitService3.getTomorrowSchedule(
            "4a316512f8fa44279ab02a99bf573341", "JSON", "1", "10",
            "D10", "7240393","" + timeTomSche
        ).enqueue(object : Callback<ScheduleBase> {
            override fun onFailure(call: Call<ScheduleBase>, t: Throwable) {
                Log.d("Logg", t.message.toString())
            }

            override fun onResponse(call: Call<ScheduleBase>, response: Response<ScheduleBase>) {
                first_schedule = response.body()?.SchoolSchedule?.get(1)?.row?.get(0)?.ONE_GRADE_EVENT_YN
                when(first_schedule){
                    "Y" -> {first_yes_ani.visibility = View.VISIBLE
                            first_no_ani.visibility = View.GONE
                    }
                    else -> {first_yes_ani.visibility = View.GONE
                             first_no_ani.visibility = View.VISIBLE
                    }
                }

                second_schedule = response.body()?.SchoolSchedule?.get(1)?.row?.get(0)?.TW_GRADE_EVENT_YN
                when(second_schedule){
                    "Y" -> {second_yes_ani.visibility = View.VISIBLE
                            second_no_ani.visibility = View.GONE
                    }
                    else -> {second_yes_ani.visibility = View.GONE
                             second_no_ani.visibility = View.VISIBLE
                    }
                }

                third_schedule = response.body()?.SchoolSchedule?.get(1)?.row?.get(0)?.THREE_GRADE_EVENT_YN
                when(third_schedule){
                    "Y" -> {third_yes_ani.visibility = View.VISIBLE
                            third_no_ani.visibility = View.GONE
                    }
                    else -> {third_yes_ani.visibility = View.GONE
                             third_no_ani.visibility = View.VISIBLE
                    }
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