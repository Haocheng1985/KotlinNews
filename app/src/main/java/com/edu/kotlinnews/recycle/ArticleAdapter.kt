package com.edu.kotlinnews.recycle

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edu.kotlinnews.DetailsActivity
import com.edu.kotlinnews.model.ListItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_article.view.*
import com.edu.kotlinnews.R
import com.google.gson.Gson

class ArticleAdapter:RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    var data:MutableList<ListItem> = ArrayList()//get data from MainActivity
    set(value) {
        data.clear()
        data.addAll(value)
        notifyDataSetChanged()
    }

    class ArticleViewHolder:RecyclerView.ViewHolder{
        lateinit var data:ListItem
        var title:TextView
        var thumbnail:ImageView

        constructor(itemView: View) : super(itemView){
            title=itemView.title_tv
            thumbnail=itemView.thumbnail_iv
            itemView.setOnClickListener{
                Log.e("Adapter","Item clicked")

                val intent=Intent(it.context,DetailsActivity::class.java)
                intent.putExtra("data",Gson().toJson(data.data))//serialize obj to json
                it.context.startActivity(intent)


            }
        }

        fun bind(data: ListItem){
            this.data=data
            title.setText(data.data.title)
            var img=data.data.thumbnail
            //the ImageView will show depend on if there is thumbnail image
            if (img == null || img.trim().isEmpty()) {

                thumbnail.visibility = View.GONE
            } else {
                thumbnail.visibility = View.VISIBLE
                Picasso.get().load(img.trim()).into(thumbnail)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article,//render item_article
                parent,
                false
            )
        )

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(data.get(position))
    }
}