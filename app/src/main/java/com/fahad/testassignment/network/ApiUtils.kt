package com.fahad.testassignment.network


import com.fahad.testassignment.utils.Constants

class ApiUtils {
    companion object {
        fun getAPIService(): ApiInterface {
            return ApiClient.getClient(Constants.BASE_URL).create(ApiInterface::class.java)
        }


    }
}