package com.aband.apart.productions.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aband.apart.productions.control.model.local.SerieLocal

class SeriesDao {
    private val seriesDao = mutableListOf<SerieLocal>()
    private val series = MutableLiveData<List<SerieLocal>>()

    init {
        series.value = seriesDao
    }

    fun addSerie(serieLocal: SerieLocal){
        seriesDao.add(serieLocal)
        series.value = seriesDao
    }

    fun getSeries() = series as LiveData<List<SerieLocal>>
}