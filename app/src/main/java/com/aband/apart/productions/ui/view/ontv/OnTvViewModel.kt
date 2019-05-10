package com.aband.apart.productions.ui.view.ontv

import com.aband.apart.productions.center.BaseViewModel
import com.aband.apart.productions.control.repository.SeriesRepository

class OnTvViewModel(private val seriesRepository: SeriesRepository) : BaseViewModel() {

    fun getSeriesOnTv(){
        addDisposable(seriesRepository.getSeriesOnTv())
    }

}