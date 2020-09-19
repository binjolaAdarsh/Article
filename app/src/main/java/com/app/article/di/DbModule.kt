package com.app.article.di

import android.app.Application
import androidx.room.Room
import com.app.article.db.ArticleDao
import com.app.article.db.ArticleDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DbModule {


    @Provides
    @Singleton
    fun provideDb(app: Application): ArticleDb {
        return Room.databaseBuilder(app, ArticleDb::class.java, "article.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideArticleDao(db: ArticleDb): ArticleDao {
        return db.articleDao()
    }
}
