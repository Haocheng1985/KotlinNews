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
//    @GET("mock/4e55609daf3395eea2df809b041d9164/api/reddit")
    fun fetchData():Call<JsonObject>

}