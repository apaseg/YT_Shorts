package com.example.ytshorts

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkManager {
    private val retrofit: Retrofit

    init {
        val okHttpClient = OkHttpClient.Builder()
            // Add any necessary headers or configurations to the OkHttpClient
            .build()


        retrofit = Retrofit.Builder()
            .baseUrl("https://internship-service.onrender.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun fetchVideos(page: Int): List<Video> {
        val videoService = retrofit.create(VideoService::class.java)
        val response = videoService.getVideos(page)

        if (response.isSuccessful) {
            val videoResponse = response.body()
            return videoResponse?.videos ?: emptyList()
        }
        else {
            // Handle API request failure
            throw ApiException("Failed to fetch videos. Error: ${response.code()}")
        }
    }
}
