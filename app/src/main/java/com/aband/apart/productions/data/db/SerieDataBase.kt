package com.aband.apart.productions.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aband.apart.productions.control.model.local.SerieLocal

@Database(entities = [SerieLocal::class], version = 1, exportSchema = false)
abstract class SerieDataBase : RoomDatabase(){
    abstract fun movieDao() : SeriesDao
}