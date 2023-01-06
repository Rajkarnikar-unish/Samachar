package com.example.samachar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsFragment = NewsFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.newsFrameLayout, newsFragment)
            commit()
        }
    }
}