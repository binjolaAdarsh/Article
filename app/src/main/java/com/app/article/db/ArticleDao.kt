package com.app.article.db

import androidx.room.*
import com.app.article.model.ArticleModel

@Dao
interface ArticleDao{
    @Transaction
    suspend fun updateData(articles: List<ArticleModel>?) {
     //deleteAllUsers()
        insertAll(articles)
    }

    @Query("DELETE FROM ArticleTable")
     suspend fun deleteAllUsers()
    @Insert(onConflict = OnConflictStrategy.REPLACE)
      suspend fun insertAll(pro: List<ArticleModel>?)

    @Query("SELECT * FROM ArticleTable  ORDER by localId  LIMIT :limit OFFSET :offset")
     suspend    fun getArticles(limit:Int,offset:Int): List<ArticleModel>?
}