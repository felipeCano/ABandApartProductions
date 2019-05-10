package com.aband.apart.productions.ui.view.toprated

import com.aband.apart.productions.center.BaseViewModel
import com.aband.apart.productions.control.repository.SeriesRepository

class TopRatedViewModel(private val seriesRepository: SeriesRepository) : BaseViewModel() {

    fun getSeriesTopRated(){
        addDisposable(seriesRepository.getSeriesTodRated())
    }

    fun clearDisposable(){
        clearDisposable()
    }
}