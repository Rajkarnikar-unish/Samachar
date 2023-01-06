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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment(R.layout.fragment_news) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var newsAdapter : NewsAdapter
    private lateinit var newsRecyclerView : RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        newsRecyclerView = view.findViewById(R.id.newsListRV)
        newsRecyclerView.layoutManager = layoutManager

        newsAdapter = NewsAdapter()

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

//            Log.i(TAG, "API REQUEST: --->" + response.body()!!)

            if(response.isSuccessful && response.body() != null) {
                newsAdapter.news = response.body()!!.results
                Log.d(TAG, "API RESPONSE ===> \n ${response.body()!!.results}")
            } else {
                Log.e(TAG, "Response not successful!")
            }

            progressBar.isVisible = false
        }

//        val subjectFragment = SubjectFragment()
//
//        var titleTextView = view.findViewById<TextView>(R.id.tvTitle)
////            var fragment = parentFragmentManager?.beginTransaction()
////            fragment?.replace(R.id.newsFrameLayout, subjectFragment)
////            fragment?.commit()
//
//        println(titleTextView.text)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}