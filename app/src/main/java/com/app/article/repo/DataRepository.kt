package com.app.article.repo

import android.util.Log
import com.app.article.db.ArticleDao
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val articleDao: ArticleDao
) {
    suspend fun  getArticles(){}
    suspend fun fetchArticles(page:String="1"){
      val articlesList =  networkDataSource.getArticles(page)

    }
}