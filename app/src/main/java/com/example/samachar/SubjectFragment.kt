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
            binding.publishedDateTV.text = result?.pubDate?.substring(0, 10)
        }

        var isClicked : Boolean = false
        binding.btnBookmark.setOnClickListener {
            if (isClicked) {
                isClicked = !isClicked
                binding.btnBookmark.setImageResource(R.drawable.ic_bookmark)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSubjectBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}