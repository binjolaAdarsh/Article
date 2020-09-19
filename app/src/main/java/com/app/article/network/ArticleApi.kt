package com.app.headlines.network

import com.app.article.model.ArticleModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * service class contains  requests and urls for  main Article screen
 */
interface ArticleApi {
    /**
     * to get the data of Articles
     * page =>  page to request
     * limit => limit per page request
     */
    @GET("blogs")
    suspend fun getArticleData(
        @Query("page") page: String = "1",
        @Query("limit") limit: String = "10"
    ): List<ArticleModel>?

}