package com.app.article.di.article

import com.app.article.ui.articles.ArticleAdapter
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides

@Module
class ArticleFragmentModule {
    companion object {
        @ArticleFragmentScope
        @Provides
        fun provideAdapter(glide: RequestManager): ArticleAdapter {
            return ArticleAdapter(glide)
        }

    }

}