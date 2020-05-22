package com.edu.kotlinnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.text.HtmlCompat
import com.edu.kotlinnews.model.Data
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.item_article.thumbnail_iv
import org.apache.commons.text.StringEscapeUtils

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.displayOptions=ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.bar_layout)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)//set back button
        val data =
            Gson().fromJson(intent.getStringExtra("data"), Data::class.java)//serialize json to obj
        title = data.title//set title

        val dispTitle = supportActionBar!!.customView!!.findViewById<TextView>(R.id.bar_title)!!
        dispTitle.text = StringEscapeUtils.unescapeHtml4(data.title)
        dispTitle.requestFocus()
        dispTitle.isSelected = true

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
//            Log.e(DetailsActivity::class.java.name,StringEscapeUtils.unescapeHtml4(data.selftext_html))
            content_tv.text =  HtmlCompat.fromHtml(StringEscapeUtils.unescapeHtml4(data.selftext_html),0)
//            content_tv.text = data.selftext
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
