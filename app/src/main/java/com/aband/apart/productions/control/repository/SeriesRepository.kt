package com.aband.apart.productions.control.repository

import android.util.Log
import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.control.model.remote.SerieRemote
import com.aband.apart.productions.data.api.ApiSeries
import com.aband.apart.productions.data.db.SeriesDao
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class SeriesRepository(
    private val apiSeries: ApiSeries,
    private val seriesDao: SeriesDao
) {

    fun popularSeries() : Observable<List<SerieLocal>>{
        return getSeries().onErrorResumeNext(serieFromBd())
    }
    fun topRatedSeries() : Observable<List<SerieLocal>>{
        return getSeriesTodRated().onErrorResumeNext(serieFromBd())
    }
    fun ontvSeries() : Observable<List<SerieLocal>>{
        return getSeriesOnTv().onErrorResumeNext(serieFromBd())
    }

    fun detaiSeriesBd(serieId : String) : Observable<SerieLocal>{
        return getDetailSeries(serieId).onErrorResumeNext(detailFromBd(serieId))
    }


    fun getSeries(): Observable<List<SerieLocal>> {
        return apiSeries.getPopularSeries().map { response ->
            Log.d("getPopular", response.toString())
            Gson().fromJson(response, SerieRemote::class.java).results

        }.doOnNext {
            saveSeries(it)
            Log.e("getPopular", it.toString())
        }
    }

    fun getSeriesTodRated(): Observable<List<SerieLocal>> {
        return apiSeries.getTopRatedSeries().map { response ->
            Log.d("getTopRated", response.toString())
            Gson().fromJson(response, SerieRemote::class.java).results
        }.doOnNext {
            saveSeries(it)
        }
    }

    fun getSeriesOnTv(): Observable<List<SerieLocal>> {
        return apiSeries.getOnTvSeries().map { response ->
            Log.d("getOntv", response.toString())
            Gson().fromJson(response, SerieRemote::class.java).results
        }.doOnNext {
            saveSeries(it)
        }
    }

    fun getDetailSeries(serieId: String): Observable<SerieLocal> {
        return apiSeries.getDetailSerie(serieId).map { response ->
            Gson().fromJson(response, SerieLocal::class.java)
        }.doOnNext {
            saveSerie(it)
            Log.e("getDetailSeries", it.toString())
        }
            .doOnError {
                Log.e("getDetailSeries1", it.toString())
            }
    }

    fun saveSeries(serieLocal: List<SerieLocal>) {
        Observable.fromCallable { seriesDao.insertSeries(serieLocal) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnError {
                Log.e( "errorsave","Unable to store movie")
            }
            .doOnNext { Log.i("errorsave"," Movie stored!") }
            .subscribe()
    }


    fun saveSerie(serieLocal: SerieLocal) {
        Observable.fromCallable { seriesDao.insertSerie(serieLocal) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }

    private fun serieFromBd() :  Observable<List<SerieLocal>>{
        return seriesDao.series().toObservable()
            .doOnNext{
                if (it.isNotEmpty()){
                    it
                }else{
                    Observable.just(emptyList<SerieLocal>())
                }
            }
    }

    private fun detailFromBd(serieId: String): Observable<SerieLocal> {
        return seriesDao.serie(serieId).filter { it != null }
            .toObservable()
            .doOnNext {
                Log.d("detailFromBd","Dispatching ${it.id} serie from DB...")
            }
    }

}