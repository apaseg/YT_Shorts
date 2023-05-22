package com.example.ytshorts

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoService {
    @GET("videos")
    suspend fun getVideos(@Query("page") page: Int): Response<VideoResponse>
}
