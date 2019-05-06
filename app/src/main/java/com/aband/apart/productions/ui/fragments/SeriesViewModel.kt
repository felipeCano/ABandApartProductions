package com.aband.apart.productions.ui.fragments

import com.aband.apart.productions.center.BaseViewModel
import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.control.model.remote.SerieRemote
import com.aband.apart.productions.control.repository.SeriesRepository

class SeriesViewModel(private val seriesRepository: SeriesRepository) : BaseViewModel() {

    fun addSeries(seriesRemote: List<SeriesRepository>){
        seriesRepository.librarySeries(seriesRemote)
    }

    //fun getSeries() = seriesRepository.getSeries()
    fun getSeries() {
        addDisposable(seriesRepository.getSeries())
    }
}