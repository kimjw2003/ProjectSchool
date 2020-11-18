package com.example.projectschool.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectschool.R
import com.example.projectschool.data.InfoBase
import com.example.projectschool.retrofit.Info.InfoClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class InfoFragment : Fragment() {

    var schoolIf : String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)

        search_Btn.setOnClickListener {
            getSchoolInfo()
        }

        return view
    }

    private fun getSchoolInfo(){
        InfoClient.infoRetrofitService.getInfo("22e04c066ac54aba85ecbb3c61df041f", "JSON", "1", "100", ""+search_Et.text.toString()
        ).enqueue(object : retrofit2.Callback<InfoBase>{
            override fun onResponse(call: Call<InfoBase>, response: Response<InfoBase>) {
                var res = response.body()?.schoolInfo?.get(0)?.row?.get(0)
                schoolIf = res?.SCHUL_NM

            }

            override fun onFailure(call: Call<InfoBase>, t: Throwable) {
                Log.d("Logd", t.message.toString())
            }

        })
    }
}