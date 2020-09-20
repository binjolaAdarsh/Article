package com.app.article.repo

import android.util.Log
import com.app.article.db.ArticleDao
import com.app.article.model.Article
import com.app.article.model.ArticleModel
import com.app.article.model.Media
import com.app.article.utils.DEBUG_TAG
import com.app.article.utils.LIMIT
import com.app.article.utils.dateGetTimeAgo
import com.app.article.utils.numberCalculation
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val articleDao: ArticleDao
) {
    /**
     * getting the data from the db table in pagination way
     */
    private suspend fun getArticles(page: String)=
         articleDao.getArticles(limit= LIMIT, offset = ((LIMIT * page.toInt()) - LIMIT))



    suspend fun fetchArticles(fromWeb:Boolean,page: String = "1"): List<Article>? {
        // if  its online then get data from server and update the database table
      if(fromWeb){
          val articlesList = networkDataSource.getArticles(page)
          articlesList?.let {articleModel ->
             var articleList=   articleModel.map{
                  Article.create(it)
                }

Log.d(DEBUG_TAG,articleList.toString())

              articleDao.updateData(articleList)
          }
      }
        // always getting data from the database table
        return getArticles(page)


    }

}