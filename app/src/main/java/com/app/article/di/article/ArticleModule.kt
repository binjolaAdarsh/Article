package com.app.article.di.article

import com.app.headlines.network.ArticleService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ArticleModule {
    companion object{
@ArticleScope
        @Provides
        fun provideArticleApi(retrofit: Retrofit): ArticleService {
            return  retrofit.create(ArticleService::class.java)
        }
       /* @ArticleService
        @Provides
        fun provideAdapter(): PostRecyclerAdapter {
            return  PostRecyclerAdapter()
        }*/
    }

}