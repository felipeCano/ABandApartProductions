package com.aband.apart.productions.data.api

import com.google.gson.JsonElement
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


const val API_KEY = "9f20bc853860ba666d76c08056986c59"
const val ACCEPT_TOKEN: String = "Accept: application/json"
const val CONTENT_TYPE: String = "Content-Type: application/json"
interface ApiSeries {

    //TODO: Put strins in constants
    @Headers(ACCEPT_TOKEN, CONTENT_TYPE)
    @GET("popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: String = "1"
    ): Observable<JsonElement>
}