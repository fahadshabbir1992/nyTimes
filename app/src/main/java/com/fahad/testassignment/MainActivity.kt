package com.fahad.testassignment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fahad.testassignment.adapters.NewsRecyclerViewAdapter
import com.fahad.testassignment.databinding.ActivityMainBinding
import com.fahad.testassignment.models.responses.GetNytimesResponse
import com.fahad.testassignment.models.responses.Results
import com.fahad.testassignment.network.ApiUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response


public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val activityMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)








        // bind RecyclerView
        val recyclerView: RecyclerView = activityMainBinding.articlesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recyclerView.setHasFixedSize(true)


        getArticles()
    }

    companion object {

        var newsData: Results? = null

    }

    var NewsRecyclerViewAdapter: NewsRecyclerViewAdapter? = null

  //  var api_key=getString(R.string.api_key)

    fun getArticles() {

        ApiUtils.getAPIService().getArticles(getString(R.string.key))
            .enqueue(object : retrofit2.Callback<GetNytimesResponse> {
                override fun onFailure(call: Call<GetNytimesResponse>, t: Throwable) {
                    //  progress.visibility = View.GONE
                    articlesText.visibility= View.VISIBLE
                    Log.e("Issue is=",t.toString())
                }

                override fun onResponse(call: Call<GetNytimesResponse>, response: Response<GetNytimesResponse>) {

                    try {
                        Log.e("News","List= "+response!!.body()!!.status.toString())
                        if (response.isSuccessful && response.body()!!.status.equals("OK")) {

                            // progress.visibility = View.GONE
                            NewsRecyclerViewAdapter =
                                NewsRecyclerViewAdapter(
                                    this@MainActivity,
                                    response.body()!!.results!!,
                                    returnNews = {



                                        var  mPrefs:SharedPreferences = getPreferences(MODE_PRIVATE)



                                       var  prefsEditor: SharedPreferences.Editor = mPrefs.edit()

                                        val gson = Gson()
                                        val json = gson.toJson(newsData)
                                        Log.e("testing value",json)
                                        prefsEditor.putString("NewsArticle", json)
                                        prefsEditor.commit()



                                    val intent = Intent(this@MainActivity, ArticleDetailsActivity::class.java)


                                  intent.putExtra("data", json)
                                    startActivity(intent)

                                    })

                            articles_recycler_view.adapter = NewsRecyclerViewAdapter




                        } else {
                            articlesText.visibility= View.VISIBLE

                        }
                    }catch ( e: Exception){
                        articlesText.visibility= View.VISIBLE
                    }
                }
            })


    }


}
