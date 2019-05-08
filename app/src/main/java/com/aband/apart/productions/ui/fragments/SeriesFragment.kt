package com.aband.apart.productions.ui.fragments

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aband.apart.productions.center.BaseFragment
import com.aband.apart.productions.R
import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.control.repository.SeriesRepository
import com.aband.apart.productions.data.api.ApiSeries
import com.aband.apart.productions.ui.adapter.SeriesOnTvAdapter
import com.aband.apart.productions.ui.adapter.SeriesPopularAdapter
import com.aband.apart.productions.ui.adapter.SeriesTopRatedAdapter
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_series.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SeriesFragment : BaseFragment() {

    lateinit var seriesViewModel: SeriesViewModel
    lateinit var seriesRepository: SeriesRepository
    lateinit var okHttpClient: OkHttpClient
    lateinit var retrofit: ApiSeries
    lateinit var builder: Gson
    var mAdapter: SeriesPopularAdapter? = null
    var mAdapterTopRated : SeriesTopRatedAdapter? = null
    var mAdaterOnTv : SeriesOnTvAdapter? = null

    override fun onFinishedViewLoad() {
        builder =
            GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setLenient().create()
        okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/tv/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(builder))
            .build()
            .create(ApiSeries::class.java)

        initializeUi()
    }

    private fun initializeUi() {
        seriesRepository = SeriesRepository(retrofit)
        seriesViewModel = SeriesViewModel(seriesRepository)
        seriesViewModel.getSeriesPopular()
        seriesViewModel.getSeriesTopRated()
        seriesViewModel.getSeriesOnTv()

        seriesViewModel.liveData.observe(this, recyclerPopular)
        seriesViewModel.liveData.observe(this, recyclerTopRated)
        seriesViewModel.liveData.observe(this, recyclerOnTv)

    }

    fun initAdapterTopRated(serieLocal: List<SerieLocal>){
        mAdapterTopRated = SeriesTopRatedAdapter(serieLocal)
        rvSeriesTopRated.adapter = mAdapterTopRated
    }

    fun initAdapterPopular(serieLocal: List<SerieLocal>){
        mAdapter = SeriesPopularAdapter(serieLocal)
        rvSeriesPopular.adapter = mAdapter
    }

    fun initAdapterOnTv(serieLocal: List<SerieLocal>){
        mAdaterOnTv = SeriesOnTvAdapter(serieLocal)
        rvSeriesOnTv.adapter = mAdaterOnTv
    }

    var recyclerPopular = Observer<List<SerieLocal>> { seriesLocal ->
        initAdapterPopular(seriesLocal)
    }

    var recyclerTopRated =  Observer<List<SerieLocal>> { seriesLocal ->
        initAdapterTopRated(seriesLocal)
    }

    var recyclerOnTv = Observer<List<SerieLocal>> { seriesLocal ->
        initAdapterOnTv(seriesLocal)
    }

    override fun fragmentLayout(): Int = R.layout.fragment_series
}