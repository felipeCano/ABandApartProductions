package com.aband.apart.productions.control.repository

import android.util.Log
import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.data.db.SeriesDao
import io.reactivex.Observable

class SeriesRepository /*private constructor*/(private val seriesDao: SeriesDao) {

    fun librarySeries(seriesRepository: List<SeriesRepository>) {}

    /*fun addQuote(quote: SerieRemote) {
        seriesDao.addSerie(quote)
    }*/

    //fun getSeries() = seriesDao.getSeries()

    private fun movieFromBd(movieId: String): Observable<SerieLocal> {
        return seriesDao.serie(movieId).filter { it != null }
            .toObservable()
            .doOnNext {
                Log.d("aa","Dispatching ${it.id} movie from DB...")
            }
    }

    fun searchMovie(serieId: String): Observable<SerieLocal> = seriesDao.serie(serieId).toObservable()


    companion object {
        // Singleton instantiation you already know and love
        @Volatile private var instance: SeriesRepository? = null

        fun getInstance(seriesDao: SeriesDao) =
            instance ?: synchronized(this) {
                instance ?: SeriesRepository(seriesDao).also { instance = it }
            }
    }
}