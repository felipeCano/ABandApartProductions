package com.aband.apart.productions.ui.view.popular

import com.aband.apart.productions.center.BaseViewModel
import com.aband.apart.productions.control.repository.SeriesRepository

class PopularViewModel(private val seriesRepository: SeriesRepository) : BaseViewModel() {

    fun getSeriesPopular() {
        addDisposable(seriesRepository.getSeries())
    }

    fun getSeriesBd(){
        addDisposable(seriesRepository.popularSeries())
    }

    fun clearDisposable(){
        clearDisposable()
    }
}