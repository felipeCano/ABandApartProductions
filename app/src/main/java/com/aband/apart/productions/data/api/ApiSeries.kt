package com.aband.apart.productions.data.api

import com.google.gson.JsonElement
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


const val API_KEY = "9f20bc853860ba666d76c08056986c59"
const val ACCEPT_TOKEN: String = "Accept: application/json"
const val CONTENT_TYPE: String = "Content-Type: application/json"

interface ApiSeries {

    @Headers(ACCEPT_TOKEN, CONTENT_TYPE)
    @GET("popular")
    fun getPopularSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1"
    ): Observable<JsonElement>

    @Headers(ACCEPT_TOKEN, CONTENT_TYPE)
    @GET("top_rated")
    fun getTopRatedSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1"
    ): Observable<JsonElement>

    @Headers(ACCEPT_TOKEN, CONTENT_TYPE)
    @GET("on_the_air")
    fun getOnTvSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1"
    ): Observable<JsonElement>

    @Headers(ACCEPT_TOKEN, CONTENT_TYPE)
    @GET("{tv_id}")
    fun getDetailSerie(
        @Path("tv_id") serieId: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): Observable<JsonElement>
}