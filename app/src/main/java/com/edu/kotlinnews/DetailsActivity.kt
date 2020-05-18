package com.edu.kotlinnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.edu.kotlinnews.model.Data
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_article.thumbnail_iv

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val data=Gson().fromJson(intent.getStringExtra("data"), Data::class.java)
        title=data.title

        var img=data.thumbnail

        if(img==null||img.trim().isEmpty()){
            thumbnail_iv.visibility= View.GONE
        }else{
            thumbnail_iv.visibility=View.VISIBLE
            Picasso.get().load(img.trim()).into(thumbnail_iv)

        }
        //todo fix if there is no selftext
        content_tv.text=data.selftext

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                finish()
            }
        }
        return true
    }
}
