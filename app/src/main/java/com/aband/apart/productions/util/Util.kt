package com.aband.apart.productions.util

import android.content.Context
import androidx.room.Room
import com.aband.apart.productions.data.api.ApiSeries
import com.aband.apart.productions.data.db.SerieDataBase
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val DATABASE_NAME ="abandapart_series.db"
object Util {


    fun gsonProvider() : Gson{
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setLenient().create()
    }

    fun okHttpClientProvider() : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    fun retrofitProvider(okHttpClient : OkHttpClient, gson : Gson) : ApiSeries{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/tv/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiSeries::class.java)
    }

    fun dataBaseProvider(context : Context) : SerieDataBase{
        return Room.databaseBuilder(context, SerieDataBase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}