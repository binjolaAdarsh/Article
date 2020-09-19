package com.app.article

import com.app.article.di.DaggerAppComponent
import com.app.article.utils.InternetUtil
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication: DaggerApplication(){
    override fun onCreate() {
        super.onCreate()
        InternetUtil.init(this)
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().bindApplication(this).build()
    }
}