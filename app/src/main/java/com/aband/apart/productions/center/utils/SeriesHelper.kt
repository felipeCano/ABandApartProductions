package com.aband.apart.productions.center.utils

import android.content.Context
import com.aband.apart.productions.control.model.local.SerieLocal
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

object SeriesHelper {
    val KEY_TITLE = "title"

    fun getSeriesFromJson(serieName : String , context: Context) : ArrayList<SerieLocal>{
         val series = ArrayList<SerieLocal>()

        try {
            // Load the JSONArray from the file
            val jsonString = loadJsonFromFile(serieName, context)
            val json = JSONObject(jsonString)
            val jsonMovies = json.getJSONArray("movies")

            // Create the list of Movies
            for (index in 0 until jsonMovies.length()) {
                val movieTitle = jsonMovies.getJSONObject(index).getString(KEY_TITLE)
                series.add(SerieLocal(movieTitle))
            }
        } catch (e: JSONException) {
            return series
        }

        return series
    }

    private fun loadJsonFromFile(filename: String, context: Context): String {
        var json = ""

        try {
            val input = context.assets.open(filename)
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            json = buffer.toString(Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return json
    }
}