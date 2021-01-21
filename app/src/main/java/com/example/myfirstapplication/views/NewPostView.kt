package com.example.myfirstapplication.views

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.myfirstapplication.R
import com.example.myfirstapplication.RequestCode
import com.example.myfirstapplication.databinding.NewPostBinding
import com.example.myfirstapplication.post.PostViewModel

class NewPostView : Fragment(R.layout.new_post) {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = NewPostBinding.inflate(
                inflater,
                container,
                false
        )

        val request = RequestCode()

        binding.apply {
            val intent = Intent()
            val extras = intent.extras

            if (extras != null) {
                content.setText(extras.getString("postContent"))
                link.setText(extras.getString("postVideo"))
                contentLink.text = extras.getString("postVideo")
                if (extras.getString("postVideo") != "") {
                    contentVideo.visibility = View.VISIBLE
                    playVideo.visibility = View.VISIBLE
                    contentLink.visibility = View.VISIBLE
                }
            }

            link.addTextChangedListener {
                if (link.text.toString() != "") {
                    contentLink.text = link.text
                    contentVideo.visibility = View.VISIBLE
                    playVideo.visibility = View.VISIBLE
                    contentLink.visibility = View.VISIBLE
                } else {
                    contentLink.text = link.text
                    contentVideo.visibility = View.GONE
                    playVideo.visibility = View.GONE
                    contentLink.visibility = View.GONE
                }
            }

            fab.setOnClickListener {
                val intentSend = Intent()

                when {
                    TextUtils.isEmpty(content.text) -> {
                        activity?.setResult(request.empty, intentSend)
                    }
                    extras == null -> {
                        intentSend.putExtra("postContent", content.text.toString())
                        intentSend.putExtra("postVideo", link.text.toString())
                        activity?.setResult(request.add, intentSend)
                    }
                    extras.getBoolean("edit") -> {
                        intentSend.putExtra("postId", extras.getLong("postId").toInt())
                        intentSend.putExtra("postContent", content.text.toString())
                        intentSend.putExtra("postVideo", link.text.toString())
                        activity?.setResult(request.edit, intentSend)
                    }
                }
                findNavController().navigateUp()
            }
        }
        return binding.root
    }
}