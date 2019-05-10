package com.aband.apart.productions.ui.view.detail

import androidx.lifecycle.Observer
import com.aband.apart.productions.R
import com.aband.apart.productions.center.BaseFragment
import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.control.repository.SeriesRepository
import com.aband.apart.productions.data.db.SerieDataBase
import com.aband.apart.productions.data.db.SeriesDao
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_serie.*

const val PATH_DETAIL_SERIE = "https://image.tmdb.org/t/p/w500"
class DetailFragment : BaseFragment() {

    lateinit var seriesRepository: SeriesRepository
    lateinit var detailViewModel: DetailViewModel
    var serieId : String = ""

    override fun onFinishedViewLoad() {
        serieId = arguments!!.getString("serieId", serieId)
        initializeUi()
    }

    private fun initializeUi() {
        seriesDao =serieDao(serieDataBase)
        seriesRepository = SeriesRepository(retrofit, seriesDao)
        detailViewModel = DetailViewModel(seriesRepository)
        detailViewModel.getSeriesDetails(serieId)

        detailViewModel.liveDataDetail.observe(this, viewDetails)

    }

    fun initViewDetailSerie(serieLocal: SerieLocal){
        titleDetail.text = serieLocal.originalName
        overrideDetail.text = serieLocal.overview
        Picasso.get()
            .load(PATH_DETAIL_SERIE + serieLocal.imageserie)
            .resize(600, 800)
            .centerCrop()
            .into(imageDetailSerie)
    }

    var viewDetails = Observer<SerieLocal> { seriesLocal ->
        initViewDetailSerie(seriesLocal)
    }

    override fun fragmentLayout(): Int = R.layout.fragment_detail_serie

    fun serieDao(db: SerieDataBase): SeriesDao = db.movieDao()
}