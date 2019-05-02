package com.aband.apart.productions.ui.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aband.apart.productions.center.BaseFragment
import com.aband.apart.productions.center.utils.InjectorUtils
import kotlinx.android.synthetic.main.fragment_series.*
import com.aband.apart.productions.R
import com.aband.apart.productions.control.model.remote.SerieRemote

class SeriesFragment : BaseFragment() {

    /*fun newInstance(serieRemote: SerieRemote): SeriesFragment {
        val args = Bundle()
        args.putString(SeriesHelper.KEY_TITLE, serieRemote.status.toString())

        // Create a new MovieFragment and set the Bundle as the arguments
        // to be retrieved and displayed when the view is created
        val fragment = SeriesFragment()
        fragment.arguments = args
        return fragment
    }*/

    override fun onFinishedViewLoad() {
        initializeUi()
    }

    private fun initializeUi() {
        val factory = InjectorUtils.provideSeriesViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
            .get(SeriesViewModel::class.java)


        /*viewModel.getSeries().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            textView_quotes.text = stringBuilder.toString()
            //title
        })*/

        button_add_quote.setOnClickListener {
            val quote = SerieRemote(editText_quote.text.toString())
          //  viewModel.addQuote(quote)
            editText_quote.setText("")
        }
    }



    override fun fragmentLayout(): Int = R.layout.fragment_series

}