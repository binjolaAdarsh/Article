package com.app.article.di

import android.app.Application
import com.app.article.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * this AppComponent is bind to the application level which means
 * its app level scoped so modules providing dependencies Scoped with @Singleton will exist till the
 * app exits
 */
@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityBuilderModule::class,
    AppModule::class,
    NetworkModule::class, DbModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        // if we want to bind particular instance of obj to component at the time of construction
        @BindsInstance
        fun bindApplication(app: Application): Builder

        fun build(): AppComponent
    }

}