package com.aband.apart.productions.data

import com.aband.apart.productions.data.db.SeriesDao

class DataBase private constructor(){
    var serieDao = SeriesDao()
    private set
    companion object{
        @Volatile private var instance: DataBase? = null

        fun getInstance() =
        // Already instantiated? - return the instance
            // Otherwise instantiate in a thread-safe manner
            instance ?: synchronized(this) {
                // If it's still not instantiated, finally create an object
                // also set the "instance" property to be the currently created one
                instance ?: DataBase().also { instance = it }
            }
    }
}