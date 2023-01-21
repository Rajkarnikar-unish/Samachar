package com.example.samachar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.samachar.databinding.FragmentNewsBinding
import retrofit2.HttpException
import java.io.IOException

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding

    private lateinit var newsAdapter : NewsAdapter
    private lateinit var newsRecyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newsListRV.setHasFixedSize(true)
        binding.newsListRV.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL,
        )

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true

            val response = try {
                RetrofitInstance.api.getNews(key = "pub_1504750a1ea1dfabdcf2cfb087466f9fda5ca")
            } catch (e: IOException) {
                Log.e(TAG, "IOException: you might not have internet")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException: unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null) {

                val news = response.body()!!.results

                newsAdapter = NewsAdapter(news, this@NewsFragment)
                binding.newsListRV.adapter = newsAdapter

//                Log.d(TAG, "API RESPONSE ===> \n ${response.body()!!.results}")
            } else {
                Log.e(TAG, "Response not successful!")
            }

            binding.progressBar.isVisible = false
        }
    }
}