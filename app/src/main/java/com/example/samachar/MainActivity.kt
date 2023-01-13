package com.example.samachar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samachar.Result
import com.example.samachar.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() , TitleClickListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var newsAdapter: NewsAdapter
//    private var listener = TitleClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true

            val response = try {
                RetrofitInstance.api.getNews(key = "pub_1504750a1ea1dfabdcf2cfb087466f9fda5ca")
            } catch (e: IOException) {
                Log.e(TAG, "IOException: you might not have internet")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e(TAG, "HttpException: unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }

            if(response.isSuccessful && response.body() != null) {
                newsAdapter.news = response.body()!!.results
                Log.d(TAG, "API RESPONSE ===> \n ${response.body()!!.results}")
            } else {
                Log.e(TAG, "Response not successful!")
            }

            binding.progressBar.isVisible = false
        }
    }

    private fun setupRecyclerView() = binding.newsListRV.apply {
        newsAdapter = NewsAdapter(this@MainActivity)
        adapter = newsAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

    override fun onClick(result: Result) {
        val intent = Intent(this, NewsItemActivity::class.java)
        startActivity(intent)
    }
}