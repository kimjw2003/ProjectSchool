package com.example.projectschool.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.projectschool.R
import com.example.projectschool.data.TimeBase
import com.example.projectschool.retrofit.time.TimeClient
import com.project.simplecode.spfToastShort
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_time.*
import kotlinx.android.synthetic.main.fragment_time.view.*
import retrofit2.Call
import retrofit2.Response

class TimeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_time, container, false)

        view.timeSet.setOnClickListener {

            closeKeyboard()

            if (view.grade_Et.text.toString() == "" && view.class_Et.text.toString() == "") {
                Toast.makeText(activity!!.applicationContext, "학년/반을 적어주세요", Toast.LENGTH_SHORT).show()
            } else if (view.grade_Et.text.toString() > "3" || view.grade_Et.text.toString() < "1" ||
                view.class_Et.text.toString() > "3" || view.class_Et.text.toString() < "1")
            {
                Toast.makeText(activity!!.applicationContext, "학년/반을 맞게 입력해 주세요", Toast.LENGTH_SHORT).show()
            } else {
                getTomorrowTime(view)
            }

            if(testEmpty.text.toString() == "empty") {
                spfToastShort("내일의 시간표가 존재하지 않습니다")
            }

        }
        return view
    } //onCreateView

    private fun closeKeyboard()
    {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun getTomorrowTime(view: View){
        TimeClient.retrofitService4.getTomorrowTime("02f6d779a6c748039f9d9b3ce649d8fb", "JSON", "1",
            "100", "D10", "7240393","2020", ""+activity!!.timeTomorrow.text,
            ""+view.grade_Et.text.toString(), ""+view.class_Et.text.toString()
        ).enqueue(object : retrofit2.Callback<TimeBase>{
            override fun onFailure(call: Call<TimeBase>, t: Throwable) {
                Log.d("Logd", t.message.toString())
            }

            override fun onResponse(call: Call<TimeBase>, response: Response<TimeBase>) {
                with(view) {

                    when(response.body()?.hisTimetable?.get(0)?.head?.get(0)?.list_total_count){
                        7 -> {
                            Log.d("Logd", "in 7")

                            val builder = AlertDialog.Builder(context)
                            val dialogView = layoutInflater.inflate(R.layout.dialog, null)
                            builder.setView(dialogView)
                                .setTitle(grade_Et.text.toString()+"학년"+class_Et.text.toString()+"반")
                                .setMessage("\n"+response.body()?.hisTimetable?.get(1)?.row?.get(0)?.ITRT_CNTNT + "\n"
                                        + response.body()?.hisTimetable?.get(1)?.row?.get(1)?.ITRT_CNTNT + "\n"
                                        + response.body()?.hisTimetable?.get(1)?.row?.get(2)?.ITRT_CNTNT + "\n"
                                        + response.body()?.hisTimetable?.get(1)?.row?.get(3)?.ITRT_CNTNT + "\n"
                                        + response.body()?.hisTimetable?.get(1)?.row?.get(4)?.ITRT_CNTNT + "\n"
                                        + response.body()?.hisTimetable?.get(1)?.row?.get(5)?.ITRT_CNTNT + "\n"
                                        + response.body()?.hisTimetable?.get(1)?.row?.get(6)?.ITRT_CNTNT )
                                .setNegativeButton("확인"){
                                    dialog, which ->
                                } .show()
                        }
                        6->{
                            Log.d("Logd", "in 6")

                            val builder = AlertDialog.Builder(context)
                            val dialogView = layoutInflater.inflate(R.layout.dialog, null)
                            builder.setView(dialogView)
                                .setTitle(grade_Et.text.toString()+"학년"+class_Et.text.toString()+"반")
                                .setMessage("\n"+response.body()?.hisTimetable?.get(1)?.row?.get(0)?.ITRT_CNTNT + "\n"
                                        + response.body()?.hisTimetable?.get(1)?.row?.get(1)?.ITRT_CNTNT + "\n"
                                        + response.body()?.hisTimetable?.get(1)?.row?.get(2)?.ITRT_CNTNT + "\n"
                                        + response.body()?.hisTimetable?.get(1)?.row?.get(3)?.ITRT_CNTNT + "\n"
                                        + response.body()?.hisTimetable?.get(1)?.row?.get(4)?.ITRT_CNTNT + "\n"
                                        + response.body()?.hisTimetable?.get(1)?.row?.get(5)?.ITRT_CNTNT )
                                .setNegativeButton("확인"){
                                        dialog, which ->
                                } .show()
                        }
                        else->{
                            Log.d("Logd", "nothing")
                            testEmpty.text = "empty"
                        }
                    } //when
                } //with
            } //OnResponse
        })
    }
}