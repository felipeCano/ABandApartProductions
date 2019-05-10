package com.aband.apart.productions.center

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.data.api.ApiSeries
import com.aband.apart.productions.data.db.SerieDataBase
import com.aband.apart.productions.data.db.SeriesDao
import com.aband.apart.productions.util.Util
import com.google.gson.Gson
import okhttp3.OkHttpClient

abstract class BaseFragment : Fragment() {

    protected lateinit var okHttpClient: OkHttpClient
    protected lateinit var retrofit: ApiSeries
    protected lateinit var gosn: Gson
    protected lateinit var seriesDao: SeriesDao
    protected lateinit var serieDataBase: SerieDataBase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(fragmentLayout(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gosn = Util.gsonProvider()
        okHttpClient = Util.okHttpClientProvider()
        retrofit = Util.retrofitProvider(okHttpClient, gosn)
        serieDataBase = Util.dataBaseProvider(context!!)
        onFinishedViewLoad()
    }

    @LayoutRes
    abstract fun fragmentLayout(): Int

    abstract fun onFinishedViewLoad()

    protected fun navController(): NavController? {
        return view?.let { Navigation.findNavController(it) }
    }
}