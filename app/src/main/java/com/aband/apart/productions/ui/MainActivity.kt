package com.aband.apart.productions.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aband.apart.productions.R
import com.aband.apart.productions.center.SeriesAdapterFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mSeriesAdapterFragment: SeriesAdapterFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }
    fun initFragment (){
       // val series = SeriesHelper.getSeriesFromJson("series.json", this)
        mSeriesAdapterFragment = SeriesAdapterFragment(supportFragmentManager)
        pager.adapter = mSeriesAdapterFragment
        tabLayout.setupWithViewPager(pager)
        //pager.currentItem = mSeriesAdapterFragment.count /2
    }
}
