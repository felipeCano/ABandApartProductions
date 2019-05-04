package com.aband.apart.productions.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Query
import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.control.model.remote.SerieRemote
import io.reactivex.Single

//@Dao
class
SeriesDao {
    private val seriesDao = mutableListOf<SerieRemote>()
    private val series = MutableLiveData<List<SerieRemote>>()

   /* @Query("SELECT * FROM serie WHERE id = :serieID")
    fun serie(serieID: String): Single<SerieLocal>*/


    init {
        series.value = seriesDao
    }

    fun addSerie(serieRemote: SerieRemote){
        seriesDao.add(serieRemote)
        series.value = seriesDao
    }

    fun getSeries() = series as LiveData<List<SerieRemote>>
}