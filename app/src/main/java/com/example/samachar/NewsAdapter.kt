package com.example.samachar

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.example.samachar.databinding.FragmentNewsBinding
import com.example.samachar.databinding.NewsItemBinding

class NewsAdapter(
    var news: List<com.example.samachar.Result>
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

        holder.binding.apply {
            newsTitle.text = news.title
        }
//        Glide.with(holder).load(news.image_url.toString()).into(holder.binding.newsThumbnail)
    }

    override fun getItemCount(): Int = news.size

}