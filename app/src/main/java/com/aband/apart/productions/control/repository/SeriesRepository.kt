package com.aband.apart.productions.control.repository

import android.util.Log
import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.control.model.remote.SerieRemote
import com.aband.apart.productions.data.api.ApiSeries
import com.google.gson.Gson
import io.reactivex.Observable

class SeriesRepository (private val apiSeries: ApiSeries) {

    fun librarySeries(seriesRepository: List<SeriesRepository>) {}

   fun getSeries() : Observable<List<SerieLocal>> {
       return apiSeries.getPopularSeries().map { response ->
           Log.d("pruebaResponse", response.toString())
           Gson().fromJson(response, SerieRemote::class.java).results

       }.doOnNext {
           Log.e("abuelita1", it.toString())
       }
           .doOnError {
               Log.e("abuelita", it.toString())
           }
   }

}