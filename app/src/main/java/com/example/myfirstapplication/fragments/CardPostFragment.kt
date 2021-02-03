package com.example.myfirstapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myfirstapplication.R
import com.example.myfirstapplication.databinding.CardPostFragmentBinding
import com.example.myfirstapplication.post.Post
import com.example.myfirstapplication.post.PostViewModel

class CardPostFragment : Fragment(R.layout.card_post_fragment) {
    private val viewModel: PostViewModel by viewModels(
            ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = CardPostFragmentBinding.inflate(
                inflater,
                container,
                false
        )

        binding.apply {
            val postId = arguments?.getLong("postId")
            val postContent = arguments?.getString("postContent")
            val postLikes = arguments?.getInt("postLikes")
            val postShares = arguments?.getInt("postShares")
            val postLikedByMe = arguments?.getBoolean("postLikedByMe")
            val postSharedByMe = arguments?.getBoolean("postSharedByMe")
            val postViews = arguments?.getInt("postViews")
            val postVideo = arguments?.getString("postVideo")

            val post = Post(
                    id = postId!!,
                    content = postContent!!,
                    likes = postLikes!!,
                    shares = postShares!!,
                    likedByMe = postLikedByMe!!,
                    sharedByMe = postSharedByMe!!,
                    views = postViews!!,
                    video = postVideo!!
            )

            content.text = postContent
            like.text = postLikes.toString()
            share.text = postShares.toString()
            like.isChecked = postLikedByMe
            share.isChecked = postSharedByMe
            view.text = postViews.toString()
            contentLink.text = postVideo

            if (contentLink.text != "") {
                contentLink.visibility = View.VISIBLE
                contentVideo.visibility = View.VISIBLE
                playVideo.visibility = View.VISIBLE
            } else {
                contentLink.visibility = View.GONE
                contentVideo.visibility = View.GONE
                playVideo.visibility = View.GONE
            }

            like.setOnClickListener {
                like.text = viewModel.likeById(postId).toString()
            }

            share.setOnClickListener {
                share.text = viewModel.shareById(postId).toString()
            }

            imageButton.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                viewModel.removeById(postId)
                                findNavController().navigateUp()
                                true
                            }
                            R.id.edit -> {
                                viewModel.edit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }

        return binding.root
    }
}