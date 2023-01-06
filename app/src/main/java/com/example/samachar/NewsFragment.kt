package com.example.samachar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import retrofit2.HttpException
import java.io.IOException

class NewsFragment : Fragment(R.layout.fragment_news), TitleClickListener {

    private lateinit var newsAdapter : NewsAdapter
    private lateinit var newsRecyclerView : RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        newsRecyclerView = view.findViewById(R.id.newsListRV)
        newsRecyclerView.layoutManager = layoutManager

        newsAdapter = NewsAdapter(this)

        newsRecyclerView.adapter = newsAdapter

        var progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        lifecycleScope.launchWhenCreated {
            progressBar.isVisible = true

            val response = try {
                RetrofitInstance.api.getNews(key = "pub_1504750a1ea1dfabdcf2cfb087466f9fda5ca")
            } catch (e: IOException) {
                Log.e(TAG, "IOException: you might not have internet")
                progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e(TAG, "HttpException: unexpected response")
                progressBar.isVisible = false
                return@launchWhenCreated
            }

            if(response.isSuccessful && response.body() != null) {
                newsAdapter.news = response.body()!!.results
                Log.d(TAG, "API RESPONSE ===> \n ${response.body()!!.results}")
            } else {
                Log.e(TAG, "Response not successful!")
            }

            progressBar.isVisible = false
        }
    }

    override fun onClick(result: Result) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.newsFrameLayout, SubjectFragment(result))
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        newsRecyclerView = view.findViewById(R.id.newsListRV)
        newsRecyclerView.adapter = NewsAdapter(this)

        return view
    }

}