package com.aband.apart.productions.center.utils

import com.aband.apart.productions.control.repository.SeriesRepository
import com.aband.apart.productions.data.DataBase
import com.aband.apart.productions.ui.fragments.SeriesViewModelFactory

object InjectorUtils {

    fun provideSeriesViewModelFactory() : SeriesViewModelFactory {
        val seriesRepository = SeriesRepository.getInstance(DataBase.getInstance().serieDao)
        return SeriesViewModelFactory(seriesRepository)
    }
}