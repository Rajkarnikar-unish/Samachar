package com.example.samachar

import retrofit2.Response
import retrofit2.http.*

interface NewsAPI {

    @GET("/api/1/news")
    suspend fun getNews(
        @Query("apikey") key: String,
        @Query("language") language: String = "en",
    ) : Response<News>
}