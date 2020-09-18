package com.app.article.di

import android.app.Application
import com.app.article.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface  Builder{
        // if we want to bind particular instance of obj to component at the time of construction
        @BindsInstance
        fun bindApplication (app:Application):Builder
        fun build():AppComponent
    }

}