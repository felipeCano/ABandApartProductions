package com.aband.apart.productions.ui.detail

import com.aband.apart.productions.center.BaseViewModel
import com.aband.apart.productions.control.repository.SeriesRepository

class DetailViewModel (private val seriesRepository: SeriesRepository) : BaseViewModel() {

    fun getSeriesDetails(serieId : String){
        addDisposableDetail(seriesRepository.getDetailSeries(serieId))
    }
}