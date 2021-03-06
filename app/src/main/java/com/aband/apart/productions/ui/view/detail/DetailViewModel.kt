package com.aband.apart.productions.ui.view.detail

import com.aband.apart.productions.center.BaseViewModel
import com.aband.apart.productions.control.repository.SeriesRepository

class DetailViewModel (private val seriesRepository: SeriesRepository) : BaseViewModel() {

    fun getSeriesDetails(serieId : String){
        addDisposableDetail(seriesRepository.getDetailSeries(serieId))
    }

    fun getDetailBd(serieId : String){
        addDisposableDetail(seriesRepository.detaiSeriesBd(serieId))
    }
}