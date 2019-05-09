package com.aband.apart.productions.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aband.apart.productions.R
import com.aband.apart.productions.control.model.local.SerieLocal
import com.aband.apart.productions.ui.interfaces.DetailSeriesInterface
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_popular_series.view.*
import android.view.animation.Animation
import android.view.animation.ScaleAnimation

import java.util.*


const val PATH = "https://image.tmdb.org/t/p/w500"
class SeriesPopularAdapter(var myDataset : List<SerieLocal>) :
RecyclerView.Adapter<SeriesPopularAdapter.SerieHolder>(){

     var detailSeries : DetailSeriesInterface? = null

    inner class SerieHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        //    val mtvTitleSeries = itemView.tvTitleSeries
        //val mtvOverride = itemView.tvOverride
        val mImageSeries = itemView.imageSeries

        init {
            mImageSeries.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            val i = v!!.id
            if (i == R.id.imageSeries){
                detailSeries!!.onDetailSeries(myDataset[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_popular_series, parent, false)
        return SerieHolder(view)
    }

    override fun onBindViewHolder(holder: SerieHolder, position: Int) {
      //  holder.mtvTitleSeries.text = myDataset[position].originalName
        //holder.mtvOverride.text = myDataset[position].overview
        Picasso.get()
            .load(PATH + myDataset[position].imageserie)
            .resize(600, 950)
            //.placeholder(R.drawable.got)
            .centerCrop()
            .into(holder.mImageSeries)

        setAnimation(holder.mImageSeries, position)
    }

    override fun getItemCount(): Int = myDataset.size

    fun onDetailsSeries(detailSeriesInterface: DetailSeriesInterface){
        this.detailSeries = detailSeriesInterface
    }

    private var lastPosition = -1

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val anim = ScaleAnimation(
                0.0f,
                1.0f,
                0.0f,
                1.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            anim.duration = Random().nextInt(701).toLong()//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim)
            lastPosition = position
        }
    }
}