package com.app.article.di

import com.app.article.ui.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract  class MainFragmentBuilderModule{
     @ContributesAndroidInjector
     abstract  fun  contributeMainFragment(): MainFragment
}
