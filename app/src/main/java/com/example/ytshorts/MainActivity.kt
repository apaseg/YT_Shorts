package com.example.ytshorts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val networkManager = NetworkManager()
    private val videoAdapter = VideoAdapter()

    private var currentPage = 0
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewVideos: RecyclerView = findViewById(R.id.recyclerViewVideos)
        val layoutManager = LinearLayoutManager(this)
        recyclerViewVideos.layoutManager = layoutManager
        recyclerViewVideos.adapter = videoAdapter

        Log.d("h","anmol")
        // Set up scroll listener
        recyclerViewVideos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Check if reached the end of the current list
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val endReached = lastVisibleItem + 5 >= totalItemCount // Adjust the threshold as per your preference

                if (endReached && !isLoading) {
                    loadNextPage()
                }
            }
        })

        // Fetch the first page of videos
        loadNextPage()
    }

    private fun loadNextPage() {
        if (!isLoading) {
            isLoading = true

            // Show a loading indicator if necessary

            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val videos = networkManager.fetchVideos(currentPage)
                    videoAdapter.addVideos(videos)
                    Log.d("hi","anmol")
                    currentPage++
                } catch (e: Exception) {
                    // Handle error
                    Toast.makeText(this@MainActivity, "Failed to fetch videos", Toast.LENGTH_SHORT).show()
                } finally {
                    isLoading = false

                    // Hide the loading indicator if necessary
                }
            }
        }
    }
}
