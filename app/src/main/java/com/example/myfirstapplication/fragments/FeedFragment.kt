package com.example.myfirstapplication.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myfirstapplication.R
import com.example.myfirstapplication.adapter.OnInteractionListener
import com.example.myfirstapplication.adapter.PostsAdapter
import com.example.myfirstapplication.databinding.FeedFragmentBinding
import com.example.myfirstapplication.post.Post
import com.example.myfirstapplication.post.PostViewModel

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private val viewModel: PostViewModel by viewModels(
            ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val binding = FeedFragmentBinding.inflate(
                inflater,
                container,
                false
        )

        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
                findNavController().navigate(R.id.action_feedFragment_to_newPostFragment)
            }

            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }

            override fun onPlayVideo(post: Post) {
                viewModel.playVideo(post)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                startActivity(intent)
            }

            override fun onView(post: Post) {
                val bundle = Bundle().apply {
                    putLong("postId", post.id)
                    putString("postAuthor", post.author)
                    putString("postContent", post.content)
                    putString("postPublished", post.published)
                    putInt("postLikes", post.likes)
                    putInt("postShares", post.shares)
                    putBoolean("postLikedByMe", post.likedByMe)
                    putBoolean("postSharedByMe", post.sharedByMe)
                    putInt("postViews", post.views)
                    putString("postVideo", post.video)
                }
                findNavController().navigate(R.id.action_feedFragment_to_cardPostFragment, bundle)
            }
        })

        binding.list.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner, { posts ->
            adapter.submitList(posts)
        })

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_newPostFragment)
        }

        return binding.root
    }
}