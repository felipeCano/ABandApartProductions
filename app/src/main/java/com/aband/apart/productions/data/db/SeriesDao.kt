package com.aband.apart.productions.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aband.apart.productions.control.model.local.SerieLocal
import io.reactivex.Single


@Dao
interface SeriesDao {
    @Query("SELECT * FROM SerieLocal WHERE id = :serieId")
    fun serie(serieId: String): Single<SerieLocal>

    @Query("SELECT * FROM SerieLocal WHERE id ")
    fun series(): Single<List<SerieLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(movies: List<SerieLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSerie(movies: SerieLocal)

}