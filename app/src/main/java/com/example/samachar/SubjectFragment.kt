package com.example.samachar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.example.samachar.Result

class SubjectFragment(
    private val result: Result,
) : Fragment(R.layout.fragment_subject) {

    private lateinit var toolbar : androidx.appcompat.widget.Toolbar

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
//        (requireActivity() as AppCompatActivity).supportActionBar?.setShowHideAnimationEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val subjectTitle = view.findViewById<TextView>(R.id.subjectTitle)
        val content = view.findViewById<TextView>(R.id.contentTextView)
        val publishedDate = view.findViewById<TextView>(R.id.publishedDateTextView)
        val thumbnailImage = view.findViewById<ImageView>(R.id.thumbnailImageView)

        subjectTitle.text = result.title
        content.text = result.content ?: result.description
        publishedDate.text = result.pubDate?.subSequence(0, 10)

        println(result.image_url)

        if (result.image_url == null) {
            thumbnailImage.visibility = View.GONE
        } else {
            Glide.with(this).load(result.image_url.toString()).into(thumbnailImage)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        toolbar = view.findViewById(R.id.subjectToolbar)
//
//        toolbar.setNavigationIcon(R.drawable.ic_back)
//
//        toolbar.setNavigationOnClickListener { view ->
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.newsFrameLayout, NewsFragment())
//                ?.commit()
//        }


        return inflater.inflate(R.layout.fragment_subject, container, false)
    }
}