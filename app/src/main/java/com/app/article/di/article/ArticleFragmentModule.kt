package com.app.article.di.article

import com.app.article.ui.articles.ArticleAdapter
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides

/**
 * this provide dependencies for the ArticleFragment  being in the scope of it only
 */
@Module
class ArticleFragmentModule {
    companion object {

        // provide adapter for the list of articles
        @ArticleFragmentScope
        @Provides
        fun provideAdapter(glide: RequestManager): ArticleAdapter {
            return ArticleAdapter(glide)
        }

    }

}