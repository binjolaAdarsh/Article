package com.app.article.repo


import com.app.headlines.network.ArticleService

import javax.inject.Inject

/**
 * class only responsible for the  getting  or posting data to the network
 * @param apiService =>  all api calls
 */
class NetworkDataSource @Inject constructor(private val apiService: ArticleService){
            suspend fun getArticles(page:String) =
             apiService.getArticleData(page=page)

}
