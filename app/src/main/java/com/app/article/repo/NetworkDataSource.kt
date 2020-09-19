package com.app.article.repo


import com.app.headlines.network.ApiService

import javax.inject.Inject

/**
 * class only responsible for the  getting  or posting data to the network
 * @param apiService =>  all api calls
 */
class NetworkDataSource @Inject constructor(private val apiService: ApiService){
            suspend fun getNewsData() =
             apiService.getNewsData()

}
