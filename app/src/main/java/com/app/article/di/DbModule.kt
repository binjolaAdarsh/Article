package com.app.article.di

import android.app.Application
import androidx.room.Room
import com.app.article.db.ArticleDao
import com.app.article.db.ArticleDb
import com.app.article.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module to provide dependencies for the database
 */
@Module
object DbModule {


    @Provides
    @Singleton
    fun provideDb(app: Application): ArticleDb {
        return Room.databaseBuilder(app, ArticleDb::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideArticleDao(db: ArticleDb): ArticleDao {
        return db.articleDao()
    }
}
