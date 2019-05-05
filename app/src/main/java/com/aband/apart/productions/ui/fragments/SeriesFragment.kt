package com.aband.apart.productions.ui.fragments

import com.aband.apart.productions.center.BaseFragment
import com.aband.apart.productions.R
import com.aband.apart.productions.control.repository.SeriesRepository
import com.aband.apart.productions.data.api.ApiSeries
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SeriesFragment : BaseFragment() {

    lateinit var seriesViewModel: SeriesViewModel
    lateinit var seriesRepository: SeriesRepository
    lateinit var okHttpClient : OkHttpClient
    lateinit var retrofit : ApiSeries
    lateinit var builder : Gson

    override fun onFinishedViewLoad() {
        builder = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setLenient().create()
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

        seriesViewModel.getSeries()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()

    }

    override fun fragmentLayout(): Int = R.layout.fragment_series
}