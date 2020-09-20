package com.app.article.di

import com.app.article.di.article.ArticleModule
import com.app.article.di.article.ArticleScope
import com.app.article.ui.ArticlesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * these class should be abstract
 * this contain the subComponents(Activities and its fragments
 *
 */
@Module
abstract class ActivityBuilderModule {

    // here we write down all potential client of dagger
    @ArticleScope
    @ContributesAndroidInjector(modules = [ArticleFragmentBuilderModule::class,MainViewModelModule::class,
        ArticleModule::class])
    abstract  fun contributeArticleActivity():ArticlesActivity
}