package com.app.article.repo

import android.util.Log
import com.app.article.db.ArticleDao
import com.app.article.model.Article
import com.app.article.model.ArticleModel
import com.app.article.model.Media
import com.app.article.utils.DEBUG_TAG
import com.app.article.utils.LIMIT
import com.app.article.utils.dategetTimeAgo
import com.app.article.utils.numberCalculation
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val articleDao: ArticleDao
) {
    suspend fun getArticles(page: String): List<Article>? {
        return articleDao.getArticles(limit= LIMIT, offset = ((LIMIT * page.toInt()) - LIMIT))
    }

    suspend fun getArticlesFromWeb(page: String = "1"): List<ArticleModel>? {
        return networkDataSource.getArticles(page)
    }

    suspend fun fetchArticles(fromWeb:Boolean,page: String = "1"): List<Article>? {

      if(fromWeb){
          val articlesList = networkDataSource.getArticles(page)
          articlesList?.let {articleModel ->
             var articleList=   articleModel.map{
                    val user=it.user?.get(0)
                val isMediaPresent=it.media?.size!! > 0
                 var media: Media?=null
                 if(isMediaPresent ){
                     media = it.media[0]
                 }

                          Article(it.id.toInt(), user?.avatar,
                              userName = "${user?.name} ${user?.lastName}",
                              userDesignation = user?.designation,
                          articleTime = dategetTimeAgo(it.createdAt),
                              articleImageUrl = if(isMediaPresent) media?.image else "",
                              articleContent = it.content,
                              articleTitle =if(isMediaPresent) media?.title else "" ,
                              articleUrl = if(isMediaPresent) media?.url else ""   ,
                              articleLikes = "${numberCalculation(it.likes)} Likes",
                              articleComments = "${numberCalculation(it.comments)} Comments"
                              )
                }

Log.d(DEBUG_TAG,articleList.toString())
              articleDao.updateData(articleList)
          }
      }
        return getArticles(page)


    }

}