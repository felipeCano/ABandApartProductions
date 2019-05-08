package com.aband.apart.productions.ui.fragments

import com.aband.apart.productions.center.BaseViewModel
import com.aband.apart.productions.control.repository.SeriesRepository

class SeriesViewModel(private val seriesRepository: SeriesRepository) : BaseViewModel() {

    fun getSeriesPopular() {
        addDisposable(seriesRepository.getSeries())
    }

    fun getSeriesTopRated(){
        addDisposable(seriesRepository.getSeriesTodRated())
    }

    fun getSeriesOnTv(){
        addDisposable(seriesRepository.getSeriesOnTv())
    }
}