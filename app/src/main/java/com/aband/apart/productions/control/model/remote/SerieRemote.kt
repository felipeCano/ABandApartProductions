package com.aband.apart.productions.control.model.remote

import com.aband.apart.productions.control.model.local.SerieLocal

data class SerieRemote(
    var page: Int,
    var results: List<SerieLocal> = emptyList()
)
