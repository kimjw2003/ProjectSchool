package com.example.projectschool.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.projectschool.R
import com.example.projectschool.fragment.FoodFragment
import com.example.projectschool.fragment.ScheduleFragment
import com.example.projectschool.fragment.TimeFragment
import com.example.projectschool.fragment.WeatherFragment

class MyPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                WeatherFragment()
            }
            1 -> {
                ScheduleFragment()
            }
            2 ->{
                FoodFragment()
            }
            else -> {return TimeFragment()
            }
        }
    }



    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return null
    }
}