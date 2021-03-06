package com.app.article.db

import androidx.room.*
import com.app.article.model.Article

/**
 * Dao for the article entity class
 */
@Dao
interface ArticleDao{
    @Transaction
    suspend fun updateData(articles: List<Article>?) {
        insertAll(articles)
    }

    @Query("DELETE FROM ArticleTable")
     suspend fun deleteAllUsers()
    @Insert(onConflict = OnConflictStrategy.REPLACE)
      suspend fun insertAll(pro: List<Article>?)

    @Query("SELECT * FROM ArticleTable  ORDER by articleId  LIMIT :limit OFFSET :offset")
     suspend    fun getArticles(limit:Int,offset:Int): List<Article>?
}