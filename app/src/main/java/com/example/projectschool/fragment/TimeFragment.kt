package com.example.projectschool.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectschool.R
import com.example.projectschool.data.TimeBase
import com.example.projectschool.retrofit.time.TimeClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_time.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class TimeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_time, container, false)

        getTomorrowTime()
        return view
    }

    fun getTomorrowTime(){
        TimeClient.retrofitService4.getTomorrowTime("02f6d779a6c748039f9d9b3ce649d8fb", "JSON", "1",
            "100", "D10", "7240393", "2020", ""+activity!!.time2,
            ""+grade_Et, ""+class_Et
        ).enqueue(object : retrofit2.Callback<TimeBase>{
            override fun onFailure(call: Call<TimeBase>, t: Throwable) {
                Log.d("Logg", "xx")
            }

            override fun onResponse(call: Call<TimeBase>, response: Response<TimeBase>) {

            }

        })
    }
}
