package com.aband.apart.productions.ui.view.popular

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.aband.apart.productions.center.BaseFragment
import com.aband.apart.productions.R
import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.control.repository.SeriesRepository
import com.aband.apart.productions.data.db.SerieDataBase
import com.aband.apart.productions.data.db.SeriesDao
import com.aband.apart.productions.ui.adapter.SeriesPopularAdapter
import com.aband.apart.productions.ui.interfaces.DetailSeriesInterface
import kotlinx.android.synthetic.main.fragment_series.*


class SeriesFragment : BaseFragment(), DetailSeriesInterface {

    lateinit var seriesViewModel: PopularViewModel
    lateinit var seriesRepository: SeriesRepository
    var mAdapter: SeriesPopularAdapter? = null


    override fun onFinishedViewLoad() {
        initializeUi()
    }

    private fun initializeUi() {
        seriesDao = serieDao(serieDataBase)
        seriesRepository = SeriesRepository(retrofit, seriesDao)
        seriesViewModel = PopularViewModel(seriesRepository)
        seriesViewModel.getSeriesPopular()
        seriesViewModel.getSeriesBd()


        seriesViewModel.liveData.observe(this, recyclerPopular)
    }

    fun initAdapterPopular(serieLocal: List<SerieLocal>) {
        mAdapter = SeriesPopularAdapter(serieLocal)
        mAdapter!!.onDetailsSeries(this)
        rvSeriesPopular.adapter = mAdapter
    }

    var recyclerPopular = Observer<List<SerieLocal>> { seriesLocal ->
        initAdapterPopular(seriesLocal)
    }

    override fun onDetailSeries(serieLocal: SerieLocal) {
        var bundle = Bundle()
        bundle.putString("serieId", serieLocal.id)
        Log.d("idprueba", serieLocal.id)
        navController()!!.navigate(R.id.action_seriesFragment_to_detailFragment, bundle)
    }

    override fun fragmentLayout(): Int = R.layout.fragment_series

    fun serieDao(db: SerieDataBase): SeriesDao = db.movieDao()

    override fun onDestroy() {
        super.onDestroy()
       // seriesViewModel.clearDisposable()
    }
}