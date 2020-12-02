package com.example.projectschool

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.projectschool.adapter.MyPagerAdapter
import com.example.projectschool.data.Base
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

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)

        viewpager_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int){
                tabs_main.getTabAt(0)?.setIcon(R.drawable.weather)
                tabs_main.getTabAt(1)?.setIcon(R.drawable.calendar)
                tabs_main.getTabAt(2)?.setIcon(R.drawable.food)
                tabs_main.getTabAt(3)?.setIcon(R.drawable.time)

                when(position) {

                    0   ->    tabs_main.getTabAt(0)?.setIcon(R.drawable.weather_blue)
                    1   ->    tabs_main.getTabAt(1)?.setIcon(R.drawable.calendar_blue)
                    2   ->    tabs_main.getTabAt(2)?.setIcon(R.drawable.food_blue)
                    3   ->    tabs_main.getTabAt(3)?.setIcon(R.drawable.time_blue)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })

        tabs_main.getTabAt(0)?.setIcon(R.drawable.weather_blue)
        tabs_main.getTabAt(1)?.setIcon(R.drawable.calendar)
        tabs_main.getTabAt(2)?.setIcon(R.drawable.food)
        tabs_main.getTabAt(3)?.setIcon(R.drawable.time)

    }
}