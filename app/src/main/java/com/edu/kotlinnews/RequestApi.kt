package com.edu.kotlinnews

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

/**
 * this is an interface,
 * use the @GET method to get api.
 *
 *
 */


interface RequestApi {

    @GET("r/kotlin/.json") //get REST api
    fun fetchData():Call<JsonObject>

}