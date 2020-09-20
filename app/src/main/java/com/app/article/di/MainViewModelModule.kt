package com.app.article.di

import androidx.lifecycle.ViewModel
import com.app.article.ui.articles.ArticlesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * for the viewModel
 */
@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    abstract  fun bindMainViewModel(viewModel: ArticlesViewModel):ViewModel
}