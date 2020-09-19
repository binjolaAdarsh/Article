package com.app.article.ui.articles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.article.model.ArticleModel
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
    private val _articles = MutableLiveData<NetworkResult<List<ArticleModel>>>()
    private val articles: LiveData<NetworkResult<List<ArticleModel>>> = _articles
    fun observeNewsData() = articles
    private lateinit var previousJob: Job

    init {
        getData()

    }

    fun getData(page:String="1") {
        _articles.postValue(NetworkResult.InProgress)
        Log.d(DEBUG_TAG, "onloading starts")
        previousJob = if (!this::previousJob.isInitialized) {

            startJob(page)

        } else {
            if (previousJob.isActive) {
                Log.d(DEBUG_TAG, "cancelling previous jobs")
                previousJob.cancel()
            }
            startJob(page)
        }

    }

    private fun startJob(page:String ="1"): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            try {
                // Make network request using a blocking call
                    val data=   dataSource.fetchArticles(InternetUtil.isInternetOn(),page=page)
                if(data !=null && data.isNotEmpty()){
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