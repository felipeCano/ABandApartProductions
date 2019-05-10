package com.aband.apart.productions.ui.view.ontv

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.room.Room
import com.aband.apart.productions.R
import com.aband.apart.productions.center.BaseFragment
import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.control.repository.SeriesRepository
import com.aband.apart.productions.data.api.ApiSeries
import com.aband.apart.productions.data.db.SerieDataBase
import com.aband.apart.productions.data.db.SeriesDao
import com.aband.apart.productions.ui.adapter.SeriesOnTvAdapter
import com.aband.apart.productions.ui.interfaces.DetailOnTvInterface
import com.aband.apart.productions.ui.interfaces.DetailSeriesInterface
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_on_tv.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class OnTvFragment : BaseFragment(), DetailOnTvInterface {

    private lateinit var onTvViewModel: OnTvViewModel
    private lateinit var seriesRepository: SeriesRepository
    lateinit var mAdaterOnTv: SeriesOnTvAdapter

    override fun onFinishedViewLoad() {
        initializeUi()
    }

    private fun initializeUi() {
        seriesDao = serieDao(serieDataBase)
        seriesRepository = SeriesRepository(retrofit, seriesDao)
        onTvViewModel = OnTvViewModel(seriesRepository)
        onTvViewModel.getSeriesOnTv()
        onTvViewModel.getOnTvBd()

        onTvViewModel.liveData.observe(this, recyclerOnTv)
    }

    fun initAdapterOnTv(serieLocal: List<SerieLocal>) {
        mAdaterOnTv = SeriesOnTvAdapter(serieLocal)
        mAdaterOnTv.onDetailsSeries(this)
        rvSeriesOnTv.adapter = mAdaterOnTv
    }

    var recyclerOnTv = Observer<List<SerieLocal>> { seriesLocal ->
        initAdapterOnTv(seriesLocal)
    }

    override fun onDetailOnTvInterface(serieLocal: SerieLocal) {
        var bundle = Bundle()
        bundle.putString("serieId", serieLocal.id)
        Log.d("idprueba", serieLocal.id)
        navController()!!.navigate(R.id.action_onTvFragment_to_detailFragment, bundle)
    }

    fun serieDao(db: SerieDataBase): SeriesDao = db.movieDao()

    override fun fragmentLayout(): Int = R.layout.fragment_on_tv

    override fun onDestroy() {
        super.onDestroy()
       // onTvViewModel.clearDisposable()
    }
}