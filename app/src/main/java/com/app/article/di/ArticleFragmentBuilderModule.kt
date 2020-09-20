package com.app.article.di

import com.app.article.di.article.ArticleFragmentModule
import com.app.article.di.article.ArticleFragmentScope
import com.app.article.ui.articles.ArticlesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module which creates supComponents for the ArticleFragment
 */
@Module
abstract  class ArticleFragmentBuilderModule{
     @ArticleFragmentScope
     @ContributesAndroidInjector(modules = [ArticleFragmentModule::class])
     abstract  fun  contributeArticleFragment(): ArticlesFragment
}
