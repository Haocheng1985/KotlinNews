package com.edu.kotlinnews

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import  retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Main application
 *
 *
 *
 * */

class MainApp:Application() {

    companion object{
        lateinit var api: RequestApi
    }

    override fun onCreate() {
        super.onCreate()

        var ret=Retrofit.Builder().baseUrl(
            "https://www.fastmock.site/"
//            "https://www.reddit.com/"
        ).addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api=ret.create(RequestApi::class.java)

    }
}