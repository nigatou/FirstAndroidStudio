package com.example.myfirstapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myfirstapplication.R
import com.example.myfirstapplication.databinding.NewPostFragmentBinding
import com.example.myfirstapplication.post.PostViewModel
import com.example.myfirstapplication.service.AndroidUtils
import com.example.myfirstapplication.service.StringArg

class NewPostFragment : Fragment(R.layout.new_post_fragment) {

    companion object {
        var Bundle.textArg: String? by StringArg
    }

    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = NewPostFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        binding.apply {
            val post = viewModel.getPost()!!
            author.text = post.author
            content.setText(post.content)
            published.text = post.published
            link.setText(post.video)
            contentLink.text = post.video
            if (post.video != "") {
                contentVideo.visibility = View.VISIBLE
                playVideo.visibility = View.VISIBLE
                contentLink.visibility = View.VISIBLE
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
                viewModel.changeContent(content.text.toString(), link.text.toString())
                viewModel.save()
                AndroidUtils.hideKeyboard(view)
                findNavController().navigateUp()
            }

            viewModel.postCreated.observe(viewLifecycleOwner) {
                viewModel.loadPosts()
                findNavController().navigateUp()
            }
        }
        return binding.root
    }
}