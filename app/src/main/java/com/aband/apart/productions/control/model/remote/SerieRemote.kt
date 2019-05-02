package com.aband.apart.productions.control.model.remote

data class SerieRemote(
    var status: String
){
    override fun toString(): String {
        return "$status"
    }
}
