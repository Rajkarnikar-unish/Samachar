package com.example.samachar

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.example.samachar.databinding.FragmentNewsBinding
import com.example.samachar.databinding.NewsItemBinding
import java.io.FileNotFoundException

class NewsAdapter(
    var news: List<Result>,
    private val context: NewsFragment
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    inner class NewsViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(NewsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        ))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = news[position]

        Log.i(TAG, "IMAGE URLS::::> ${news.image_url}")

        holder.binding.apply {
            newsTitle.text = news.title
            try {
                Glide
                    .with(context)
                    .load(news.image_url.toString())
                    .error(R.drawable.image2)
                    .into(newsThumbnail)
            } catch (e: FileNotFoundException) {
                throw e
            }
        }
        holder.binding.root.setOnClickListener {
            Log.i(TAG, "THIS IS TEST CLICK: ${news.title}")

            val action = NewsFragmentDirections.actionSelectNews()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int = news.size

}