package com.aband.apart.productions.control.repository

import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.data.db.SeriesDao

class SeriesRepository private constructor(private val seriesDao: SeriesDao) {

    fun librarySeries(serieLocal: List<SerieLocal>) {}

    companion object {
        // Singleton instantiation you already know and love
        @Volatile
        private var instance: SeriesRepository? = null

        /*fun getInstance(seriesDao: SeriesDao) = instance? : synchronized(this){

        }*/
    }
}