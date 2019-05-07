package com.aband.apart.productions.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aband.apart.productions.R
import com.aband.apart.productions.control.model.local.SerieLocal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_popular_series.view.*

const val IMAGE_PATH = "https://image.tmdb.org/t/p/w500"
class SeriesPopularAdapter(var myDataset : List<SerieLocal>) :
RecyclerView.Adapter<SeriesPopularAdapter.SerieHolder>(){

    class SerieHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val mtvTitleSeries = itemView.tvTitleSeries
        val mtvOverride = itemView.tvOverride
        val mImageSeries = itemView.imageSeries
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_popular_series, parent, false)
        return SerieHolder(view)
    }

    override fun onBindViewHolder(holder: SerieHolder, position: Int) {
        holder.mtvTitleSeries.text = myDataset[position].originalName
        holder.mtvOverride.text = myDataset[position].overview
        Picasso.get()
            .load(IMAGE_PATH + myDataset[position].imageserie)
            .resize(300, 300)
            .placeholder(R.drawable.got)
            .centerCrop()
            .into(holder.mImageSeries)
    }

    fun loadSerie(data: List<SerieLocal>){
        myDataset = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = myDataset.size
}