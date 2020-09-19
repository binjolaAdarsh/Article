package com.app.article.di

import com.app.article.ui.articles.ArticlesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract  class ArticleFragmentBuilderModule{
     @ContributesAndroidInjector
     abstract  fun  contributeArticleFragment(): ArticlesFragment
}
