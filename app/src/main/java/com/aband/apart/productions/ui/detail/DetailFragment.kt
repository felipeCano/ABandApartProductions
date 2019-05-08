package com.aband.apart.productions.ui.detail

import androidx.lifecycle.Observer
import com.aband.apart.productions.R
import com.aband.apart.productions.center.BaseFragment
import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.control.repository.SeriesRepository
import com.aband.apart.productions.data.api.ApiSeries
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_serie.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val PATH_DETAIL_SERIE = "https://image.tmdb.org/t/p/w500"
class DetailFragment : BaseFragment() {

    lateinit var seriesRepository: SeriesRepository
    lateinit var detailViewModel: DetailViewModel
    lateinit var okHttpClient: OkHttpClient
    lateinit var retrofit: ApiSeries
    lateinit var builder: Gson
    var serieId : String = ""
    override fun onFinishedViewLoad() {
        serieId = arguments!!.getString("serieId", serieId)
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
        detailViewModel = DetailViewModel(seriesRepository)
        detailViewModel.getSeriesDetails(serieId)

        detailViewModel.liveDataDetail.observe(this, viewDetails)

    }

    fun initViewDetailSerie(serieLocal: SerieLocal){
        titleDetail.text = serieLocal.originalName
        overrideDetail.text = serieLocal.overview
        Picasso.get()
            .load(PATH_DETAIL_SERIE + serieLocal.imageserie)
            .resize(300, 300)
            .centerCrop()
            .into(imageDetailSerie)
    }

    var viewDetails = Observer<SerieLocal> { seriesLocal ->
        initViewDetailSerie(seriesLocal)
    }

    override fun fragmentLayout(): Int = R.layout.fragment_detail_serie
}