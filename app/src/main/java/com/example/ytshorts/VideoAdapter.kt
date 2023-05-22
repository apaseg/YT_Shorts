package com.example.ytshorts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    private val videoList: MutableList<Video> = mutableListOf()

    // ... other methods

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videoList[position]

        // Bind the data to the ViewHolder views
        holder.imageViewThumbnail.setImageResource(R.drawable.krishnaimage) // Replace with actual image loading mechanism
        holder.textViewTitle.text = video.title
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    fun addVideos(videos: List<Video>) {
        val startPosition = videoList.size
        videoList.addAll(videos)
        notifyItemRangeInserted(startPosition, videos.size)
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewThumbnail: ImageView = itemView.findViewById(R.id.imageViewThumbnail)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
    }
}

