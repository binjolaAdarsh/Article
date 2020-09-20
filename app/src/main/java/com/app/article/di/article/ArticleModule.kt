package com.app.article.di.article

import com.app.headlines.network.ArticleApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * this will provide  depedencies for the ArticleActivity  within the scope of itself
 */
@Module
class ArticleModule {
    companion object{
@ArticleScope
        @Provides
        fun provideArticleApi(retrofit: Retrofit): ArticleApi {
            return  retrofit.create(ArticleApi::class.java)
        }

    }

}