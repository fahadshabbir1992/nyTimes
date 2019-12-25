package com.fahad.testassignment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fahad.testassignment.models.responses.Results

import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_article_details.*
import java.lang.Exception


public class ArticleDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)
        val gson = Gson()
        var json = intent.getStringExtra("data")
try{
        val obj: Results =
            gson.fromJson<Results>(json, Results::class.java)
        Log.e("Data to show", obj.toString())

        title1.text = obj.title
        article.text = obj.abstract
        author.text = obj.byline
        date.text = obj.published_date
        read_btn.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(obj.url))
            startActivity(browserIntent)
        }


        obj.media[0].`media-metadata`
        for (i in obj.media[0].`media-metadata`) {

            Log.e("hello", i.url.toString())

        }
    }catch(e:Exception) {
}

    }
}
