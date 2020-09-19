package com.app.article.di

import com.app.article.ui.ArticlesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

// these class should be abstract
// this contain the subComponents
@Module
abstract class ActivityBuilderModule {

    // here we write down all potential client of dagger
    @ContributesAndroidInjector(modules = [MainFragmentBuilderModule::class,MainViewModelModule::class])
    abstract  fun contributeMainActivity():ArticlesActivity
}