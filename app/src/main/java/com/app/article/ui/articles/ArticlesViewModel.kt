package com.app.article.ui.articles

import androidx.lifecycle.ViewModel
import com.app.article.repo.DataRepository
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(private val dataSource: DataRepository) : ViewModel() {

}