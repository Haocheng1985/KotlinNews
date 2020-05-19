package com.edu.kotlinnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.LinearLayoutManager
import com.edu.kotlinnews.model.ListItem
import com.edu.kotlinnews.recycle.ArticleAdapter
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Kotlin News"//set title
        article_rv.layoutManager = LinearLayoutManager(this)//set main page
        articleAdapter = ArticleAdapter()
        article_rv.adapter = articleAdapter
    }

    override fun onResume() {
        super.onResume()
        MainApp.api.fetchData().enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Fail to load data.", Toast.LENGTH_SHORT)
                    .show()//Toast if connect error
                Log.e(MainActivity::class.java.name, "Error load json", t)
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data == null) {
                        onFailure(call, RuntimeException("Empty result."))
                        return
                    }
                    var realData: JsonElement = data.get("data");//get data from ListItem
                    realData = realData.asJsonObject.get("children")//get children-data from data

                    var list = Gson().fromJson<MutableList<ListItem>>(realData,
                        object : TypeToken<MutableList<ListItem>>() {}.type
                    )//json to obj

                    articleAdapter.data = list//pass data to recycleview

                } else {
                    onFailure(call, RuntimeException("No response"))
                }
            }
        })

    }
}
