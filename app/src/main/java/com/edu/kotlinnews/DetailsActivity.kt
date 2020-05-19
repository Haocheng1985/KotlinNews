package com.edu.kotlinnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.edu.kotlinnews.model.Data
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_article.thumbnail_iv

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)//set back button
        val data =
            Gson().fromJson(intent.getStringExtra("data"), Data::class.java)//serialize json to obj
        title = data.title//set title


        var img = data.thumbnail
        var selftext = data.selftext
        //the ImageView will show depend on if there is thumbnail image
        if (img == null || img.trim().isEmpty()) {
            thumbnail_iv.visibility = View.GONE
        } else {
            thumbnail_iv.visibility = View.VISIBLE
            Picasso.get().load(img.trim()).into(thumbnail_iv)

        }
        //if a news has no selftext, just display the url
        if (selftext == null || selftext.trim().isEmpty()) {
            content_tv.text = data.url
            Toast.makeText(this, "No selftext, showing the URL instead", Toast.LENGTH_SHORT).show()
        } else {
            content_tv.text = data.selftext
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {//set click func on back button
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return true
    }
}
