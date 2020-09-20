package com.app.article.ui.articles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.article.model.Article
import com.app.article.repo.DataRepository
import com.app.article.utils.DEBUG_TAG
import com.app.article.utils.InternetUtil
import com.app.headlines.network.NetworkResult
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(private val dataSource: DataRepository) : ViewModel() {
    private val _articles = MutableLiveData<NetworkResult<List<Article>>>()
    private val articles: LiveData<NetworkResult<List<Article>>> = _articles
   var currentPage=1
    var list: MutableList<Article> = mutableListOf()

    fun observeNewsData() = articles
    init {
        startJob()

    }


     fun startJob(page:String ="1"): Job {
        _articles.postValue(NetworkResult.InProgress)
        return viewModelScope.launch(Dispatchers.IO) {
            try {
                // Make network request using a blocking call
                    val data=   dataSource.fetchArticles(InternetUtil.isInternetOn(),page=page)
                if(data !=null ){
                       _articles.postValue(NetworkResult.Success(data))
                   }
            } catch (cause: CancellationException) {
                Log.d(DEBUG_TAG, "job got cancelled $cause")
                // called when previous job are cancelled
            } catch (cause: Exception) {
                // If anything throws an exception, inform the caller

                Log.d(DEBUG_TAG, "Something went wrong $cause")
                _articles.postValue(NetworkResult.Error(cause))
            }
        }
    }
}