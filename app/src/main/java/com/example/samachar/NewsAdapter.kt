package com.example.samachar

import android.icu.text.CaseMap.Title
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.samachar.databinding.NewsItemBinding

class NewsAdapter(private val listener: TitleClickListener) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bindData(result : Result, listener: TitleClickListener) {
//            binding.tvTitle.text = result.title
            binding.root.setOnClickListener {
                listener.onClick(result)
            }
        }
    }

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

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.apply {
            val news = news[position]

//            tvTitle.setOnClickListener {
//                val activity = it.context as AppCompatActivity
//                activity.supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.newsFrameLayout, SubjectFragment())
//                    .addToBackStack(null)
//                    .commit()
//            }

            tvTitle.text = news.title
            tvCategory.text = news.category?.get(0) ?: ""
            tvDescription.text = news.description
            tvPublishedDate.text = news.pubDate?.substring(0, 10)
        }
        holder.bindData(result = news[position], listener)
    }
}