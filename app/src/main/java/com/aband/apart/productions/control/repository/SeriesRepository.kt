package com.aband.apart.productions.control.repository

import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.control.model.remote.SerieRemote
import com.aband.apart.productions.data.api.ApiSeries
import com.google.gson.Gson
import io.reactivex.Observable

class SeriesRepository (private val apiSeries: ApiSeries) {

    fun librarySeries(seriesRepository: List<SeriesRepository>) {}

   fun getSeries() : Observable<List<SerieLocal>>{
       return apiSeries.getPopularSeries().map { response ->
        Gson().fromJson(response, SerieRemote::class.java).results
       }
   }
    companion object {
        // Singleton instantiation you already know and love
        @Volatile private var instance: SeriesRepository? = null

        fun getInstance(apiSeries: ApiSeries) =
            instance ?: synchronized(this) {
                instance ?: SeriesRepository(apiSeries).also { instance = it }
            }
    }
}