package com.aband.apart.productions.control.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "serie")
data class SerieLocal(
    @PrimaryKey
    val id: Int,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("poster_path")
    val imageserie: String,
    val overview: String

)