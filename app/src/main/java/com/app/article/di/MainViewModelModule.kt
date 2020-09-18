package com.app.article.di

import androidx.lifecycle.ViewModel
import com.app.article.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract  fun bindMainViewModel(viewModel: MainViewModel):ViewModel
}