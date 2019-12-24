package com.fahad.testassignment.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.autofill.AutofillValue
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso
import android.widget.RatingBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.fahad.testassignment.MainActivity

import com.fahad.testassignment.R
import com.fahad.testassignment.models.responses.results
import de.hdodenhof.circleimageview.CircleImageView

class NewsRecyclerViewAdapter(
    val context: Context,
    val data: List<results>,
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
        val NewsData: results = data.get(position)


//        if(rspecialityData.specialty_image != null && !rspecialityData.specialty_image.equals("")  ){
//            Picasso.get().load(context.resources.getString(R.string.specialityimagepath)
//                    +rspecialityData.specialty_image).placeholder(context.resources.getDrawable
//                (R.drawable.maledocavatar)).into(holder.specialityImage)
//        }


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

       // val specialityImage by lazy { itemView.findViewById<ImageView>(R.id.speciality) }

    }
}