package com.example.samachar

import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var subjectTitle = view.findViewById<TextView>(R.id.subjectTitle)
        var content = view.findViewById<TextView>(R.id.contentTextView)
        var publishedDate = view.findViewById<TextView>(R.id.publishedDateTextView)
        var thumbnailImage = view.findViewById<ImageView>(R.id.thumbnailImageView)

        subjectTitle.text = result.title
        content.text = result.content ?: result.description
        publishedDate.text = result.pubDate?.subSequence(0, 10)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subject, container, false)
    }
}