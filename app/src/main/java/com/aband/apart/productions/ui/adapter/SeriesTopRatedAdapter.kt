package com.aband.apart.productions.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aband.apart.productions.R
import com.aband.apart.productions.control.model.local.SerieLocal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_top_rated_series.view.*

const val PATH_TOP_RATED = "https://image.tmdb.org/t/p/w500"
class SeriesTopRatedAdapter(var myDataset : List<SerieLocal>) :
    RecyclerView.Adapter<SeriesTopRatedAdapter.SerieHolder>(){

    class SerieHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val mtvTitleSeriesTopRated = itemView.tvTitleSeriesTopRated
        val mtvOverrideTopRated = itemView.tvOverrideTopRated
        val mImageSeriesTopRated = itemView.imageSeriesTopRated
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_top_rated_series, parent, false)
        return SerieHolder(view)
    }

    override fun onBindViewHolder(holder: SerieHolder, position: Int) {
        holder.mtvTitleSeriesTopRated.text = myDataset[position].originalName
        holder.mtvOverrideTopRated.text = myDataset[position].overview
        Picasso.get()
            .load(PATH_TOP_RATED + myDataset[position].imageserie)
            .resize(300, 300)
            .placeholder(R.drawable.got)
            .centerCrop()
            .into(holder.mImageSeriesTopRated)
    }
    override fun getItemCount(): Int = myDataset.size
}