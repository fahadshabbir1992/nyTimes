package com.fahad.testassignment.network

import com.fahad.testassignment.models.responses.GetNytimesResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {




    @GET("all-sections/7.json")
    fun getArticles(
       @Query("api-key") api_key:String
    ): Call<GetNytimesResponse>



}