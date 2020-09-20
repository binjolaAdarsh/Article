package com.app.article.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * App level module  providing dependencies for the app level
 */
@Module
object AppModule {
    @Provides
    @Singleton
    fun getGlide(application: Application): RequestManager {
        return Glide.with(application)
    }

}