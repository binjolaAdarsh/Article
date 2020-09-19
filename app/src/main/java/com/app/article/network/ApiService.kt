package com.app.headlines.network

import com.app.headlines.model.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * service class contains  requests and urls for  main customer search screen
 */
interface ApiService {
    /**
     * to get the data of customer address along with location
     * country =>  this is for what to be search for
     * category => this is for category filters
     * api key
     */
    @GET("top-headlines")
    suspend fun getNewsData(
        @Query("country") country: String = "us",
        @Query("category") category: String = "business",
        @Query("apiKey") apiKey: String = "5e1092104c9b4a08959b24c3c271a6ed"
    ): NewsModel?

}