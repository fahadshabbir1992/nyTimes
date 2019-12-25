package com.fahad.testassignment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso
import androidx.constraintlayout.widget.ConstraintLayout
import com.fahad.testassignment.MainActivity

import com.fahad.testassignment.R
import com.fahad.testassignment.models.responses.Results
import de.hdodenhof.circleimageview.CircleImageView

class NewsRecyclerViewAdapter(
    val context: Context,
    val data: List<Results>,
    val returnNews:() -> Unit
) :
    RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val NewsData: Results = data.get(position)


        holder.title.text = NewsData.title
        if(!NewsData.byline.isNullOrEmpty()){
        holder.details.text = NewsData.byline
        }
        holder.date.text = NewsData.published_date

        if(!NewsData.media[0]!!.`media-metadata`[0]!!.url.isNullOrEmpty()){
            Picasso.get().load(NewsData.media[0]!!.`media-metadata`[0]!!.url).into(holder.image)
        }


       holder.news.setOnClickListener {
          MainActivity.newsData = NewsData
           returnNews()
        }

    }


    // stores and recycles views as they are scrolled off screen
    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title by lazy { itemView.findViewById<TextView>(R.id.articleTitle) }
        val details by lazy { itemView.findViewById<TextView>(R.id.author) }
        val image by lazy { itemView.findViewById<CircleImageView>(R.id.articleImage) }
        val date by lazy { itemView.findViewById<TextView>(R.id.date) }
      val news by lazy { itemView.findViewById<ConstraintLayout>(R.id.news) }
    }
}