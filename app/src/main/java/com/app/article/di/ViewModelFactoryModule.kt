package com.app.article.di

import androidx.lifecycle.ViewModelProvider
import com.app.article.utils.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

/**
 * module to provide the viewModel factory
 */
@Module
abstract class ViewModelFactoryModule {
    // providing the instance of viewModelProviderFactory
    @Binds
    abstract fun bindViewModelFactory (modelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}