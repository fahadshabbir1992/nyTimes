package com.fahad.testassignment.network

import com.fahad.testassignment.models.responses.GetNytimesResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @GET("all-sections/7.json?api-key=Vyx9V1dgJQjYcJQ7Hx3o9DkV18plTOez")
    fun getArticles(
    ): Call<GetNytimesResponse>



}