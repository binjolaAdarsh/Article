package com.app.article.repo

import android.util.Log
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val newsDao: NewsDao
) {




}