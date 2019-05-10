package com.aband.apart.productions.ui.view.menu

import com.aband.apart.productions.R
import com.aband.apart.productions.center.BaseFragment
import kotlinx.android.synthetic.main.menu_series.*

class MenuSeries : BaseFragment() {

    override fun onFinishedViewLoad() {
        btnPopular.setOnClickListener {
            navController()!!.navigate(MenuSeriesDirections.actionMenuSeriesToSeriesFragment())
        }
        btnTopRated.setOnClickListener {
            navController()!!.navigate(MenuSeriesDirections.actionMenuSeriesToTopRatedFragment())
        }
        btnOnTv.setOnClickListener {
            navController()!!.navigate(MenuSeriesDirections.actionMenuSeriesToOnTvFragment())
        }
    }

    override fun fragmentLayout(): Int = R.layout.menu_series
}