package com.gnova.data.api

import com.gnova.data.api.response.NewsResponse
import com.gnova.data.util.Constants.API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    fun getBreakingNews(
            @Query("country") country: String = "gb",
            @Query("page") page: Int = 1,
            @Query("apikey") apiKey: String  = API_KEY
    ): Single<NewsResponse>


    @GET("everything")
    fun searchNews(
            @Query("q") searchQuery: String,
            @Query("page") page: Int = 1,
            @Query("apikey") apiKey: String  = API_KEY
    ): Single<NewsResponse>

}