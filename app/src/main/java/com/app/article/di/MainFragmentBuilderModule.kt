package com.app.article.di

import com.app.article.ui.articles.ArticlesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract  class MainFragmentBuilderModule{
     @ContributesAndroidInjector
     abstract  fun  contributeMainFragment(): ArticlesFragment
}
