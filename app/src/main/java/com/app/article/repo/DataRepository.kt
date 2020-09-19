package com.app.article.repo

import com.app.article.db.ArticleDao
import com.app.article.model.ArticleModel
import com.app.article.utils.LIMIT
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val articleDao: ArticleDao
) {
    suspend fun getArticles(page: String): List<ArticleModel>? {
        return articleDao.getArticles(limit= LIMIT, offset = ((LIMIT * page.toInt()) - LIMIT))
    }

    suspend fun getArticlesFromWeb(page: String = "1"): List<ArticleModel>? {
        return networkDataSource.getArticles(page)
    }

    suspend fun fetchArticles(fromWeb:Boolean,page: String = "1"): List<ArticleModel>? {

      if(fromWeb){
          val articlesList = networkDataSource.getArticles(page)
          articlesList?.let {
              it.forEach {  model->
                  model.localId=model.id.toInt()
              }
              articleDao.updateData(it)
          }
      }
        return getArticles(page)


    }
    suspend fun updateData(articles: List<ArticleModel>?){
        articleDao.updateData(articles)
    }
}