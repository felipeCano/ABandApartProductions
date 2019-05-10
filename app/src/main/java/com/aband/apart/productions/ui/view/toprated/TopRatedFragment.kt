package com.aband.apart.productions.ui.view.toprated

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.aband.apart.productions.R
import com.aband.apart.productions.center.BaseFragment
import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.control.repository.SeriesRepository
import com.aband.apart.productions.data.db.SerieDataBase
import com.aband.apart.productions.data.db.SeriesDao
import com.aband.apart.productions.ui.adapter.SeriesTopRatedAdapter
import com.aband.apart.productions.ui.interfaces.DetailSeriesInterface
import kotlinx.android.synthetic.main.fragment_top_rated.*

class TopRatedFragment : BaseFragment(), DetailSeriesInterface {

    private lateinit var topRatedViewModel: TopRatedViewModel
    private lateinit var seriesRepository: SeriesRepository
    private var mAdapterTopRated: SeriesTopRatedAdapter? = null

    override fun onFinishedViewLoad() {
        initializeUi()
    }

    private fun initializeUi() {
        seriesDao =serieDao(serieDataBase)
        seriesRepository = SeriesRepository(retrofit, seriesDao)
        topRatedViewModel = TopRatedViewModel(seriesRepository)
        topRatedViewModel.getSeriesTopRated()

        topRatedViewModel.liveData.observe(this, recyclerTopRated)
    }
    fun initAdapterTopRated(serieLocal: List<SerieLocal>) {
        mAdapterTopRated = SeriesTopRatedAdapter(serieLocal)
        mAdapterTopRated!!.onDetailsSeries(this)
        rvSeriesTopRated.adapter = mAdapterTopRated
    }

    var recyclerTopRated = Observer<List<SerieLocal>> { seriesLocal ->
        initAdapterTopRated(seriesLocal)
    }

    override fun onDetailSeries(serieLocal: SerieLocal) {
        var bundle = Bundle()
        bundle.putString("serieId", serieLocal.id)
        Log.d("idprueba", serieLocal.id)
        navController()!!.navigate(R.id.action_topRatedFragment_to_detailFragment)
    }

    fun serieDao(db: SerieDataBase): SeriesDao = db.movieDao()
    override fun fragmentLayout(): Int = R.layout.fragment_top_rated
}