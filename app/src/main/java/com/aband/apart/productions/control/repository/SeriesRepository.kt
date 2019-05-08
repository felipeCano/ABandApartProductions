package com.aband.apart.productions.control.repository

import android.util.Log
import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.control.model.remote.SerieRemote
import com.aband.apart.productions.data.api.ApiSeries
import com.google.gson.Gson
import io.reactivex.Observable

class SeriesRepository (private val apiSeries: ApiSeries) {

   fun getSeries() : Observable<List<SerieLocal>> {
       return apiSeries.getPopularSeries().map { response ->
           Log.d("getPopular", response.toString())
           Gson().fromJson(response, SerieRemote::class.java).results

       }.doOnNext {
           Log.e("getPopular", it.toString())
       }
           .doOnError {
               Log.e("getPopular", it.toString())
           }
   }

    fun getSeriesTodRated() : Observable<List<SerieLocal>>{
        return apiSeries.getTopRatedSeries().map { response->
            Log.d("getTopRated", response.toString())
            Gson().fromJson(response, SerieRemote::class.java).results
        }
    }

    fun getSeriesOnTv() : Observable<List<SerieLocal>>{
        return apiSeries.getOnTvSeries().map { response->
            Log.d("getOntv", response.toString())
            Gson().fromJson(response, SerieRemote::class.java).results
        }
    }

}