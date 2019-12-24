package com.fahad.testassignment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahad.testassignment.adapters.NewsRecyclerViewAdapter
import com.fahad.testassignment.models.responses.GetNytimesResponse
import com.fahad.testassignment.models.responses.results
import com.fahad.testassignment.network.ApiUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        articles_recycler_view.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        getArticles()
    }

    companion object {

        var newsData: results? = null

    }

    var NewsRecyclerViewAdapter: NewsRecyclerViewAdapter? = null
    var api_key="Vyx9V1dgJQjYcJQ7Hx3o9DkV18plTOez"
    fun getArticles() {
        ApiUtils.getAPIService().getArticles()
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

                                        //   Log.e("log data",NewsData!!.option_id.toString())
                                        //     getDoctorsList( specialityData!!.option_id, "", "")
                                        //  findDoctors(specialityData!!.option_id, "", "")


                                        var  mPrefs:SharedPreferences = getPreferences(MODE_PRIVATE)



//set variables of 'myObject', etc.

                                       var  prefsEditor: SharedPreferences.Editor = mPrefs.edit()

                                        val gson = Gson()
                                        val json = gson.toJson(newsData)
                                        Log.e("testing value",json)
                                        prefsEditor.putString("NewsArticle", json)
                                        prefsEditor.commit()




                                    //    SharedPrefs.setArticleData(this@MainActivity, newsData)
                                    val intent = Intent(this@MainActivity, ArticleDetailsActivity::class.java)
//Log.e("Data send", newsData.toString())
                      //  var r:results? = newsData.toString()

                                  intent.putExtra("data", json)
                                    startActivity(intent)

                                    })

                            articles_recycler_view.adapter = NewsRecyclerViewAdapter



//                         specialityList.clear()
//                         specialityMap.clear()
//
//                      //   specialityList.add("Speciality")
//                         for (facility in response.body()!!.data) {
//                             specialityList.add(facility.title)
//                             specialityMap.put(facility.title, facility.option_id)
//                         }
//
//                         specialityAdapter = ArrayAdapter(this@FindDoctorActivity, android.R.layout.simple_spinner_item, specialityList)
//                         specialityAdapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                         speciality_spinner.adapter = specialityAdapter


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
