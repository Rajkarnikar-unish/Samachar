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
import com.example.samachar.databinding.FragmentSubjectBinding

class SubjectFragment(
//    private val result: Result,
) : Fragment() {

    private var result: Result? = null

    private lateinit var toolbar : androidx.appcompat.widget.Toolbar

    private lateinit var binding: FragmentSubjectBinding

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
//        (requireActivity() as AppCompatActivity).supportActionBar?.setShowHideAnimationEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            result = SubjectFragmentArgs.fromBundle(it).result

            binding.subjectTitle.text = result?.title
            binding.contentTextView.text = result?.description
            binding.publishedDateTV.text = result?.pubDate
        }

        binding.btnBookmark.setOnClickListener {
            binding.btnBookmark.setImageResource(R.drawable.ic_bookmark)
        }

//        val subjectTitle = view.findViewById<TextView>(R.id.subjectTitle)
//        val content = view.findViewById<TextView>(R.id.contentTextView)
//        val publishedDate = view.findViewById<TextView>(R.id.publishedDateTextView)
//        val thumbnailImage = view.findViewById<ImageView>(R.id.thumbnailImageView)
//
//        subjectTitle.text = result.title
//        content.text = result.content ?: result.description
//        publishedDate.text = result.pubDate?.subSequence(0, 10)
//
//        println(result.image_url)
//
//        if (result.image_url == null) {
//            thumbnailImage.visibility = View.GONE
//        } else {
//            Glide.with(this).load(result.image_url.toString()).into(thumbnailImage)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSubjectBinding.inflate(layoutInflater, container, false)
        return binding.root

//        toolbar = view.findViewById(R.id.subjectToolbar)
//
//        toolbar.setNavigationIcon(R.drawable.ic_back)
//
//        toolbar.setNavigationOnClickListener { view ->
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.newsFrameLayout, NewsFragment())
//                ?.commit()
//        }


    }
}