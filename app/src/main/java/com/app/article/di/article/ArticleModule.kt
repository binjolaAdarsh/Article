package com.app.article.di.article

import com.app.headlines.network.ArticleApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

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