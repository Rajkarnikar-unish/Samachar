package com.example.samachar

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.samachar.databinding.NewsItemBinding

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var news : List<Result>
        get() = differ.currentList
        set(value) {differ.submitList(value)}

    override fun getItemCount() = news.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(NewsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    val subjectFragment = SubjectFragment()

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.apply {
            val news = news[position]

            tvTitle.setOnClickListener {
                val activity = it.context as AppCompatActivity
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.newsFrameLayout, subjectFragment)
                    .commit()
            }

            tvTitle.text = news.title
            tvCategory.text = news.category[0]
            tvDescription.text = news.description
            tvPublishedDate.text = news.pubDate.substring(0, 10)
        }
    }
}