package com.aband.apart.productions.ui.fragments

import com.aband.apart.productions.center.BaseViewModel
import com.aband.apart.productions.control.repository.SeriesRepository

class SeriesViewModel(private val seriesRepository: SeriesRepository) : BaseViewModel() {

    fun addSeries(seriesRemote: List<SeriesRepository>){
        seriesRepository.librarySeries(seriesRemote)
    }

    fun getSeries() {
        addDisposable(seriesRepository.getSeries())
    }
}