package com.fahad.testassignment

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fahad.testassignment.models.responses.results
import com.fahad.testassignment.utils.SharedPrefs
import com.google.gson.Gson
import org.json.JSONObject

class ArticleDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

  //    var  articleData: results? = SharedPrefs.getArticleData(this@ArticleDetailsActivity)

        var  mPrefs: SharedPreferences = getPreferences(MODE_PRIVATE)

        val gson = Gson()
        //val json = mPrefs.getString("NewsArticle", "")
        var json =intent.getStringExtra("data")




       val obj:results =
           gson.fromJson<results>(json, results::class.java)
        Log.e("Data to show", obj.toString())

        obj.title
        obj.abstract
        obj.byline
        obj.published_date
        obj.url

         obj.media[0].`media-metadata`
        for (i in  obj.media[0].`media-metadata`){

            Log.e("hello",i.url.toString())

        }
    }
}
