package com.aband.apart.productions.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aband.apart.productions.R
import com.aband.apart.productions.control.model.local.SerieLocal
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_on_tv_series.view.*

const val PATH_ON_TV = "https://image.tmdb.org/t/p/w500"
class SeriesOnTvAdapter(var myDataset : List<SerieLocal>) :
    RecyclerView.Adapter<SeriesOnTvAdapter.SerieHolder>(){

    class SerieHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val mtvTitleSeriesOnTv = itemView.tvTitleSeriesOnTv
        val mtvOverrideOnTv = itemView.tvOverrideOnTv
        val mImageSeriesOnTv = itemView.imageSeriesOnTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_on_tv_series, parent, false)
        return SerieHolder(view)
    }

    override fun onBindViewHolder(holder: SerieHolder, position: Int) {
        holder.mtvTitleSeriesOnTv.text = myDataset[position].originalName
        holder.mtvOverrideOnTv.text = myDataset[position].overview
        Picasso.get()
            .load(PATH_ON_TV + myDataset[position].imageserie)
            .resize(300, 300)
            .placeholder(R.drawable.got)
            .centerCrop()
            .into(holder.mImageSeriesOnTv)
    }
    override fun getItemCount(): Int = myDataset.size
}