package com.aband.apart.productions.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aband.apart.productions.R
import com.aband.apart.productions.center.SeriesAdapterFragment

class MainActivity : AppCompatActivity() {
    private lateinit var mSeriesAdapterFragment: SeriesAdapterFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }
    fun initFragment (){
        mSeriesAdapterFragment = SeriesAdapterFragment(supportFragmentManager)
       /* pager.adapter = mSeriesAdapterFragment
        tabLayout.setupWithViewPager(pager)*/
    }
}
