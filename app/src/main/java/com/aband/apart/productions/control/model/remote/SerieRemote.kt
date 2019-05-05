package com.aband.apart.productions.control.model.remote

import com.aband.apart.productions.control.model.local.SerieLocal
import com.google.gson.annotations.SerializedName

data class SerieRemote(
    var page: Int,
    var results: List<SerieLocal> = emptyList(),
    @SerializedName("total_results")
    var totalResults: Int,
    @SerializedName("total_pages")
    var totalPages: Int
)
